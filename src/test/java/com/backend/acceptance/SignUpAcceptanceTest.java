package com.backend.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.backend.acceptance.steps.SignUpAcceptanceSteps;
import com.backend.acceptance.utils.AcceptanceTestTemplate;
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
}
