package com.backend.auth.domain;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Email {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private final String emailText;


    public static Email create(String emailText) {
        if (isNotValidEmailString(emailText)) {
            throw new IllegalArgumentException("email is not valid");
        }

        return new Email(emailText);
    }


    public String getEmailText() {
        return this.emailText;
    }

    private static boolean isNotValidEmailString(String emailText) {
        return (emailText == null || emailText.isBlank()) || !pattern.matcher(emailText).matches();
    }

}
