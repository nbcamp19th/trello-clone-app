package com.sparta.trelloproject.domain.comment.repository;

import com.sparta.trelloproject.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentQueryRepository {
}
