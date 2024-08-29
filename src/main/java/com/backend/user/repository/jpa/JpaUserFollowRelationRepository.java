package com.backend.user.repository.jpa;

import com.backend.user.repository.entity.UserFollowRelationEntity;
import com.backend.user.repository.entity.UserFollowRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserFollowRelationRepository extends
    JpaRepository<UserFollowRelationEntity, UserFollowRelationIdEntity> {

}
