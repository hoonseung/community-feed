package com.backend.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.backend.fake.FakeObjectFactory;
import com.backend.user.application.dto.UserDto;
import com.backend.user.domain.User;
import com.backend.user.domain.UserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[유저 서비스 테스트]")
class UserServiceTest {


    private final UserService userService = FakeObjectFactory.userService();


    @DisplayName("유저 생성 시 정상적으로 저장 후 반환한다.")
    @Test
    void givenUser_whenCreatingUser_thenReturnUser() {
        //given
        UserDto dto = UserDto.of(null, new UserInfo("홍길동", ""));

        //when
        User result = userService.createUser(dto);

        //then
        User found = userService.getUser(result.getId());
        assertEquals(result.getId(), found.getId());
        assertEquals(result.getUserInfo(), found.getUserInfo());
    }

}