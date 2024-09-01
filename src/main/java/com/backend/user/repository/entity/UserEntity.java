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
import org.hibernate.annotations.DynamicUpdate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "community_user")
@Entity
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;


    public static UserEntity createUserEntity(User user) {
        return new UserEntity(
            user.getId() == null ? null : user.getId(),
            user.getName(),
            user.getImageUrl(),
            user.getFollowerCount(),
            user.getFollowingCount()
        );
    }


    public User toUser() {
        return User.createUser(
            id,
            userName,
            profileImage,
            followerCount,
            followingCount
        );
    }
}
