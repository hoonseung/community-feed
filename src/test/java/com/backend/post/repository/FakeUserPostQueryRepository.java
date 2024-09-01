package com.backend.post.repository;

import com.backend.post.repository.post_queue.UserPostQueueQueryRepository;
import com.backend.post.ui.dto.GetPostContentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Profile("test")
@Repository
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;

    @Override
    public List<GetPostContentResponseDto> getPostsResponse(Long useId, Long lastPostId) {
        return fakeUserQueueRedisRepository.getPostsByUserId(useId)
            .stream()
            .filter(postEntity -> lastPostId == null || postEntity.getId() < lastPostId)
            .map(GetPostContentResponseDto::from)
            .toList();
    }
}
