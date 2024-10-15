package com.sparta.trelloproject.domain.user.entity;

import com.sparta.trelloproject.domain.user.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private boolean isDeleted = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole authority;

}
