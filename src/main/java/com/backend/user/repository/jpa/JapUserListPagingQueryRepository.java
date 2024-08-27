package com.backend.user.repository.jpa;

import com.backend.user.application.dto.GetUserListResponseDto;
import com.backend.user.repository.entity.QUserEntity;
import com.backend.user.repository.entity.QUserFollowRelationEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JapUserListPagingQueryRepository {


    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserFollowRelationEntity followRelation = QUserFollowRelationEntity.userFollowRelationEntity;


    public List<GetUserListResponseDto> getFollowerUser(Long userId, Long lastFollowerId){
        return jpaQueryFactory
            .select(
                Projections.fields(
                    GetUserListResponseDto.class
                )
            ).from(user)
            .innerJoin(followRelation)
            .on(user.id.eq(followRelation.followingUserId))
            .where(
                followRelation.followerUserId.eq(userId),
                hasLastData(lastFollowerId))
            .orderBy(user.id.desc())
            .limit(20)
            .fetch();
    }


    private BooleanExpression hasLastData(Long lastId){
        if (Objects.isNull(lastId)){
            return null;
        }
        return user.id.lt(lastId);
    }
}
