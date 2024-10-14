package com.sparta.trelloproject.domain.user.repository;

import com.sparta.trelloproject.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserQueryRepository {
}
