package com.sparta.trelloproject.domain.notification.entity;

import com.sparta.trelloproject.common.entity.Timestamped;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity(name = "notifications")
@Getter
public class Notification extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="제목은 공백이 될 수 없습니다.")
    private String content;

    @NotBlank(message="제목은 공백이 될 수 없습니다.")
    private Long targetId;

    @Enumerated(EnumType.STRING)
    private NotificationType targetType;
}
