package com.backend.post.repository;

import com.backend.post.application.interfaces.CommentRepository;
import com.backend.post.domain.comment.Comment;
import com.backend.post.repository.entity.comment.CommentEntity;
import com.backend.post.repository.jpa.JpaCommentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;


    @Transactional
    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = CommentEntity.createComment(comment);
        if (Objects.isNull(commentEntity.getId())) {
            return jpaCommentRepository.save(commentEntity).toComment();
        }
        jpaCommentRepository.updateCommentEntity(commentEntity);
        return commentEntity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        return jpaCommentRepository.findById(id)
            .map(CommentEntity::toComment)
            .orElseThrow(() -> new EntityNotFoundException("comment not found"));

    }
}
