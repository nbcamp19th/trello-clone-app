package com.sparta.trelloproject.domain.card.repository;

import com.sparta.trelloproject.domain.card.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    boolean existsByUserIdAndCardId(Long id, Long id1);
}
