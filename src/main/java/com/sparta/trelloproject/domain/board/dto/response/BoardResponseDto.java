package com.sparta.trelloproject.domain.board.dto.response;

import com.sparta.trelloproject.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String backgroundColor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardResponseDto from(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getBackgroundColor(),
                board.getCreatedAt(),
                board.getModifiedAt()
        );
    }
}
