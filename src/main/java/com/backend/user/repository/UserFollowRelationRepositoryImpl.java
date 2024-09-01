package com.backend.user.repository;

import com.backend.post.repository.post_queue.UserPostQueueCommandRepository;
import com.backend.user.application.interfaces.UserFollowRelationRepository;
import com.backend.user.domain.User;
import com.backend.user.repository.entity.UserEntity;
import com.backend.user.repository.entity.UserFollowRelationEntity;
import com.backend.user.repository.entity.UserFollowRelationIdEntity;
import com.backend.user.repository.jpa.JpaUserFollowRelationRepository;
import com.backend.user.repository.jpa.JpaUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserFollowRelationRepositoryImpl implements UserFollowRelationRepository {

    private final JpaUserFollowRelationRepository jpaUserFollowRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;


    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserFollowRelationIdEntity id = new UserFollowRelationIdEntity(
            user.getId(), targetUser.getId());
        return jpaUserFollowRelationRepository.existsById(id);
    }

    @Transactional
    @Override
    public void save(User user, User targetUser) {
        jpaUserFollowRelationRepository.save(
            new UserFollowRelationEntity(user.getId(), targetUser.getId()));
        jpaUserRepository.saveAll(List.of(
            UserEntity.createUserEntity(user),
            UserEntity.createUserEntity(targetUser))
        );

        userPostQueueCommandRepository.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Transactional
    @Override
    public void delete(User user, User targetUser) {
        jpaUserFollowRelationRepository.deleteById(new UserFollowRelationIdEntity(user.getId(),
            targetUser.getId()));
        jpaUserRepository.saveAll(List.of(
            UserEntity.createUserEntity(user),
            UserEntity.createUserEntity(targetUser))
        );

        userPostQueueCommandRepository.deleteUnfollowPost(user.getId(), targetUser.getId());
    }
}
