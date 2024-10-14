package com.sparta.trelloproject.domain.user.entity;

import com.sparta.trelloproject.domain.user.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    @NotBlank(message = "비밀번호는 꼭 입력해야합니다")
    private String password;
    @NotBlank(message = "이름은 꼭 입력해야합니다")
    private String name;
    private boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    private UserRole authority;

}
