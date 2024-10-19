package com.backend.auth.domain;

import lombok.Getter;

@Getter
public class UserAuth {

    private final Email email;
    private final Password password;
    private final UserRole userRole;
    private final Long userId;


    public static UserAuth createUserAuth(String emailText, String passwordText, String role,
        Long userId) {
        return new UserAuth(emailText, passwordText, role, userId);
    }

    public static UserAuth createUserAuth(String emailText, String passwordText, String role) {
        return new UserAuth(emailText, passwordText, role, null);
    }

    public static UserAuth createUserAuthPs(String emailText, String passwordText, String role, Long userId) {
        return new UserAuth(emailText, Password.createPassword(passwordText), role, userId);
    }


    private UserAuth(String emailText, String passwordText, String role, Long userId) {
        this.email = Email.create(emailText);
        this.password = Password.createNewPassword(passwordText);
        this.userRole = UserRole.valueOf(role);
        this.userId = userId;
    }

    private UserAuth(String emailText, Password password, String role, Long userId) {
        this.email = Email.create(emailText);
        this.password = password;
        this.userRole = UserRole.valueOf(role);
        this.userId = userId;
    }


    public boolean isMatchPassword(String password) {
        return this.password.isMatchPassword(password);
    }


}
