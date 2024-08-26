package com.backend.post.domain.cotent;

public class PostContent extends Content {

    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 500;

    public PostContent(String content) {
        super(content);
    }


    @Override
    public void checkText(String contentText) {
        if (contentText == null) {
            throw new IllegalArgumentException("Content text cannot be null");
        }
        if (contentText.length() < MIN_LENGTH || contentText.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                "Content length must be between 5 and 500 characters");
        }
    }
}
