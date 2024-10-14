package com.sparta.trelloproject.domain.card.entity;

import com.sparta.trelloproject.common.entity.Timestamped;
import com.sparta.trelloproject.domain.list.entity.Lists;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;

@Entity(name = "cards")
@Getter
public class Card extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="제목은 공백이 될 수 없습니다.")
    private String title;
    private String contents;
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "lists_id")
    private Lists list;
}
