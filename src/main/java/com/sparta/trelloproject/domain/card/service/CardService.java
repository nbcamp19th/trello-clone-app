package com.sparta.trelloproject.domain.card.service;

import com.sparta.trelloproject.common.exception.ForbiddenException;
import com.sparta.trelloproject.common.exception.NotFoundException;
import com.sparta.trelloproject.common.exception.ResponseCode;
import com.sparta.trelloproject.common.s3.S3Service;
import com.sparta.trelloproject.domain.card.dto.reponse.CardImageResponseDto;
import com.sparta.trelloproject.domain.card.dto.reponse.CardListResponseDto;
import com.sparta.trelloproject.domain.card.dto.reponse.CardResponseDto;
import com.sparta.trelloproject.domain.card.dto.request.CardRequestDto;
import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.card.entity.CardImage;
import com.sparta.trelloproject.domain.card.repository.CardImageRepository;
import com.sparta.trelloproject.domain.card.repository.CardRepository;
import com.sparta.trelloproject.domain.comment.dto.response.UpdateCommentResponse;
import com.sparta.trelloproject.domain.comment.repository.CommentRepository;
import com.sparta.trelloproject.domain.comment.service.CommentService;
import com.sparta.trelloproject.domain.list.entity.Lists;
import com.sparta.trelloproject.domain.list.repository.ListRepository;
import com.sparta.trelloproject.domain.workspace.entity.UserWorkspace;
import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import com.sparta.trelloproject.domain.workspace.repository.UserWorkSpaceRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ListRepository listRepository;
    private final S3Service s3Service;
    private final UserWorkSpaceRepository userWorkSpaceRepository;
    private final CardImageRepository cardImageRepository;
    private final CommentRepository commentRepository;

    public String saveCard(long userId, MultipartFile file, CardRequestDto cardRequestDto) {
        try {
            UserWorkspace userWorkspace = userWorkSpaceRepository.findByWorkspaceIdAndUserId(
                cardRequestDto.getWorkSpaceId(), userId);

            if (userWorkspace == null || WorkSpaceUserRole.ROLE_READ_USER.equals(
                userWorkspace.getWorkSpaceUserRole())) {
                throw new ForbiddenException(ResponseCode.INVALID_UPLOAD);
            }

            Lists lists = listRepository.findById(cardRequestDto.getListId())
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_LIST));

            Card card = Card.from(cardRequestDto, lists);
            cardRepository.save(card);

            String uploadImageUrl = s3Service.uploadFile(file, card);

            return uploadImageUrl;
        }
        catch (IOException e) {
            throw new ForbiddenException(ResponseCode.INVALID_UPLOAD);
        }
    }

    public String updateCard(MultipartFile file, CardRequestDto cardRequestDto, Long cardId,
        long userId) {
        try {
            UserWorkspace userWorkspace = userWorkSpaceRepository.findByWorkspaceIdAndUserId(
                cardRequestDto.getWorkSpaceId(), userId);

            if (userWorkspace == null || WorkSpaceUserRole.ROLE_READ_USER.equals(
                userWorkspace.getWorkSpaceUserRole())) {
                throw new ForbiddenException(ResponseCode.INVALID_UPLOAD);
            }

            Card card = cardRepository.findByCardId(cardId);

            CardImage cardImage = cardImageRepository.findByCardId(cardId);
            String filePath = cardImage.getPath();
            card.update(cardRequestDto);

            String uploadImageUrl = s3Service.updateFile(file, card, filePath);
            return uploadImageUrl;
        }
        catch (IOException e) {
            throw new ForbiddenException(ResponseCode.INVALID_UPLOAD);
        }
    }

    @Transactional(readOnly = true)
    public Page<CardListResponseDto> getCardList(String title, String contents, String manager,
        LocalDateTime dueDateFrom,
        LocalDateTime dueDateTo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cardRepository.findCardList(title, contents, manager, dueDateFrom, dueDateTo,
            pageable);
    }

    @Transactional(readOnly = true)
    public CardResponseDto getCard(Long id) {
        Card card = cardRepository.findByCardId(id);

        List<UpdateCommentResponse> comments = commentRepository.findByCardId(id).stream()
            .map(UpdateCommentResponse::from)
            .collect(Collectors.toList());

        CardImage cardImage = cardImageRepository.findByCardId(id);

        CardImageResponseDto cardImageResponse = CardImageResponseDto.from(cardImage);

        CardResponseDto response = CardResponseDto.of(card, comments, cardImageResponse);
        return response;
    }

    //삭제관련 회의이후 수정!
    public void deleteCard(Long id) {
        Card card = cardRepository.findByCardId(id);

        cardRepository.delete(card);
    }

}
