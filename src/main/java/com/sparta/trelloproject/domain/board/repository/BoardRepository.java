package com.sparta.trelloproject.domain.board.repository;

import com.sparta.trelloproject.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardQueryRepository {
    List<Board> findAllByWorkspaceId(Long workspaceId);
}
