package com.sparta.trelloproject.domain.comment.service;

import com.sparta.trelloproject.common.annotation.AuthUser;
import com.sparta.trelloproject.common.exception.ForbiddenException;
import com.sparta.trelloproject.common.exception.NotFoundException;
import com.sparta.trelloproject.common.exception.ResponseCode;
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
import com.sparta.trelloproject.domain.workspace.entity.UserWorkspace;
import com.sparta.trelloproject.domain.workspace.entity.Workspace;
import com.sparta.trelloproject.domain.workspace.enums.WorkSpaceUserRole;
import com.sparta.trelloproject.domain.workspace.repository.UserWorkSpaceRepository;
import com.sparta.trelloproject.domain.workspace.repository.WorkspaceRepository;
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
    private final WorkspaceRepository workspaceRepository;
    private final UserWorkSpaceRepository userWorkSpaceRepository;

    //댓글 등록
    @Transactional
    public SaveCommentResponse saveComment(AuthUser authUser,Long cardId, SaveCommentRequest saveCommentRequest) {
        User user=userRepository.findById(authUser.getUserId()).orElseThrow(()->
                new NotFoundException(NOT_FOUND_USER));

        //워크스페이스가 있는 지 확인
        Workspace workspace = workspaceRepository.findById(saveCommentRequest.getWorkspaceId())
                .orElseThrow(() -> new NotFoundException(ResponseCode.NOT_FOUND_WORKSPACE));

        //읽기 전용 유저인지 확인
        UserWorkspace userWorkspace = userWorkSpaceRepository.findByWorkspaceIdAndUserId(saveCommentRequest.getWorkspaceId(), authUser.getUserId());
        if (userWorkspace == null || userWorkspace.getWorkSpaceUserRole() == WorkSpaceUserRole.ROLE_READ_USER) {
            throw new ForbiddenException(ResponseCode.FORBIDDEN);
        }
        //card가 존재하는 지 확인
        Card card=cardRepository.findById(cardId).orElseThrow(()->
                new NotFoundException(NOT_FOUND_CARD));

        Comment newComment=Comment.from(saveCommentRequest,user,card);
        commentRepository.save(newComment);

        return SaveCommentResponse.from(newComment);
    }

    //댓글 수정
    @Transactional
    public UpdateCommentResponse updateComment(AuthUser authUser, Long commentId, @Valid UpdateCommentRequest updateCommentRequest) {
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
    public void deleteComment(AuthUser authUser, Long commentId) {
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
