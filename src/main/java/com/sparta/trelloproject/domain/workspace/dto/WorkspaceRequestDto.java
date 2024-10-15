package com.sparta.trelloproject.domain.workspace.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkspaceRequestDto {
    private String workspaceName;
    private String workspaceDescription;
}
