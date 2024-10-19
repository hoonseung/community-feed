package com.backend.acceptance;

import static com.backend.acceptance.steps.LoginAcceptanceSteps.requestLoginAndGetCode;
import static com.backend.acceptance.steps.LoginAcceptanceSteps.requestLoginAndGetToken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.backend.acceptance.utils.AcceptanceTestTemplate;
import com.backend.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.Test;

class LoginAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "hong@email.com";


    @Test
    void givenEmailAndPassword_whenLogin_thenReturnAccessToken() {
        //given
        LoginRequestDto dto = new LoginRequestDto(email, "password");

        //when
        String token = requestLoginAndGetToken(dto);
        Integer code = requestLoginAndGetCode(dto);

        //then
        assertNotNull(token);
        assertEquals(0, code);
    }

    @Test
    void givenWrongEmail_whenLogin_thenReturnNotZero() {
        //given
        LoginRequestDto dto = new LoginRequestDto("wrong", "wrong password");

        //when
        Integer code = requestLoginAndGetCode(dto);

        //then
        assertEquals(400, code);
    }

    @Test
    void givenWrongPassword_whenLogin_thenReturnNotZero() {
        //given
        LoginRequestDto dto = new LoginRequestDto(email, "wrong password");

        //when
        Integer code = requestLoginAndGetCode(dto);

        //then
        assertEquals(400, code);
    }

}
