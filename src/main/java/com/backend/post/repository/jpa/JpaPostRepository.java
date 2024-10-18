package com.backend.post.repository.jpa;

import com.backend.post.repository.entity.post.PostEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {


    @Modifying
    @Query("update PostEntity p set p.content = :#{#postEntity.getContent()}, p.state = :#{#postEntity.getState()}, p.modifiedAt = now() where p.id = :#{#postEntity.getId()}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query("update PostEntity p set p.likeCount = :likeCount + p.likeCount, p.modifiedAt = now() where p.id = :postId")
    void updatePostLikeCount(Long postId, Integer likeCount);

    @Modifying
    @Query("update PostEntity p set p.commentCount = p.commentCount + 1 where p.id = :id")
    void increaseCommentCount(Long id);

    @Query("select p from PostEntity p where p.author.id = :id")
    List<PostEntity> findAllByAuthorId(Long id);

    @Query("select p.id from PostEntity p where p.author.id = :id")
    List<Long> findAllPostIdByAuthorId(Long id);
}
