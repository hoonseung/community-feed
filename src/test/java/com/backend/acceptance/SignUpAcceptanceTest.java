package com.backend.acceptance;

import static com.backend.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static com.backend.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.backend.acceptance.steps.SignUpAcceptanceSteps;
import com.backend.acceptance.utils.AcceptanceTestTemplate;
import com.backend.auth.application.dto.CreateUserAuthRequestDto;
import com.backend.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "hong@email.com";


    @BeforeEach
    void setUp() {
        super.cleanup();
    }


    @Test
    void givenEmail_whenSendEmail_then_VerificationTokenSaved() {
        //given
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto(email);

        //when
        Integer code = SignUpAcceptanceSteps.requestSendEmail(sendEmailRequestDto);

        //then
        String emailToken = getEmailToken(sendEmailRequestDto.email());
        assertNotNull(emailToken);
        assertEquals(0, code);
    }

    @Test
    void givenInvalidEmail_whenSendEmail_thenVerificationTokenNotSaved() {
        //given
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto("abc");

        //when
        Integer code = SignUpAcceptanceSteps.requestSendEmail(sendEmailRequestDto);

        assertEquals(400, code);
    }

    @Test
    void givenSendVerify_whenVerifyEmailWithCorrectToken_thenEmailVerified() {
        //given
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto(email);
        requestSendEmail(sendEmailRequestDto);

        //when
        String emailToken = getEmailToken(sendEmailRequestDto.email());
        Integer code = requestVerifyEmail(sendEmailRequestDto.email(),
            emailToken);

        //then
        boolean emailVerified = isEmailVerified(sendEmailRequestDto.email());
        assertEquals(0, code);
        assertTrue(emailVerified);
    }

    @Test
    void givenSendVerify_whenVerifyEmailWithWrongToken_thenEmailNotVerified() {
        //given
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto(email);
        requestSendEmail(sendEmailRequestDto);

        //when
        Integer code = requestVerifyEmail(sendEmailRequestDto.email(), "wrongToken");

        //then
        boolean emailVerified = isEmailVerified(sendEmailRequestDto.email());
        assertEquals(400, code);
        assertFalse(emailVerified);
    }

    @Test
    void givenSendVerify_whenVerifyEmailAgain_thenThrowError() {
        //given
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto(email);
        requestSendEmail(sendEmailRequestDto);
        String emailToken = getEmailToken(sendEmailRequestDto.email());
        requestVerifyEmail(sendEmailRequestDto.email(), emailToken);

        //when

        Integer code = requestVerifyEmail(sendEmailRequestDto.email(), emailToken);

        //then
        assertEquals(400, code);
    }

    @Test
    void givenSendVerify_whenVerifyEmailWithWrongEmail_thenThrowError() {
        //given
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto(email);
        requestSendEmail(sendEmailRequestDto);

        //when
        Integer code = requestVerifyEmail(
            "wrong email",
            "wrong token");

        //then
        assertEquals(400, code);
    }

    @Test
    void givenVerifiedEmail_whenRegister_thenUserRegistered() {
        //given
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto(email);
        String requestEmail = sendEmailRequestDto.email();
        requestSendEmail(sendEmailRequestDto);
        String emailToken = getEmailToken(requestEmail);
        requestVerifyEmail(requestEmail, emailToken);

        //when
        CreateUserAuthRequestDto createUserAuthRequestDto = new CreateUserAuthRequestDto(email,
            "password", "USER", "hong", "profileImageUrl");
        Integer code = SignUpAcceptanceSteps.registerUser(createUserAuthRequestDto);

        assertEquals(0, code);
        Long userId = getUserId(email);
        assertEquals(1L, userId);
    }

    @Test
    void givenUnVerifiedEmail_whenRegister_thenThrowError() {
        //given
        SendEmailRequestDto sendEmailRequestDto = new SendEmailRequestDto(email);
        requestSendEmail(sendEmailRequestDto);

        //when
        CreateUserAuthRequestDto createUserAuthRequestDto = new CreateUserAuthRequestDto(email,
            "password", "USER", "hong", "profileImageUrl");
        Integer code = SignUpAcceptanceSteps.registerUser(createUserAuthRequestDto);

        assertEquals(400, code);
    }
}
