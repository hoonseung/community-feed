package com.backend.post.application.dto;

public record LikePostRequestDto(
    Long userId,
    Long postId
) {

}
