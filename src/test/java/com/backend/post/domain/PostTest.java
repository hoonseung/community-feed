package com.backend.post.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.backend.post.domain.cotent.PostPublicationState;
import com.backend.user.domain.User;
import com.backend.user.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Post 테스트]")
class PostTest {


    private User user1;
    private User user2;
    private Post post;

    @BeforeEach
    void init() {
        user1 = new User(1L, new UserInfo("홍길동", ""));
        user2 = new User(2L, new UserInfo("이순신", ""));
        post = Post.createPost(1L, user1, "content", PostPublicationState.PUBLIC);
    }


    @DisplayName("다른 사용자가 내 게시글을 좋아요를 하면 내 게시글 좋아요 카운트가 증가해야한다.")
    @Test
    void whenAnotherUserMyPostLiked_thenShouldPostLikeCountIncreased() {
        //given

        //when
        post.like(user2);

        //then
        assertEquals(1, post.getCount());
    }

    @DisplayName("사용자 자신의 게시글을 좋아요를 하면 예외가 발생해야한다..")
    @Test
    void whenSelfMarkedLikePost_thenShouldThrowException() {
        //given

        //when & then
        assertThrows(IllegalArgumentException.class, () -> post.like(user1),
            "You can't like self");
    }

    @DisplayName("다른 사용자가 내 게시글을 싫어요를 하면 내 게시글 좋아요 카운트가 감소해야한다.")
    @Test
    void whenAnotherUserMyPostDisliked_thenShouldPostLikeCountDecreased() {
        //given

        post.like(user2);
        //when
        post.dislike(user2);

        //then
        assertEquals(0, post.getCount());
    }

    @DisplayName("사용자 자신의 게시글을 싫어요를 하면 예외가 발생해야한다..")
    @Test
    void whenSelfMarkedDislikePost_thenShouldThrowException() {
        //given

        //when & then
        assertThrows(IllegalArgumentException.class, () -> post.dislike(user1),
            "You can't unlike self");
    }

    @DisplayName("사용자 게시글을 싫어요를 할때 카운트가 0이하면 예외가 발생해야한다.")
    @Test
    void whenDislikingUserPost_thenIfPostLikeCountIsLessThan0ShouldBeThrowException() {
        //given

        //when & then
        assertThrows(IllegalArgumentException.class, () -> post.dislike(user2),
            "Count cannot be negative");
    }

    @DisplayName("다른 사용자가 게시글을 수정하려고 하면 예외를 발생시킨다.")
    @Test
    void whenAnotherUserUpdatingMyPost_thenShouldThrowException() {
        //given
        String updateContent = "update content";
        //when & then
        assertThrows(IllegalArgumentException.class,
            () -> post.updatePost(user2, updateContent, PostPublicationState.PUBLIC),
            "does not author");
    }

}