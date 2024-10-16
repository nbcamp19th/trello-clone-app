package com.sparta.trelloproject.domain.board.entity;

import com.sparta.trelloproject.common.entity.Timestamped;
import com.sparta.trelloproject.domain.list.entity.Lists;
import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "boards")
@Getter
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String backgroundColor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "workspaces_id", nullable = false)
    private Workspace workspace;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Lists> lists;

    public Board(String title, String backgroundColor, Workspace workspace) {
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.workspace = workspace;
    }

    public void update(String title, String backgroundColor) {
        this.title = title;
        this.backgroundColor = backgroundColor;
    }
}
