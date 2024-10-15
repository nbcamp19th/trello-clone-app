package com.sparta.trelloproject.domain.comment.repository;

import com.sparta.trelloproject.domain.comment.entity.Comment;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentQueryRepository {

    List<Comment> findByCardId(Long id);

}
