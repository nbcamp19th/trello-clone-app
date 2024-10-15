package com.sparta.trelloproject.domain.card.controller;

import com.sparta.trelloproject.common.annotation.AuthUser;
import com.sparta.trelloproject.common.response.SuccessResponse;
import com.sparta.trelloproject.domain.card.dto.request.CardRequestDto;
import com.sparta.trelloproject.domain.card.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping("/cards")
    public ResponseEntity<SuccessResponse<String>> saveCard(@RequestParam("file") MultipartFile file,
        @ModelAttribute CardRequestDto cardRequestDto,
        @AuthenticationPrincipal AuthUser authUser) {
        String uploadedFileName = cardService.saveCard(authUser.getUserId(),file, cardRequestDto);

        return ResponseEntity.ok(SuccessResponse.of(uploadedFileName));
    }
}
