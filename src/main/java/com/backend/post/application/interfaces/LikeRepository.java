package com.backend.post.application.interfaces;

import com.backend.post.domain.Post;
import com.backend.post.domain.comment.Comment;
import com.backend.user.domain.User;

public interface LikeRepository {

    boolean checkLike(Post post, User user);

    void save(Post post, User user);

    void delete(Post post, User user);

    boolean checkLike(Comment comment, User user);

    void save(Comment comment, User user);

    void delete(Comment comment, User user);
}
