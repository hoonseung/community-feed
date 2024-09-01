package com.backend.user.repository.jpa;

import com.backend.user.repository.entity.UserFollowRelationEntity;
import com.backend.user.repository.entity.UserFollowRelationIdEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserFollowRelationRepository extends
    JpaRepository<UserFollowRelationEntity, UserFollowRelationIdEntity> {

    @Query("select r.followerUserId from UserFollowRelationEntity r where r.followingUserId = :userId")
    List<Long> findAllFollowers(Long userId);

}
