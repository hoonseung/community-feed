package com.backend.acceptance.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.backend.auth.domain.RandomTokenGenerator;
import org.junit.jupiter.api.Test;

class RandomGeneratorTest {


    @Test
    void givenRandGenerateToken_whenGenerateRandomToken_givenCorrectTokenLength() {
        //given
        String generateToken = RandomTokenGenerator.generateToken();

        //then
        assertNotNull(generateToken);
        assertEquals(16, generateToken.length());
    }

    @Test
    void givenRandGenerateToken_whenGenerateRandomToken_givenTokenPatternMatch() {
        //given
        String generateToken = RandomTokenGenerator.generateToken();

        //then
        assertTrue(generateToken.matches("[A-za-z0-9]{16}"));
        assertNotNull(generateToken);
    }

    @Test
    void givenRandGenerateToken_whenGenerateRandomToken_givenTokenIsUnique() {
        //given
        String generateToken1 = RandomTokenGenerator.generateToken();
        String generateToken2 = RandomTokenGenerator.generateToken();

        //then
        assertNotEquals(generateToken1, generateToken2);
    }
}
