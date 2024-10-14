package com.sparta.trelloproject.domain.workspace.entity;

import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "user_workspace")
@Getter
public class UserWorkspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private WorkSpaceUserRole userRole;
}
