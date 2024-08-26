package com.backend.post.repository;

import com.backend.post.application.interfaces.LikeRepository;
import com.backend.post.domain.Post;
import com.backend.post.domain.comment.Comment;
import com.backend.user.domain.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FakeLikeRepository implements LikeRepository {

    private final Map<Post, Set<User>> postLikeStore = new HashMap<>();
    private final Map<Comment, Set<User>> commentLikeStore = new HashMap<>();


    @Override
    public boolean checkLike(Post post, User user) {
        if (postLikeStore.get(post) == null) {
            return false;
        }

        return postLikeStore.get(post).contains(user);
    }

    @Override
    public void save(Post post, User user) {
        if (!postLikeStore.containsKey(post)) {
            Set<User> users = new HashSet<>();
            users.add(user);
            postLikeStore.put(post, users);
            return;
        }

        postLikeStore.get(post).add(user);
    }

    @Override
    public void delete(Post post, User user) {
        if (postLikeStore.containsKey(post)) {
            postLikeStore.get(post).remove(user);
        }
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        if (commentLikeStore.get(comment) == null) {
            return false;
        }

        return commentLikeStore.get(comment).contains(user);
    }

    @Override
    public void save(Comment comment, User user) {
        if (!commentLikeStore.containsKey(comment)) {
            Set<User> users = new HashSet<>();
            users.add(user);
            commentLikeStore.put(comment, users);
            return;
        }

        commentLikeStore.get(comment).add(user);
    }

    @Override
    public void delete(Comment comment, User user) {
        if (commentLikeStore.containsKey(comment)) {
            commentLikeStore.get(comment).remove(user);
        }
    }
}
