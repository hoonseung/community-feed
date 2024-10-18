package com.backend.auth.domain;

import lombok.Getter;

@Getter
public class UserAuth {

    private final Email email;
    private final Password password;
    private final UserRole userRole;
    private Long userId;


    public static UserAuth createUserAuth(String emailText, String passwordText, String role,
        Long userId) {
        return new UserAuth(emailText, passwordText, role, userId);
    }

    public static UserAuth createUserAuth(String emailText, String passwordText, String role) {
        return new UserAuth(emailText, passwordText, role, null);
    }


    private UserAuth(String emailText, String passwordText, String role, Long userId) {
        this.email = Email.create(emailText);
        this.password = Password.createPassword(passwordText);
        this.userRole = UserRole.valueOf(role);
        this.userId = userId;
    }

}
