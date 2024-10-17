package com.sparta.trelloproject.domain.board.dto;

import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import lombok.Getter;

@Getter
public class BoardAuthorityDto {
    private long boardId;
    private String title;
    private String backgroundColor;
    private int listCount;
    private long workspaceId;
    private WorkSpaceUserRole workspaceAuthority;
}