package com.backend.auth.repository;

import com.backend.auth.application.interfaces.EmailSendRepository;
import com.backend.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String token) {
        // TODO 구글 smtp 이용
    }
}
