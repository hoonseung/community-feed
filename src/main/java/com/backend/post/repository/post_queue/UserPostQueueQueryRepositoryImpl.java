package com.backend.post.repository.post_queue;

import com.backend.post.repository.entity.like.QLikeRelationEntity;
import com.backend.post.repository.entity.post.QPostEntity;
import com.backend.post.repository.entity.post.QUserPostQueueEntity;
import com.backend.post.ui.dto.GetPostContentResponseDto;
import com.backend.user.repository.entity.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QPostEntity postEntity = QPostEntity.postEntity;
    private final QUserEntity userEntity = QUserEntity.userEntity;
    private final QLikeRelationEntity likeRelationEntity = QLikeRelationEntity.likeRelationEntity;
    private final QUserPostQueueEntity qUserPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;

    // 이 쿼리는 특정 사용자가 팔로우하는 유저의 피드를 가져오는 쿼리입니다.
    @Override
    public List<GetPostContentResponseDto> getPostsResponse(Long useId, Long lastPostId) {
        return jpaQueryFactory.select(
                Projections.fields(
                    GetPostContentResponseDto.class,
                    postEntity.id.as("id"),
                    postEntity.content.as("content"),
                    userEntity.id.as("userId"),
                    userEntity.userName.as("userName"),
                    userEntity.profileImage.as("userProfileImage"),
                    postEntity.createdAt.as("createdAt"),
                    postEntity.modifiedAt.as("modifiedAt"),
                    postEntity.likeCount.as("likeCount"),
                    likeRelationEntity.isNotNull().as("isLikeByMe"), // left join으로 인해 null이면 false null이 아니면 true 반환
                    postEntity.commentCount.as("commentCount")

                ))
            .from(qUserPostQueueEntity)
            .join(postEntity).on(qUserPostQueueEntity.postId.eq(postEntity.id))
            .join(userEntity).on(qUserPostQueueEntity.authorId.eq(userEntity.id))
            .leftJoin(likeRelationEntity).on(hasLikeByMe(useId))
            .where(
                qUserPostQueueEntity.userId.eq(useId),
                hasLastData(lastPostId)
            ).orderBy(qUserPostQueueEntity.postId.desc())
            .limit(20)
            .fetch();
    }


    // 좋아요 여부를 확인하는 쿼리
    private BooleanExpression hasLikeByMe(Long userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        return likeRelationEntity.id.targetId.eq(postEntity.id)
            .and(likeRelationEntity.id.targetType.eq("POST"))
            .and(likeRelationEntity.id.userId.eq(userId));
    }

    // 마지막 데이터를 가져오는 쿼리
    private BooleanExpression hasLastData(Long lastId) {
        if (Objects.isNull(lastId)) {
            return null;
        }
        return postEntity.id.lt(lastId); // lastId보다 작은 데이터만 가져옴
    }
}
