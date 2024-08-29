package com.backend.user.application.dto;

import com.backend.user.domain.User;

public record CreateUserRequestDto(
    String name,
    String profileImageUrl
) {

    public User toUser() {
        return User.createUser(null, name, profileImageUrl);
    }
}
