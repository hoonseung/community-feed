package com.backend.post.repository.entity.like;

import com.backend.common.repository.TimeBaseEntity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "community_like_relation")
@Entity
public class LikeRelationEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeRelationIdEntity id;


    public static LikeRelationEntity createPostLikeRelationEntity(Long postId, Long userId) {
        return new LikeRelationEntity(
            new LikeRelationIdEntity(postId, userId, LikeTarget.POST.name()));
    }

    public static LikeRelationEntity createCommentLikeRelationEntity(Long commentId, Long userId) {
        return new LikeRelationEntity(
            new LikeRelationIdEntity(commentId, userId, LikeTarget.COMMENT.name()));
    }

}
