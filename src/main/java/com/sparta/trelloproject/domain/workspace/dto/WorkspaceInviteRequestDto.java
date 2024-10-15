package com.sparta.trelloproject.domain.workspace.dto;

import lombok.Getter;

@Getter
public class WorkspaceInviteRequestDto {
    String email;
    Long workspaceId;
    String workspaceUserRole;
}
