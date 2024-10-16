package com.sparta.trelloproject.domain.board.service;

import com.sparta.trelloproject.common.exception.ForbiddenException;
import com.sparta.trelloproject.common.exception.NotFoundException;
import com.sparta.trelloproject.common.exception.ResponseCode;
import com.sparta.trelloproject.domain.board.dto.request.BoardRequestDto;
import com.sparta.trelloproject.domain.board.entity.Board;
import com.sparta.trelloproject.domain.board.repository.BoardRepository;
import com.sparta.trelloproject.domain.workspace.entity.UserWorkspace;
import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import com.sparta.trelloproject.domain.workspace.repository.UserWorkSpaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserWorkSpaceRepository userWorkspaceRepository;

    // 보드 생성
    public Board saveBoard(BoardRequestDto boardRequestDto, Long userId) {
        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(boardRequestDto.getWorkspaceId(), userId);

        if (userWorkspace == null || userWorkspace.getWorkSpaceUserRole() == WorkSpaceUserRole.ROLE_READ_USER) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }

        Board board = new Board(boardRequestDto.getTitle(), boardRequestDto.getBackgroundColor(), userWorkspace.getWorkspace());
        log.info("Board created successfully with title: {}", boardRequestDto.getTitle());

        return boardRepository.save(board);
    }

    // 보드 수정
    public Board updateBoard(Long boardId, BoardRequestDto boardRequestDto, Long userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_BOARD));

        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(board.getWorkspace().getId(), userId);
        if (userWorkspace == null || userWorkspace.getWorkSpaceUserRole() == WorkSpaceUserRole.ROLE_READ_USER) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }

        board.update(boardRequestDto.getTitle(), boardRequestDto.getBackgroundColor());
        return boardRepository.save(board);
    }

    // 보드 다건 조회
    @Transactional(readOnly = true)
    public List<Board> getBoardList(Long workspaceId, Long userId) {
        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(workspaceId, userId);
        if (userWorkspace == null) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }
        return boardRepository.findAllByWorkspaceId(workspaceId);
    }

    // 보드 단건 조회
    @Transactional(readOnly = true)
    public Board getBoard(Long boardId, Long userId) {
        Board board = boardRepository.findByIdWithDetails(boardId);

        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(board.getWorkspace().getId(), userId);
        if (userWorkspace == null) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }

        return board;
    }

    // 보드 삭제
    public void deleteBoard(Long workspaceId, Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_BOARD));

        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(workspaceId, userId);

        if (userWorkspace == null || userWorkspace.getWorkSpaceUserRole() == WorkSpaceUserRole.ROLE_READ_USER) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }

        boardRepository.delete(board);
    }
}
