package com.backend.post.application.dto;

public record DisLikePostRequestDto(
    Long userId,
    Long postId
) {

}
