package com.backend.user.application.dto;

public record UnFollowUserRequestDto(
    Long userId,
    Long targetUserId
) {

}
