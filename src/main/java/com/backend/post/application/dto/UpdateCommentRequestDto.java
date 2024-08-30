package com.backend.post.application.dto;

public record UpdateCommentRequestDto(
    Long userId,
    String content
) {


}
