package com.sparta.trelloproject.domain.comment.service;

import com.sparta.trelloproject.common.annotation.AuthUser;
import com.sparta.trelloproject.common.exception.NotFoundException;
import com.sparta.trelloproject.common.exception.UnauthorizedException;
import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.card.repository.CardRepository;
import com.sparta.trelloproject.domain.comment.dto.request.SaveCommentRequest;
import com.sparta.trelloproject.domain.comment.dto.request.UpdateCommentRequest;
import com.sparta.trelloproject.domain.comment.dto.response.SaveCommentResponse;
import com.sparta.trelloproject.domain.comment.dto.response.UpdateCommentResponse;
import com.sparta.trelloproject.domain.comment.entity.Comment;
import com.sparta.trelloproject.domain.comment.repository.CommentRepository;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.trelloproject.common.exception.ResponseCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    //댓글 등록
    @Transactional
    public SaveCommentResponse saveComment(AuthUser authUser,Long cardId, SaveCommentRequest saveCommentRequest) {
        User user=userRepository.findById(authUser.getUserId()).orElseThrow(()->
                new NotFoundException(NOT_FOUND_USER));

        //card가 존재하는 지 확인
        Card card=cardRepository.findById(cardId).orElseThrow(()->
                new NotFoundException(NOT_FOUND_CARD));

        Comment newComment=Comment.from(saveCommentRequest,user,card);
        commentRepository.save(newComment);

        return SaveCommentResponse.from(newComment);
    }

    //댓글 수정
    @Transactional
    public UpdateCommentResponse updateComment(AuthUser authUser,Long cardId, Long commentId, @Valid UpdateCommentRequest updateCommentRequest) {
        //댓글이 존재하는 지 확인
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->
                new NotFoundException(NOT_FOUND_COMMENT));

        //댓글을 작성한 유저가 맞는 지 확인
        if(!authUser.getUserId().equals(comment.getUser().getId())){
            throw new UnauthorizedException(INVALID_USER_AUTHORITY);
        }
        comment.update(updateCommentRequest);

        return UpdateCommentResponse.from(comment);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(AuthUser authUser,Long cardId, Long commentId) {
        //댓글이 존재하는 지 확인
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->
                new NotFoundException(NOT_FOUND_COMMENT));

        //댓글을 작성한 유저가 맞는 지 확인
        if(!authUser.getUserId().equals(comment.getUser().getId())){
            throw new UnauthorizedException(INVALID_USER_AUTHORITY);
        }

        commentRepository.deleteById(commentId);
    }


}
