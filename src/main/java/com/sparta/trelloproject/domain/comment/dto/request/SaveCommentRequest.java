package com.sparta.trelloproject.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveCommentRequest {
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String contents;
}
