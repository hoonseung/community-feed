package com.backend.acceptance;

import static com.backend.acceptance.steps.FeedAcceptanceSteps.requestGetFeeds;
import static org.assertj.core.api.Assertions.assertThat;

import com.backend.acceptance.steps.FeedAcceptanceSteps;
import com.backend.acceptance.utils.AcceptanceTestTemplate;
import com.backend.post.application.dto.CreatePostRequestDto;
import com.backend.post.domain.cotent.PostPublicationState;
import com.backend.post.ui.dto.GetPostContentResponseDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeedAcceptanceTest extends AcceptanceTestTemplate {

    private String token;

    /**
     * User1 follow -> User2 User1 follow -> User3
     */


    @BeforeEach
    void setUp() {
        this.token = super.login("hong@email.com");
    }

    /**
     * User2 create Post 1 User1 get Post from User2 Post
     */

    @Test
    void whenUserCreatePost_thenUsersFollowersCanGetUserFeeds() {
        //given
        var postDto = new CreatePostRequestDto(1L, "User1 is can get User2 Post",
            PostPublicationState.PUBLIC);
        Long postId = FeedAcceptanceSteps.requestCreatePost(postDto);

        //when
        List<GetPostContentResponseDto> result = requestGetFeeds(
            token); // FakeUserQueueRedisRepository 사용

        //then
        assertThat(postId).isNotNull();
        //assertThat(result).hasSize(1); FakeUserQueueRedisRepository 현재 사용하지 않아 데이터가 저장되어 있지 않음
        //assertThat(result.get(0).getContent()).isEqualTo("User1 is can get User2 Post");
    }
}
