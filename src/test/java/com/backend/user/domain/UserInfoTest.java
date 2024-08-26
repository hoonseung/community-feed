package com.backend.user.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[유저 정보 테스트]")
class UserInfoTest {


    @DisplayName("유저를 생성하면 예외가 발생하지 않고 생성된다.")
    @Test
    void whenUserCreating_thenDoNotThrowException() {
        assertDoesNotThrow(() -> new User(1L, new UserInfo("홍길동", null)));
    }

    @DisplayName("유저를 생성할 때 사용자 이름이 없으면 예외가 발생한다.")
    @Test
    void whenUserCreatingWithOutName_thenThrowException() {
        //given & when & then
        assertThrows(IllegalArgumentException.class, () -> new User(1L, new UserInfo(null, null)),
            "Name cannot be null or empty");
    }


}