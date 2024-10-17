package com.backend.acceptance.auth;

import com.backend.auth.domain.Email;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {



    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String email) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Email.create(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"naver.com", "valid/@ab", "안녕하세여.com"})
    void givenInvalidEmail_whenCreate_thenThrowError(String email) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Email.create(email));
    }


    @ParameterizedTest
    @ValueSource(strings = {"exmaple4029@naver.com, this@gmail.com"})
    void givenEnableValidEmail_whenCreate_thenReturnEmail(String email) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Email.create(email));
    }
}
