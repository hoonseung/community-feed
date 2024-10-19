package com.backend.acceptance.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.backend.auth.domain.TokenProvider;
import org.junit.jupiter.api.Test;

class TokenProviderTest {

    private final String secretKey = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);


    @Test
    void givenValidUserAndRole_whenCreateToken_thenParsingValidToken() {
        //given
        Long userId = 1L;
        String role = "ADMIN";

        //when
        String token = tokenProvider.createToken(userId, role);

        //then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getUserRole(token));
    }

    @Test
    void givenInValidToken_whenUserIdAndRole_thenThrowError() {
        //given
        String inValidToken = "invalidToken";

        //then
        assertThrows(Exception.class, () -> tokenProvider.getUserId(inValidToken));
    }

}
