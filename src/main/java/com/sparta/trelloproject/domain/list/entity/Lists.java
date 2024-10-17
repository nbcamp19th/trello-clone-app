package com.sparta.trelloproject.domain.list.entity;

import com.sparta.trelloproject.domain.board.entity.Board;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lists")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @Column(unique = true)
    private Integer sequence;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boards_id")
    private Board board;

    private Lists(String title, Integer sequence, Board board) {
        this.title = title;
        this.sequence = sequence;
        this.board = board;
    }

    public static Lists of(String title, int sequence, Board board) {
        return new Lists(
                title,
                sequence,
                board
        );
    }

    public void updateListTitle(String title) {
        this.title = title;
    }
}
