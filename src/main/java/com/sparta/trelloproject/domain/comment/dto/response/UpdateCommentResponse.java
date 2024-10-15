package com.sparta.trelloproject.domain.comment.dto.response;

import com.sparta.trelloproject.domain.comment.entity.Comment;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UpdateCommentResponse {
    private Long commentId;
    private Long userId;
    private Long cardId;
    private String contents;
    private LocalDateTime modifiedAt;

    private UpdateCommentResponse(Long commentId,Long userId,Long cardId,String contents,LocalDateTime modifiedAt){
        this.commentId=commentId;
        this.userId=userId;
        this.cardId=cardId;
        this.contents=contents;
        this.modifiedAt=modifiedAt;
    }

    public static UpdateCommentResponse from(Comment comment){
        return new UpdateCommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getCard().getId(),
                comment.getContents(),
                comment.getModifiedAt()
        );
    }
}
