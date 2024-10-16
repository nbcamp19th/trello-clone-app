package com.sparta.trelloproject.domain.board.repository;

import com.sparta.trelloproject.domain.board.entity.Board;

import java.util.List;

public interface BoardQueryRepository {
    List<Board> findAllByWorkspaceIdWithDetails(Long workspaceId);

    Board findByIdWithDetails(Long boardId);
}
