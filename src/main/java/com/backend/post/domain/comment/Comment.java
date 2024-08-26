package com.backend.post.domain.comment;

import com.backend.common.domain.PositiveIntegerCounter;
import com.backend.post.domain.Post;
import com.backend.post.domain.cotent.CommentContent;
import com.backend.post.domain.cotent.Content;
import com.backend.user.domain.User;
import lombok.Getter;

public class Comment {

    @Getter
    private final Long id;
    @Getter
    private final User author;
    @Getter
    private final Content content;
    @Getter
    private final Post post;
    private final PositiveIntegerCounter likeCounter;


    public static Comment createComment(User author, String content, Post post) {
        return new Comment(null, author, new CommentContent(content), post);
    }


    public Comment(Long id, User author, Content content, Post post) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.post = post;
        this.likeCounter = new PositiveIntegerCounter();
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


    public void updateComment(User user, String updateContent) {
        if (!user.equals(this.author)) {
            throw new IllegalArgumentException("does not author");
        }
        this.content.updateContent(updateContent);
    }


    public int getCount() {
        return likeCounter.getCount();
    }
}
