package com.sparta.trelloproject.domain.card.entity;

import com.sparta.trelloproject.common.entity.Timestamped;
import com.sparta.trelloproject.domain.card.dto.request.CardRequestDto;
import com.sparta.trelloproject.domain.card.dto.request.CardStatusRequestDto;
import com.sparta.trelloproject.domain.card.enums.CardStatus;
import com.sparta.trelloproject.domain.list.entity.Lists;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cards")
@Getter
@NoArgsConstructor
public class Card extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    private String contents;
    private LocalDateTime dueDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @ManyToOne
    @JoinColumn(name = "lists_id")
    private Lists list;

    private Card(String title, String contents, LocalDateTime dueDate, CardStatus cardStatus,
        Lists list) {
        this.title = title;
        this.contents = contents;
        this.dueDate = dueDate;
        this.cardStatus = cardStatus;
        this.list = list;
    }

    public static Card from(CardRequestDto cardRequestDto, Lists list) {
        CardStatus cardStatus = CardStatus.valueOf(cardRequestDto.getCardStatus().toUpperCase());
        return new Card(cardRequestDto.getTitle(), cardRequestDto.getContents(),
            cardRequestDto.getDueDate(), cardStatus, list);
    }

    public void update(CardRequestDto cardRequestDto) {
        this.title = cardRequestDto.getTitle();
        this.contents = cardRequestDto.getContents();
        this.dueDate = cardRequestDto.getDueDate();
    }

    public void updateStatus(CardStatusRequestDto cardStatusRequestDto) {
        CardStatus cardStatus = CardStatus.valueOf(
            cardStatusRequestDto.getCardStatus().toUpperCase());
        this.cardStatus = cardStatus;
    }

    public Card(String title, Lists list) {
        this.title = title;
        this.list = list;
    }
}
