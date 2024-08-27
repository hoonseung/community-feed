package com.backend.post.repository.entity.post;

import com.backend.post.domain.cotent.PostPublicationState;
import jakarta.persistence.AttributeConverter;

public class PostPublicationStatConverter implements
    AttributeConverter<PostPublicationState, String> {


    @Override
    public String convertToDatabaseColumn(PostPublicationState state) {
        return state.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String s) {
        return PostPublicationState.valueOf(s);
    }
}
