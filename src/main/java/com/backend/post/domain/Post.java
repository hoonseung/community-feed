package com.backend.post.domain;

import com.backend.common.domain.PositiveIntegerCounter;
import com.backend.post.domain.cotent.Content;
import com.backend.post.domain.cotent.PostContent;
import com.backend.post.domain.cotent.PostPublicationState;
import com.backend.user.domain.User;
import lombok.Getter;

public class Post {

    @Getter
    private Long id;
    @Getter
    private final User author;
    @Getter
    private final Content content;
    private final PositiveIntegerCounter likeCounter;
    private PostPublicationState state;


    public static Post createPost(User user, String content, PostPublicationState state) {
        return new Post(null, user, new PostContent(content), state);
    }

    public static Post createPost(Long id, User user, String content, PostPublicationState state) {
        return new Post(id, user, new PostContent(content), state);
    }


    public Post(Long id, User author, Content content, PostPublicationState state) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCounter = new PositiveIntegerCounter();
        this.state = state;
    }


    public void like(User user) {
        if (user.equals(this.author)) {
            throw new IllegalArgumentException("You can't like self");
        }
        this.likeCounter.increment();
    }

    public void dislike(User user) {
        if (user.equals(this.author)) {
            throw new IllegalArgumentException("You can't unlike self");
        }
        this.likeCounter.decrement();
    }

    public void updatePost(User user, String updateContent, PostPublicationState state) {
        if (!user.equals(this.author)) {
            throw new IllegalArgumentException("does not author");
        }
        this.content.updateContent(updateContent);
        this.state = state;
    }

    public int getCount() {
        return this.likeCounter.getCount();
    }

}
