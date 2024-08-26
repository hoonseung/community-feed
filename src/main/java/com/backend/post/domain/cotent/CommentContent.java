package com.backend.post.domain.cotent;

public class CommentContent extends Content {

    private static final int MAX_LENGTH = 100;

    public CommentContent(String contentText) {
        super(contentText);
    }


    @Override
    public void checkText(String contentText) {
        if (contentText == null) {
            throw new IllegalArgumentException("Content text cannot be null");
        }
        if (contentText.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Content text is too long");
        }
    }
}
