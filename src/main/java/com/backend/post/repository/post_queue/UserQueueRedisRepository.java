package com.backend.post.repository.post_queue;

import com.backend.post.repository.entity.post.PostEntity;
import java.util.List;

public interface UserQueueRedisRepository {

    void publishPostToFollowingUsers(PostEntity postEntity, List<Long> userIds);

    void publishPostsToFollower(List<PostEntity> postEntities, Long userId);

    void deleteUserQueue(Long userId, Long authorId);

}
