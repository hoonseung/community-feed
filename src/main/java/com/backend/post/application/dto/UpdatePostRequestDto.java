package com.backend.post.application.dto;

import com.backend.post.domain.cotent.PostPublicationState;

public record UpdatePostRequestDto(
    Long userId,
    Long postId,
    String content,
    PostPublicationState state
) {

}
