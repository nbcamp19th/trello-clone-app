package com.sparta.trelloproject.domain.card.controller;

import com.sparta.trelloproject.common.annotation.AuthUser;
import com.sparta.trelloproject.common.response.SuccessResponse;
import com.sparta.trelloproject.domain.card.dto.request.CardRequestDto;
import com.sparta.trelloproject.domain.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    /**
     * 카드를 생성합니다
     * @param file           파일을 입력받습니다
     * @param cardRequestDto 카드를 등록할 타이틀, 컨텐츠, 마감일과 등록할 list의 id, 워크스페이스 접근 권한을 알기위한 workSpaceid를
     *                       받습니다
     * @param authUser       현재 로그인중인 유저 정보를 가져오기 위함입니다.
     * @return 업로드한 파일 이름이 반환됩니다.
     */
    @PostMapping("/cards")
    public ResponseEntity<SuccessResponse<String>> saveCard(
        @RequestParam("file") MultipartFile file,
        @ModelAttribute CardRequestDto cardRequestDto,
        @AuthenticationPrincipal AuthUser authUser) {
        String uploadedFileName = cardService.saveCard(authUser.getUserId(), file, cardRequestDto);

        return ResponseEntity.ok(SuccessResponse.of(uploadedFileName));
    }

    /**
     * 카드를 수정합니다
     * @param file 파일을 입력받습니다
     * @param cardRequestDto 카드를 등록할 타이틀, 컨텐츠, 마감일과 등록할 list의 id, 워크스페이스 접근 권한을 알기위한 workSpaceid를
     *                       받습니다
     * @param cardId 수정할 카드 id를 PathVariable 입력받습니다.
     * @param authUser 현재 로그인중인 유저 정보를 가져오기 위함입니다.
     * @return 수정된 파일 이름을 반환합니다.
     */
    @PatchMapping("/cards/{cardId}")
    public ResponseEntity<SuccessResponse<String>> updateCard(@RequestParam("file") MultipartFile file,
        @ModelAttribute CardRequestDto cardRequestDto, @PathVariable Long cardId,
        @AuthenticationPrincipal AuthUser authUser) {
        String uploadedFileName = cardService.updateCard(file, cardRequestDto, cardId, authUser.getUserId());
        return ResponseEntity.ok(SuccessResponse.of(uploadedFileName));
    }
}
