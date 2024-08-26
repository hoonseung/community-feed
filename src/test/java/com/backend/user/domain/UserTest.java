package com.backend.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[유저 도메인 테스트]")
class UserTest {

    private final UserInfo userInfo = new UserInfo("홍길동", "");


    @DisplayName("서로 식별자가 다른 유저들의 동등성 비교하면 거짓을 반환한다.")
    @Test
    void givenUsers_whenNotEqual_thenReturnFalse() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(2L, userInfo);

        //when & then
        assertFalse(user1.equals(user2));
    }

    @DisplayName("서로 식별자가 같은 유저들의 동등성 비교하면 참을 반환한다. ")
    @Test
    void givenUsers_whenEqual_thenReturnTrue() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(1L, userInfo);

        //when & then
        assertTrue(user1.equals(user2));
    }

    @DisplayName("서로 식별자가 다른 유저들의 해시코드를 비교하면 참을 반환한다. ")
    @Test
    void givenUsers_whenNotEqualHashCode_thenReturnFalse() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(2L, userInfo);

        //when & then
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @DisplayName("서로 식별자가 같은 유저들의 해시코드를 비교하면 참을 반환한다. ")
    @Test
    void givenUsers_whenEqualHashCode_thenReturnTrue() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(1L, userInfo);

        //when & then
        assertEquals(user1.hashCode(), user2.hashCode());
    }


    @DisplayName("유저가 다른 유저를 팔로우 하였을 때 유저의 팔로잉 수와 대상 유저의 팔로우 수가 증가해야한다.")
    @Test
    void whenUserFollowingTargetUser_thenUserFollowingAndTargetUserFollowerCountIncrease() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(2L, userInfo);

        //when
        user1.follow(user2);

        //then
        assertEquals(user1.getFollowingCount(), user2.getFollowerCount());
    }

    @DisplayName("유저가 다른 유저를 언팔로우 하였을 때 유저의 팔로잉 수와 대상 유저의 팔로우 수가 감소해야한다.")
    @Test
    void whenUserFollowingTargetUser_thenUserFollowingAndTargetUserFollowerCountDecrease() {
        //given
        User user1 = new User(1L, userInfo);
        User user2 = new User(2L, userInfo);

        //when
        int followingCount = user1.getFollowingCount();
        int followerCount = user2.getFollowerCount();
        user1.follow(user2);
        user1.unfollow(user2);


        //then
        assertEquals(followingCount, user1.getFollowingCount());
        assertEquals(followerCount, user2.getFollowerCount());
    }


}