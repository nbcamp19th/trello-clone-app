package com.sparta.trelloproject.domain.workspace.entity;

import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import jakarta.persistence.*;
<<<<<<< HEAD
=======
import jakarta.validation.constraints.NotNull;
>>>>>>> 546264139de8c347d0560f83e0ef3a940110a99b
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
