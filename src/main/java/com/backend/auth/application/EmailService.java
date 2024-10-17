package com.backend.auth.application;

import com.backend.auth.application.dto.SendEmailRequestDto;
import com.backend.auth.application.interfaces.EmailSendRepository;
import com.backend.auth.application.interfaces.EmailVerificationRepository;
import com.backend.auth.domain.Email;
import com.backend.auth.domain.RandomTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;


    @Transactional
    public void sendEmail(SendEmailRequestDto sendEmailRequestDto) {
        Email email = Email.create(sendEmailRequestDto.email());
        String token = RandomTokenGenerator.generateToken();

        emailSendRepository.sendEmail(email, token);
        emailVerificationRepository.createEmailVerification(email, token);
    }
}
