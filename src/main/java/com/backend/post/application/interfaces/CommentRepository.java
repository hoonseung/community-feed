package com.backend.post.application.interfaces;

import com.backend.post.domain.comment.Comment;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
