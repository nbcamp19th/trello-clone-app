package com.sparta.trelloproject.domain.board.controller;

import com.sparta.trelloproject.common.annotation.AuthUser;
import com.sparta.trelloproject.common.response.SuccessResponse;
import com.sparta.trelloproject.domain.board.dto.request.BoardRequestDto;
import com.sparta.trelloproject.domain.board.dto.response.BoardResponseDto;
import com.sparta.trelloproject.domain.board.entity.Board;
import com.sparta.trelloproject.domain.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 보드 생성
    @PostMapping("/v1/workspaces/{workspaceId}/boards")
    public ResponseEntity<SuccessResponse<BoardResponseDto>> saveBoard(@PathVariable Long workspaceId,
                                                                       @Valid @RequestBody BoardRequestDto boardRequestDto,
                                                                       @AuthenticationPrincipal AuthUser authUser) {
        Board board = boardService.saveBoard(workspaceId, boardRequestDto, authUser);
        BoardResponseDto createdBoard = BoardResponseDto.from(board);
        return ResponseEntity.ok(SuccessResponse.of(createdBoard));
    }

    // 보드 수정
    @PatchMapping("/v1/workspaces/{workspaceId}/boards/{boardId}")
    public ResponseEntity<SuccessResponse<BoardResponseDto>> updateBoard(@PathVariable Long boardId,
                                                                         @RequestBody BoardRequestDto boardRequestDto,
                                                                         @AuthenticationPrincipal AuthUser authUser) {
        Board board = boardService.updateBoard(boardId, boardRequestDto, authUser);
        BoardResponseDto updatedBoard = BoardResponseDto.from(board);
        return ResponseEntity.ok(SuccessResponse.of(updatedBoard));
    }

    // 보드 다건 조회
    @GetMapping("/v1/workspaces/{workspaceId}/boards")
    public ResponseEntity<SuccessResponse<List<BoardResponseDto>>> getBoardList(@PathVariable Long workspaceId,
                                                                                @AuthenticationPrincipal AuthUser authUser) {
        List<BoardResponseDto> boards = boardService.getBoardList(workspaceId, authUser)
                .stream()
                .map(BoardResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(SuccessResponse.of(boards));
    }

    // 보드 단건 조회
    @GetMapping("/v1/workspaces/{workspaceId}/boards/{boardId}")
    public ResponseEntity<SuccessResponse<BoardResponseDto>> getBoard(@PathVariable Long boardId,
                                                                      @AuthenticationPrincipal AuthUser authUser) {
        Board board = boardService.getBoard(boardId, authUser);
        BoardResponseDto boardResponseDto = BoardResponseDto.from(board);
        return ResponseEntity.ok(SuccessResponse.of(boardResponseDto));
    }

    // 보드 삭제
    @DeleteMapping("/v1/workspaces/{workspaceId}/boards/{boardId}")
    public ResponseEntity<SuccessResponse<Void>> deleteBoard(@PathVariable Long boardId,
                                                             @AuthenticationPrincipal AuthUser authUser) {
        boardService.deleteBoard(boardId, authUser);
        return ResponseEntity.ok(SuccessResponse.of(null));
    }
}
