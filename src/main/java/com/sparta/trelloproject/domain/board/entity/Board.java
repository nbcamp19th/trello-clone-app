package com.sparta.trelloproject.domain.board.entity;

import com.sparta.trelloproject.common.entity.Timestamped;
import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity(name = "boards")
@Getter
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "제목은 공백이 들어올 수 없습니다")
    private String title;

    @NotBlank(message = "배경은 공백이 들어올 수 없습니다")
    private String backgroundColor;

    @ManyToOne
    @JoinColumn(name = "workspaces_id")
    private Workspace workspace;
}
