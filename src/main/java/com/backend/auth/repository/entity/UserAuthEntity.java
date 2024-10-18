package com.backend.auth.repository.entity;

import com.backend.auth.domain.UserAuth;
import com.backend.common.repository.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "community_user_auth")
@Entity
public class UserAuthEntity extends TimeBaseEntity {

    @Id
    private String email;
    private String password;
    private String userRole;
    private Long userId;


    public static UserAuthEntity createUserAuthEntity(UserAuth userAuth, Long userId) {
        return new UserAuthEntity(
            userAuth.getEmail().getEmailText(),
            userAuth.getPassword().getEncryptedPasswordText(),
            userAuth.getUserRole().name(),
            userId
        );
    }

    public UserAuth toUserAuth() {
        return UserAuth.createUserAuth(
            email,
            password,
            userRole,
            userId
        );
    }

}
