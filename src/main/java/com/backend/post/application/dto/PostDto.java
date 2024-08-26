package com.backend.post.application.dto;

import com.backend.post.domain.cotent.Content;
import com.backend.user.domain.User;

public record PostDto(
    Long id,
    User author,
    Content content
) {


    public static PostDto of(Long id, User author, Content content) {
        return new PostDto(id, author, content);
    }

}
