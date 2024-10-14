package com.sparta.trelloproject.domain.list.entity;

import com.sparta.trelloproject.domain.board.entity.Board;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "Lists")
@Getter
public class Lists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Integer sequence;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "boards_id")
    private Board board;
}
