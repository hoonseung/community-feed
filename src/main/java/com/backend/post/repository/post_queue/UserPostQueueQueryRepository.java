package com.backend.post.repository.post_queue;

import com.backend.post.ui.dto.GetPostContentResponseDto;
import java.util.List;

public interface UserPostQueueQueryRepository {

    List<GetPostContentResponseDto> getPostsResponse(Long useId, Long lastPostId);
}
