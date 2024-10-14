package com.sparta.trelloproject.domain.card.repository;

import com.sparta.trelloproject.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long>, CardQueryRepository {
}
