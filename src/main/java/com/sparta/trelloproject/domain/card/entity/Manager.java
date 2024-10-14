package com.sparta.trelloproject.domain.card.entity;

import com.sparta.trelloproject.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "manager")
@Getter
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cards_id")
    private Card card;
}
