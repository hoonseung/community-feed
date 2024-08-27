package com.backend.user.repository.jpa;

import com.backend.user.application.dto.GetUserListResponseDto;
import com.backend.user.repository.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query("select new com.backend.user.application.dto.GetUserListResponseDto(u.name, u.profileImage)"
        + " from UserEntity u inner join UserFollowRelationEntity f on u.id = f.followingUserId where f.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserListById(@Param("userId") Long userId);

    @Query("select new com.backend.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
        + "from UserEntity u inner join UserFollowRelationEntity f on u.id = f.followerUserId where f.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserListById(@Param("userId") Long userId);

}
