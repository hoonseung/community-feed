package com.backend.post.repository.post_queue;

import com.backend.post.repository.entity.post.PostEntity;
import com.backend.post.repository.entity.post.UserPostQueueEntity;
import com.backend.post.repository.jpa.JpaPostRepository;
import com.backend.post.repository.jpa.JpaUserPostQueueRepository;
import com.backend.user.repository.entity.UserEntity;
import com.backend.user.repository.jpa.JpaUserFollowRelationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserFollowRelationRepository jpaUserFollowRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    // 게시글을 썼을 떄 모든 팔로워들이 볼 수 있도록 등록
    @Transactional
    @Override
    public void publishPost(PostEntity postEntity) {
        UserEntity author = postEntity.getAuthor();
        List<Long> followerIds = jpaUserFollowRelationRepository.findAllFollowers(author.getId());

        jpaUserPostQueueRepository.saveAll(
            followerIds.stream()
                .map(followerId -> UserPostQueueEntity.of(
                    followerId,
                    postEntity.getId(),
                    author.getId()
                ))
                .toList()
        );
    }

    // 팔로우하면 팔로우 한 사람의 작성글을 모두 볼 수 있도록 등록
    @Transactional
    @Override
    public void saveFollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.saveAll(jpaPostRepository.findAllByAuthorId(targetId)
            .stream()
            .map(postId ->
                UserPostQueueEntity.of(
                    userId,
                    postId,
                    targetId
                )
            )
            .toList());
    }


    @Transactional
    @Override
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteByUserIdAndAuthorId(userId, targetId);
    }
}
