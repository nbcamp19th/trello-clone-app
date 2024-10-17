package com.sparta.trelloproject.domain.list.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ListDeleteRequestDto {
    @NotNull(message = "워크스페이스 고유번호는 필수입니다.")
    private Long workspaceId;
}
