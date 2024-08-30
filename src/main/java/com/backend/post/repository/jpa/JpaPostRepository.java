package com.backend.post.repository.jpa;

import com.backend.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {


    @Modifying
    @Query("update PostEntity p set p.content = :#{#postEntity.getContent()}, p.state = :#{#postEntity.getState()}, p.modifiedAt = now() where p.id = :#{#postEntity.getId()}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query("update PostEntity p set p.likeCount = :#{#jpaPostRepository}, p.modifiedAt = now() where p.id = :#{#postEntity.getId()}")
    void updatePostLikeCount(PostEntity postEntity);
}
