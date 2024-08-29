package com.backend.user.application.dto;

import com.backend.user.domain.User;

public record GetUserResponseDto(
    Long id,
    String name,
    String profileImage,
    Integer followerCount,
    Integer followingCount
) {

    public static GetUserResponseDto from(User user) {
        return new GetUserResponseDto(
            user.getId(),
            user.getName(),
            user.getImageUrl(),
            user.getFollowerCount(),
            user.getFollowingCount()
        );
    }
}
