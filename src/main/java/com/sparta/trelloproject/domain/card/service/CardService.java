package com.sparta.trelloproject.domain.card.service;

import com.sparta.trelloproject.common.annotation.AuthUser;
import com.sparta.trelloproject.common.exception.ForbiddenException;
import com.sparta.trelloproject.common.exception.NotFoundException;
import com.sparta.trelloproject.common.exception.ResponseCode;
import com.sparta.trelloproject.common.s3.S3Service;
import com.sparta.trelloproject.domain.card.dto.request.CardRequestDto;
import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.card.entity.CardImage;
import com.sparta.trelloproject.domain.card.repository.CardImageRepository;
import com.sparta.trelloproject.domain.card.repository.CardRepository;
import com.sparta.trelloproject.domain.list.entity.Lists;
import com.sparta.trelloproject.domain.list.repository.ListRepository;
import com.sparta.trelloproject.domain.user.repository.UserRepository;
import com.sparta.trelloproject.domain.workspace.entity.UserWorkspace;
import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import com.sparta.trelloproject.domain.workspace.repository.UserWorkSpaceRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
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

            Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카드를 찾을 수 없습니다."));

            CardImage cardImage = cardImageRepository.findByCardId(cardId);
            String filePath = cardImage.getPath();
            card.update(cardRequestDto);

            String uploadImageUrl = s3Service.updateFile(file, card, filePath);
            return uploadImageUrl;
        }
        catch (IOException e) {
            throw new IllegalArgumentException("S3 파일 업로드에 실패하였습니다.");
        }
    }
}

