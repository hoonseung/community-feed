package com.backend.auth.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class Password {

    private final String encryptedPasswordText;


    public static Password createPassword(String encryptedPasswordText) {
        if (encryptedPasswordText == null || encryptedPasswordText.isEmpty()) {
            throw new IllegalArgumentException("패스워드는 빈값이 될 수 없습니다.");
        }
        return new Password(SHA256.encrypt(encryptedPasswordText));
    }

    public boolean isMatchPassword(String password) {
        return encryptedPasswordText.matches(SHA256.encrypt(password));
    }
}
