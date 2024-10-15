package com.sparta.trelloproject.domain.board.service;

import com.sparta.trelloproject.common.annotation.AuthUser;
import com.sparta.trelloproject.common.exception.ForbiddenException;
import com.sparta.trelloproject.common.exception.NotFoundException;
import com.sparta.trelloproject.common.exception.ResponseCode;
import com.sparta.trelloproject.domain.board.dto.request.BoardRequestDto;
import com.sparta.trelloproject.domain.board.entity.Board;
import com.sparta.trelloproject.domain.board.repository.BoardRepository;
import com.sparta.trelloproject.domain.workspace.entity.UserWorkspace;
import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import com.sparta.trelloproject.domain.workspace.repository.UserWorkSpaceRepository;
import com.sparta.trelloproject.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserWorkSpaceRepository userWorkspaceRepository;

    // 보드 생성
    public Board saveBoard(Long workspaceId, BoardRequestDto boardRequestDto, AuthUser authUser) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_WORKSPACE));

        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(workspaceId, authUser.getUserId());
        if (userWorkspace == null || userWorkspace.getWorkSpaceUserRole() == WorkSpaceUserRole.ROLE_READ_USER) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }

        Board board = new Board(boardRequestDto.getTitle(), boardRequestDto.getBackgroundColor(), workspace);
        return boardRepository.save(board);
    }

    // 보드 수정
    public Board updateBoard(Long boardId, BoardRequestDto boardRequestDto, AuthUser authUser) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_BOARD));

        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(board.getWorkspace().getId(), authUser.getUserId());
        if (userWorkspace == null || userWorkspace.getWorkSpaceUserRole() == WorkSpaceUserRole.ROLE_READ_USER) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }

        board.update(boardRequestDto.getTitle(), boardRequestDto.getBackgroundColor());
        return boardRepository.save(board);
    }

    // 보드 다건 조회
    @Transactional(readOnly = true)
    public List<Board> getBoardList(Long workspaceId, AuthUser authUser) {
        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(workspaceId, authUser.getUserId());
        if (userWorkspace == null) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }
        return boardRepository.findAllByWorkspaceId(workspaceId);
    }

    // 보드 단건 조회
    @Transactional(readOnly = true)
    public Board getBoard(Long boardId, AuthUser authUser) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_BOARD));

        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(board.getWorkspace().getId(), authUser.getUserId());
        if (userWorkspace == null) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }

        return board;
    }

    // 보드 삭제
    public void deleteBoard(Long boardId, AuthUser authUser) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_BOARD));

        UserWorkspace userWorkspace = userWorkspaceRepository.findByWorkspaceIdAndUserId(board.getWorkspace().getId(), authUser.getUserId());

        if (userWorkspace == null || userWorkspace.getWorkSpaceUserRole() == WorkSpaceUserRole.ROLE_READ_USER) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }

        boardRepository.delete(board);
    }
}
