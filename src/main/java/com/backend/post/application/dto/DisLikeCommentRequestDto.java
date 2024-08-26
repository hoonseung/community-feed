package com.backend.post.application.dto;

public record DisLikeCommentRequestDto(
    Long userId,
    Long commentId
) {

}
