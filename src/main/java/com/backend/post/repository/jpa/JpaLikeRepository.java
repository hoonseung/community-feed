package com.backend.post.repository.jpa;

import com.backend.post.repository.entity.like.LikeRelationEntity;
import com.backend.post.repository.entity.like.LikeRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaLikeRepository extends JpaRepository<LikeRelationEntity, LikeRelationIdEntity> {


    @Modifying
    @Query("delete from LikeRelationEntity l where  l.id = :likeId")
    void deleteByLikeId(LikeRelationIdEntity likeId);
}
