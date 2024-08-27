package com.backend.user.repository.entity;

import com.backend.common.repository.TimeBaseEntity;
import com.backend.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "community_user")
@Entity
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;


    public static UserEntity createUser(User user) {
        return new UserEntity(
            null,
            user.getName(),
            user.getImageUrl(),
            user.getFollowerCount(),
            user.getFollowingCount()
        );
    }


    public User toUser() {
        return User.createUser(
            id,
            name,
            profileImage,
            followingCount,
            followerCount
        );
    }
}
