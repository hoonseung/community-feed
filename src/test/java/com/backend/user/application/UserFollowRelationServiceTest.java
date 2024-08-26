package com.backend.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.backend.fake.FakeObjectFactory;
import com.backend.user.application.dto.FollowUserRequestDto;
import com.backend.user.application.dto.UnFollowUserRequestDto;
import com.backend.user.application.dto.UserDto;
import com.backend.user.application.interfaces.UserFollowRelationRepository;
import com.backend.user.domain.User;
import com.backend.user.domain.UserInfo;
import com.backend.user.repository.FakeFollowRelationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[유저 팔로우 서비스 테스트]")
class UserFollowRelationServiceTest {


    private final UserService userService = FakeObjectFactory.userService();
    private final UserFollowRelationService userFollowRelationService = FakeObjectFactory.userFollowRelationService();

    private User user1;
    private User user2;
    private FollowUserRequestDto followUserRequestDto;
    private FollowUserRequestDto followSelfUserRequestDto;
    private UnFollowUserRequestDto unFollowUserRequestDto;
    private UnFollowUserRequestDto unFollowSelfUserRequestDto;

    @BeforeEach
    void init() {
        user1 = userService.createUser(UserDto.of(null, new UserInfo("홍길동", "")));
        user2 = userService.createUser(UserDto.of(null, new UserInfo("이순신", "")));
        followUserRequestDto =  new FollowUserRequestDto(user1.getId(), user2.getId());
        followSelfUserRequestDto = new FollowUserRequestDto(user1.getId(), user1.getId());
        unFollowUserRequestDto = new UnFollowUserRequestDto(user1.getId(), user2.getId());
        unFollowSelfUserRequestDto = new UnFollowUserRequestDto(user1.getId(), user1.getId());
    }


    @DisplayName("유저가 팔로우하면 팔로우 관계 객체가 정상적으로 저장된다.")
    @Test
    void givenUser_whenFollowingUser_thenReturnUser() {
        //given

        //when
        userFollowRelationService.follow(followUserRequestDto);

        //then
        System.out.println(user1.getFollowingCount());
        System.out.println(user2.getFollowerCount());
        assertEquals(1, user1.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @DisplayName("유저가 이미 팔로우한 유저를 팔로우 할 때 예외를 발생시킨다.")
    @Test
    void givenUser_whenFollowingAlreadyFollowingUser_thenThrowException() {
        //given
        userFollowRelationService.follow(followUserRequestDto);
        //when & then
        assertThrows(IllegalArgumentException.class,
            () -> userFollowRelationService.follow(followUserRequestDto),
            "already following");
    }

    @DisplayName("유저가 자기 자신을 팔로우 할 때 예외를 발생시킨다.")
    @Test
    void givenUser_whenFollowingSelf_thenThrowException() {
        //given
        //when & then
        assertThrows(IllegalArgumentException.class,
            () -> userFollowRelationService.follow(followSelfUserRequestDto),
            "Cannot follow self");
    }

    @DisplayName("유저가 언팔로우하면 팔로우 관계가 삭제된다.")
    @Test
    void givenUser_whenUnFollowingUser_thenFollowRelationDeleted() {
        //given
        userFollowRelationService.follow(followUserRequestDto);
        //when
        userFollowRelationService.unFollow(unFollowUserRequestDto);

        //then
        System.out.println(user1.getFollowingCount());
        System.out.println(user2.getFollowerCount());
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowingCount());

    }

    @DisplayName("유저가 자기 자신을 언팔로우 할 때 예외를 발생시킨다.")
    @Test
    void givenUser_whenUnFollowingSelf_thenThrowException() {
        //given
        userFollowRelationService.follow(followUserRequestDto);
        //when & then
        assertThrows(IllegalArgumentException.class,
            () -> userFollowRelationService.unFollow(unFollowSelfUserRequestDto),
            "Cannot unfollow self");
    }

}