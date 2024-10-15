package com.sparta.trelloproject.domain.card.entity;

import com.sparta.trelloproject.common.entity.Timestamped;
import com.sparta.trelloproject.domain.card.dto.request.CardRequestDto;
import com.sparta.trelloproject.domain.list.entity.Lists;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
@Getter
public class Card extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    private String contents;
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "lists_id")
    private Lists list;

    private Card(String title, String contents, LocalDateTime dueDate,Lists list)
    {
        this.title = title;
        this.contents = contents;
        this.dueDate = dueDate;
        this.list = list;
    }

    public static Card from(CardRequestDto cardRequestDto, Lists list) {
        return new Card(cardRequestDto.getTitle(), cardRequestDto.getContents(), cardRequestDto.getDueDate(), list);
    }
}
