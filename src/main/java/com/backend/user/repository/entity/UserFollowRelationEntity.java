package com.backend.user.repository.entity;

import com.backend.common.repository.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserFollowRelationIdEntity.class)
@Table(name = "community_user_follow_relation")
@Entity
public class UserFollowRelationEntity extends TimeBaseEntity {

    @Id
    private Long followerUserId;

    @Id
    private Long followingUserId;



}
