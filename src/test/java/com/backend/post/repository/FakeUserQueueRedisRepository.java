package com.backend.post.repository;

import com.backend.post.repository.entity.post.PostEntity;
import com.backend.post.repository.post_queue.UserQueueRedisRepository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("test")
@Repository
public class FakeUserQueueRedisRepository implements UserQueueRedisRepository {

    private Map<Long, Set<PostEntity>> queue = new HashMap<>();

    @Override
    public void publishPostToFollowingUsers(PostEntity postEntity, List<Long> userIds) {
        for (Long userId : userIds) {
            if (queue.containsKey(userId)) {
                queue.get(userId).add(postEntity);
            } else {
                queue.put(userId, Set.of(postEntity));
            }
        }

    }

    @Override
    public void publishPostsToFollower(List<PostEntity> postEntities, Long userId) {
        if (queue.containsKey(userId)) {
            queue.get(userId).addAll(postEntities);
        } else {
            queue.put(userId, new HashSet<>(postEntities));

        }
    }

    @Override
    public void deleteUserQueue(Long userId, Long authorId) {
        if (queue.containsKey(userId)) {
            queue.get(userId)
                .removeIf(postEntity -> postEntity.getAuthor().getId().equals(authorId));
        }
    }

    public List<PostEntity> getPostsByUserId(Long userId){
        return List.copyOf(queue.get(userId));
    }
}
