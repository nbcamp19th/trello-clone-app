package com.sparta.trelloproject.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserAuthorityUpdateRequestDto {
    @NotNull(message = "아이디는 필수입니다.")
    private Long userId;

    @NotBlank(message = "권한은 필수입니다.")
    private String authority;
}
