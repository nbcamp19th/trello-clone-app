package com.sparta.trelloproject.domain.card.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "card_image")
@Getter
public class CardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String path;

    @NotNull
    private String fileName;

    @NotNull
    private String originName;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
}
