package com.backend.post.application.dto;

public record CreateCommentRequestDto(
    Long postId,
    Long userId,
    String content
) {


}
