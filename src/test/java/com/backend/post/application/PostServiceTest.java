package com.backend.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.backend.post.application.dto.DisLikePostRequestDto;
import com.backend.post.application.dto.LikePostRequestDto;
import com.backend.post.application.dto.UpdatePostRequestDto;
import com.backend.post.domain.Post;
import com.backend.post.domain.cotent.PostPublicationState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[Post Service 테스트]")
class PostServiceTest extends PostApplicationTestTemplate {


    @DisplayName("게시글이 정상적으로 생성된다.")
    @Test
    void givenCreatePostRequest_whenCreatingPost_thenShouldBeCreateSuccessFully() {
        //given

        //when
        Post post = postService.createPost(createPostRequestDto);

        //then
        Post expectPost = postService.getPost(post.getId());
        assertEquals(expectPost, post);
    }

    @DisplayName("게시글이 정상적으로 수정된다.")
    @Test
    void givenUpdatePostRequest_whenUpdatingPost_thenShouldBeUpdateSuccessFully() {
        //given
        String updateContent = "update content";
        Post post = postService.createPost(createPostRequestDto);
        UpdatePostRequestDto updatePostRequestDto = new UpdatePostRequestDto(user1.getId(),
            post.getId(), updateContent, PostPublicationState.PUBLIC);

        //when
        Post updatePost = postService.updatePost(updatePostRequestDto);

        //then
        assertEquals(updateContent, updatePost.getContent().getContentText());
    }

    @DisplayName("게시글 좋아요 요청이 주어지면 성공적 수행된다.")
    @Test
    void givenLikePostRequest_whenLikingPostOperation_thenShouldBeOperationSuccessFully() {
        //given
        Post post = postService.createPost(createPostRequestDto);
        LikePostRequestDto likePostRequestDto = new LikePostRequestDto(user2.getId(), post.getId());

        //when
        postService.likePost(likePostRequestDto);

        //then
        assertEquals(1, post.getLikeCount());
    }

    @DisplayName("게시글 싫어요 요청이 주어지면 성공적 수행된다.")
    @Test
    void givenDislikePostRequest_whenDislikingPostOperation_thenShouldBeOperationSuccessFully() {
        //given
        Post post = postService.createPost(createPostRequestDto);
        LikePostRequestDto likePostRequestDto = new LikePostRequestDto(user2.getId(), post.getId());
        DisLikePostRequestDto disLikePostRequestDto = new DisLikePostRequestDto(user2.getId(),
            post.getId());
        postService.likePost(likePostRequestDto);
        //when
        postService.dislikePost(disLikePostRequestDto);

        //then
        assertEquals(0, post.getLikeCount());
    }


}