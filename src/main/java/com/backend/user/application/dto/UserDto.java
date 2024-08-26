package com.backend.user.application.dto;

import com.backend.user.domain.User;
import com.backend.user.domain.UserInfo;

public record UserDto(
    Long id,
    UserInfo userInfo
) {


    public static UserDto of(Long id, UserInfo userInfo) {
        return new UserDto(id, userInfo);
    }

    public User toUser() {
        return new User(null, userInfo);
    }
}
