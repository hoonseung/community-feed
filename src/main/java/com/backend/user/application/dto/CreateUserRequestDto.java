package com.backend.user.application.dto;

import com.backend.user.domain.UserInfo;

public record CreateUserRequestDto(
    String name,
    String profileImageUrl
) {


    public UserDto toUserDto() {
        return UserDto.of(null, new UserInfo(name, profileImageUrl));
    }
}
