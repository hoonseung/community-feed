package com.backend.auth.domain;

import java.security.SecureRandom;
import java.util.Random;

public abstract class RandomTokenGenerator {

    private static final String CHARACTERS = "012345678910abcdefgABCEDEFGHIJKLMSopqrstuwxyzSYZ";
    private static final int TOKEN_LENGTH = 16;
    private final SecureRandom secureRandom = new SecureRandom();


    public static String generateToken() {
        StringBuilder sb = new StringBuilder(TOKEN_LENGTH);

        for (int i = 0; i < TOKEN_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(new Random().nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }
}
