package com.backend.acceptance;

import com.backend.acceptance.steps.FeedAcceptanceSteps;
import com.backend.acceptance.utils.AcceptanceTestTemplate;
import com.backend.post.application.dto.CreatePostRequestDto;
import com.backend.post.domain.cotent.PostPublicationState;
import com.backend.post.ui.dto.GetPostContentResponseDto;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /**
     * User1 follow -> User2 User1 follow -> User3
     */


    @BeforeEach
    void setUp() {
        super.testBefore();
    }

    /**
     * User2 create Post 1 User1 get Post from User2 Post
     */

    @Test
    void whenUserCreatePost_thenUsersFollowersCanGetUserFeeds() {
        //given
        var postDto = new CreatePostRequestDto(2L, "User1 is can get User2 Post ",
            PostPublicationState.PUBLIC);
        FeedAcceptanceSteps.requestCreatePost(postDto);

        //when
        List<GetPostContentResponseDto> result = FeedAcceptanceSteps.requestGetFeeds(
            1L);

        //then
        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result.get(0).getContent()).isEqualTo("User1 is can get User2 Post ");
    }
}
