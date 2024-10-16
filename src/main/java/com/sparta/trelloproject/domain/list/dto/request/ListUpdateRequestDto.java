package com.sparta.trelloproject.domain.list.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ListUpdateRequestDto {
    @NotBlank(message = "리스트 제목은 필수입니다.")
    private String title;

    @NotNull(message = "워크스페이스 고유번호는 필수입니다.")
    private Long workspaceId;

}
