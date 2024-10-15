package com.sparta.trelloproject.domain.card.entity;

import com.sparta.trelloproject.domain.card.dto.request.CardImageRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card_image")
@Getter
@NoArgsConstructor
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

    private CardImage(String path, String fileName, String originName, Card card){
        this.path = path;
        this.fileName = fileName;
        this.originName = originName;
        this.card = card;
    }

    public static CardImage of(CardImageRequestDto cardImageDto, Card card) {
        return new CardImage(cardImageDto.getPath(), cardImageDto.getFileName(), cardImageDto.getOriginName(), card);
    }

    public void update(CardImageRequestDto cardImageDto) {
        this.path = cardImageDto.getPath();
        this.fileName = cardImageDto.getFileName();
        this.originName = cardImageDto.getOriginName();
    }
}
