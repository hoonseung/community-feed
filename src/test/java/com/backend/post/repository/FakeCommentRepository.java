package com.backend.post.repository;

import com.backend.post.application.interfaces.CommentRepository;
import com.backend.post.domain.comment.Comment;
import java.util.HashMap;
import java.util.Map;

public class FakeCommentRepository implements CommentRepository {

    private final Map<Long, Comment> store = new HashMap<>();
    private static Long id = 1L;


    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            store.put(comment.getId(), comment);
            return comment;
        }

        Long commentId = id++;
        Comment newComment = new Comment(commentId, comment.getAuthor(), comment.getContent(),
            comment.getPost(), 0);

        store.put(commentId, newComment);
        return newComment;
    }

    @Override
    public Comment findById(Long id) {
        return store.get(id);
    }
}
