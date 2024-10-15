package com.sparta.trelloproject.domain.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.trelloproject.common.annotation.AuthUser;
import com.sparta.trelloproject.common.config.JwtUtil;
import com.sparta.trelloproject.domain.comment.dto.request.SaveCommentRequest;
import com.sparta.trelloproject.domain.comment.dto.response.SaveCommentResponse;
import com.sparta.trelloproject.domain.comment.entity.Comment;
import com.sparta.trelloproject.domain.comment.service.CommentService;
import com.sparta.trelloproject.domain.user.entity.User;
import com.sparta.trelloproject.domain.user.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 댓글_저장_성공() throws Exception{
        //given
        AuthUser authUser=new AuthUser(1L,"test@email.com", UserRole.ROLE_USER);
        Long cardId=1L;
        SaveCommentRequest request=new SaveCommentRequest("댓글입니다");
        SaveCommentResponse response=new SaveCommentResponse();

        Mockito.when(commentService.saveComment(any(AuthUser.class),eq(cardId), any(SaveCommentRequest.class)))
                .thenReturn(response);

        //when
        ResultActions result = mockMvc.perform(post("/api/v1/cards/{cardId}/comment", cardId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        result.andExpect(status().isOk());
    }

}