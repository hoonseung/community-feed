package com.backend.user.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserFollowRelationIdEntity {

    private Long followerUserId;
    private Long followingUserId;


}
