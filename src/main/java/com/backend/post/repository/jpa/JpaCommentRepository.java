package com.backend.post.repository.jpa;

import com.backend.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query("update CommentEntity c set c.content = :#{#commentEntity.getContent()}, c.modifiedAt = now() where c.id = :#{#commentEntity.getId()}")
    void updateCommentEntity(CommentEntity commentEntity);

    @Modifying
    @Query("update CommentEntity c set c.likeCount = :#{#commentEntity.getLikeCount()}, c.modifiedAt = now() where c.id = :#{#commentEntity.getId()}")
    void updateCommentLikeCount(CommentEntity commentEntity);

}
