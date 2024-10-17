package com.sparta.trelloproject.domain.card.service;

import com.sparta.trelloproject.common.exception.ForbiddenException;
import com.sparta.trelloproject.common.exception.NotFoundException;
import com.sparta.trelloproject.common.exception.ResponseCode;
import com.sparta.trelloproject.common.s3.S3Service;
import com.sparta.trelloproject.domain.card.dto.reponse.CardImageResponseDto;
import com.sparta.trelloproject.domain.card.dto.reponse.CardListResponseDto;
import com.sparta.trelloproject.domain.card.dto.reponse.CardResponseDto;
import com.sparta.trelloproject.domain.card.dto.request.CardImageRequestDto;
import com.sparta.trelloproject.domain.card.dto.request.CardRequestDto;
import com.sparta.trelloproject.domain.card.dto.request.CardStatusRequestDto;
import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.card.entity.CardImage;
import com.sparta.trelloproject.domain.card.repository.CardImageRepository;
import com.sparta.trelloproject.domain.card.repository.CardRepository;
import com.sparta.trelloproject.domain.comment.dto.response.UpdateCommentResponse;
import com.sparta.trelloproject.domain.comment.repository.CommentRepository;
import com.sparta.trelloproject.domain.list.entity.Lists;
import com.sparta.trelloproject.domain.list.repository.ListRepository;
import com.sparta.trelloproject.domain.workspace.entity.UserWorkspace;
import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import com.sparta.trelloproject.domain.workspace.repository.UserWorkSpaceRepository;
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

        String uploadImageUrl = s3Service.uploadFile(file);

        CardImageRequestDto cardImageDto = createCardImageDto(file, uploadImageUrl);
        cardImageRepository.save(CardImage.of(cardImageDto, card));

        return uploadImageUrl;
    }

    public String updateCard(MultipartFile file, CardRequestDto cardRequestDto, Long cardId,
        long userId) {
        UserWorkspace userWorkspace = userWorkSpaceRepository.findByWorkspaceIdAndUserId(
            cardRequestDto.getWorkSpaceId(), userId);

        if (userWorkspace == null || WorkSpaceUserRole.ROLE_READ_USER.equals(
            userWorkspace.getWorkSpaceUserRole())) {
            throw new ForbiddenException(ResponseCode.INVALID_UPLOAD);
        }

        Card card = cardRepository.findByCardId(cardId);
        CardImage cardImage = cardImageRepository.findByCardId(cardId);
        String existingFilePath = cardImage != null ? cardImage.getPath() : null;

        card.update(cardRequestDto);

        String uploadImageUrl = s3Service.updateFile(file, existingFilePath);

        CardImageRequestDto cardImageDto = createCardImageDto(file, uploadImageUrl);

        if (cardImage != null) {
            cardImage.update(cardImageDto);
        } else {
            cardImageRepository.save(CardImage.of(cardImageDto, card));
        }
        return uploadImageUrl;
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

        return CardResponseDto.of(card, comments, cardImageResponse);
    }

    public void deleteCard(Long id) {
        Card card = cardRepository.findByCardId(id);
        cardRepository.delete(card);
    }

    public void updateCardStatus(Long cardId, CardStatusRequestDto cardStatusRequestDto) {
        Card card = cardRepository.findByCardId(cardId);
        card.updateStatus(cardStatusRequestDto);
    }

    public void deleteCardImage(Long cardId) {
        CardImage cardImage = cardImageRepository.findByCardId(cardId);
        if (cardImage != null) {
            s3Service.deleteFile(cardImage.getFileName());
            cardImageRepository.delete(cardImage);
        }
    }

    private CardImageRequestDto createCardImageDto(MultipartFile multipartFile, String url) {
        String originalFileName = multipartFile.getOriginalFilename();
        String originName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        return new CardImageRequestDto(url, fileName, originName, extension);
    }
}
