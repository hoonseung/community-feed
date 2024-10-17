package com.backend.auth.application.interfaces;

import com.backend.auth.domain.Email;

public interface EmailSendRepository {

    void sendEmail(Email email, String token);
}
