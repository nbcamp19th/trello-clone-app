package com.sparta.trelloproject.domain.list.repository;

import com.sparta.trelloproject.domain.list.entity.Lists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<Lists, Long>, ListQueryRepository {
}
