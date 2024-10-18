package com.backend.auth.application;

import com.backend.auth.application.dto.SendEmailRequestDto;
import com.backend.auth.application.interfaces.EmailSendRepository;
import com.backend.auth.application.interfaces.EmailVerificationRepository;
import com.backend.auth.domain.Email;
import com.backend.auth.domain.RandomTokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;


    public void sendEmail(SendEmailRequestDto sendEmailRequestDto) {
        Email email = Email.create(sendEmailRequestDto.email());
        String token = RandomTokenGenerator.generateToken();

        emailSendRepository.sendEmail(email, token);
        emailVerificationRepository.createEmailVerification(email, token);
    }

    public void verifyEmail(String emailText, String token) {
        emailVerificationRepository.verifyEmail(Email.create(emailText), token);
    }
}
