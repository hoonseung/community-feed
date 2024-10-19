package com.backend.acceptance.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.backend.auth.domain.Password;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PasswordEncryptTest {

    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        //given
        Password password = Password.createNewPassword("password");
        //then
        Assertions.assertTrue(password.isMatchPassword("password"));
    }

    @Test
    void givenPassword_whenMatchDiffPassword_thenReturnFalse(){
        //given
        Password password = Password.createNewPassword("password1");
        //then
        assertFalse(password.isMatchPassword("password"));
    }

    @NullAndEmptySource
    @ParameterizedTest
    void givenNullOrEmptyString_whenMatchPassword_thenThrowError(String password){
        assertThrows(IllegalArgumentException.class, () -> Password.createNewPassword(password));
    }
}
