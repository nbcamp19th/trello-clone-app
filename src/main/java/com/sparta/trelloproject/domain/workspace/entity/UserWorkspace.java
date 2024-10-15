package com.sparta.trelloproject.domain.workspace.entity;

import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserWorkspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private WorkSpaceUserRole userRole;
}
