package com.backend.post.repository.jpa;

import com.backend.post.repository.entity.comment.QCommentEntity;
import com.backend.post.repository.entity.post.QPostEntity;
import com.backend.post.ui.dto.GetCommentContentResponseDto;
import com.backend.user.repository.entity.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JpaCommentListQueryRepositoryImpl implements JpaCommentListQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QPostEntity postEntity = QPostEntity.postEntity;
    private final QCommentEntity commentEntity = QCommentEntity.commentEntity;
    private final QUserEntity userEntity = QUserEntity.userEntity;

    // 이 쿼리는 특정 게시글의 댓글을 가져오는 쿼리입니다.
    @Override
    public List<GetCommentContentResponseDto> getCommentsResponse(Long postId, Long lastCommentId) {
        return jpaQueryFactory.select(
                Projections.fields(
                    GetCommentContentResponseDto.class,
                    commentEntity.id.as("id"),
                    commentEntity.content.as("content"),
                    userEntity.id.as("userId"),
                    userEntity.userName.as("userName"),
                    userEntity.profileImage.as("userProfileImage"),
                    commentEntity.createdAt.as("createdAt"),
                    commentEntity.modifiedAt.as("modifiedAt"),
                    commentEntity.likeCount.as("likeCount")
                ))
            .from(commentEntity)
            .join(userEntity).on(commentEntity.author.id.eq(userEntity.id))
            .where(
                commentEntity.post.id.eq(postId),
                hasLastData(lastCommentId)
            ).orderBy(commentEntity.id.desc())
            .limit(20)
            .fetch();
    }


    private BooleanExpression hasLastData(Long lastCommentId) {
        if (lastCommentId == null) {
            return null;
        }
        return commentEntity.id.lt(lastCommentId);
    }
}
