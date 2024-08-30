package com.backend.post.repository;

import com.backend.post.application.interfaces.LikeRepository;
import com.backend.post.domain.Post;
import com.backend.post.domain.comment.Comment;
import com.backend.post.repository.entity.comment.CommentEntity;
import com.backend.post.repository.entity.like.LikeRelationEntity;
import com.backend.post.repository.entity.like.LikeRelationIdEntity;
import com.backend.post.repository.entity.like.LikeTarget;
import com.backend.post.repository.entity.post.PostEntity;
import com.backend.post.repository.jpa.JpaCommentRepository;
import com.backend.post.repository.jpa.JpaLikeRepository;
import com.backend.post.repository.jpa.JpaPostRepository;
import com.backend.user.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class LikeRepositoryImpl implements LikeRepository {

    @PersistenceContext
    private final EntityManager em;

    private final JpaLikeRepository jpaLikeRepository;
    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;


    @Override
    public boolean checkLike(Post post, User user) {
        return jpaLikeRepository.existsById(
            new LikeRelationIdEntity(post.getId(), user.getId(), LikeTarget.POST.name()));
    }

    @Transactional
    @Override
    public void save(Post post, User user) {
        PostEntity postEntity = PostEntity.createPostEntity(post);
        em.persist(
            LikeRelationEntity.createPostLikeRelationEntity(post.getId(), user.getId()));
        jpaPostRepository.updatePostLikeCount(postEntity);
    }

    @Transactional
    @Override
    public void delete(Post post, User user) {
        PostEntity postEntity = PostEntity.createPostEntity(post);
        jpaLikeRepository.deleteById(
            LikeRelationEntity.createPostLikeRelationEntity(post.getId(), user.getId()).getId());
        jpaPostRepository.updatePostLikeCount(postEntity);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        return jpaLikeRepository.existsById(
            new LikeRelationIdEntity(comment.getId(), user.getId(), LikeTarget.COMMENT.name()));
    }

    @Transactional
    @Override
    public void save(Comment comment, User user) {
        CommentEntity commentEntity = CommentEntity.createComment(comment);
        jpaLikeRepository.save(
            LikeRelationEntity.createCommentLikeRelationEntity(comment.getId(), user.getId()));
        jpaCommentRepository.updateCommentLikeCount(commentEntity);
    }

    @Transactional
    @Override
    public void delete(Comment comment, User user) {
        CommentEntity commentEntity = CommentEntity.createComment(comment);
        jpaLikeRepository.deleteById(
            LikeRelationEntity.createCommentLikeRelationEntity(comment.getId(), user.getId())
                .getId());
        jpaCommentRepository.updateCommentLikeCount(commentEntity);
    }
}
