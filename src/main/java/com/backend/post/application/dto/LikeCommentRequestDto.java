package com.backend.post.application.dto;

public record LikeCommentRequestDto(
    Long userId,
    Long commentId
) {

}
