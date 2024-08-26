package com.backend.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.backend.post.application.dto.DisLikeCommentRequestDto;
import com.backend.post.application.dto.LikeCommentRequestDto;
import com.backend.post.domain.comment.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Comment Service 테스트]")
class CommentServiceTest extends PostApplicationTestTemplate {


    @DisplayName("댓글이 정상적으로 생성된다.")
    @Test
    void givenCreateCommentRequest_whenCreatingComment_thenShouldBeCreateSuccessFully() {
        //given

        //when
        Comment comment = commentService.createComment(createCommentRequestDto);

        //then
        Comment expectComment = commentService.getComment(comment.getId());
        assertEquals(comment, expectComment);
    }

    @DisplayName("댓글이 정상적으로 수정된다.")
    @Test
    void givenUpdateCommentRequest_whenUpdatingComment_thenShouldBeUpdateSuccessFully() {
        //given
        String updateContent = "updated content";

        //when
        Comment updateComment = commentService.updateComment(updateCommentRequestDto);

        //then
        assertEquals(updateContent, updateComment.getContent().getContentText());
    }

    @DisplayName("댓글 좋아요 요청이 주어지면 성공적 수행된다.")
    @Test
    void givenLikeCommentRequest_whenLikingCommentOperation_thenShouldBeOperationSuccessFully() {
        //given
        LikeCommentRequestDto likeCommentRequestDto = new LikeCommentRequestDto(user1.getId(),
            comment.getId());

        //when
        commentService.like(likeCommentRequestDto);

        //then
        assertEquals(1, comment.getCount());
    }

    @DisplayName("댓글 싫어요 요청이 주어지면 성공적 수행된다.")
    @Test
    void givenDislikeCommentRequest_whenDislikingCommentOperation_thenShouldBeOperationSuccessFully() {
        //given
        LikeCommentRequestDto likeCommentRequestDto = new LikeCommentRequestDto(user1.getId(),
            comment.getId());
        commentService.like(likeCommentRequestDto);

        DisLikeCommentRequestDto disLikeCommentRequestDto = new DisLikeCommentRequestDto(
            user1.getId(), comment.getId());
        //when
        commentService.dislike(disLikeCommentRequestDto);

        //then
        assertEquals(0, comment.getCount());
    }
}