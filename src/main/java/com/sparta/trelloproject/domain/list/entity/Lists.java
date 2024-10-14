package com.sparta.trelloproject.domain.list.entity;

import com.sparta.trelloproject.domain.board.entity.Board;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Getter
public class Lists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "타이틀은 공백이 될 수 없습니다")
    private String title;

    private Integer sequence;

    @ManyToOne
    @JoinColumn(name = "boards_id")
    private Board board;
}
