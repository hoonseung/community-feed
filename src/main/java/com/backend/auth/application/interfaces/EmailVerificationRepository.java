package com.backend.auth.application.interfaces;

import com.backend.auth.domain.Email;

public interface EmailVerificationRepository {

    void createEmailVerification(Email email, String token);

    void verifyEmail(Email email, String token);

    boolean isEmailVerified(Email email);
}
