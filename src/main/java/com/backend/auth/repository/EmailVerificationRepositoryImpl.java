package com.backend.auth.repository;

import com.backend.auth.application.interfaces.EmailVerificationRepository;
import com.backend.auth.domain.Email;
import com.backend.auth.repository.entity.EmailVerificationEntity;
import com.backend.auth.repository.jpa.JpaEmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;


    @Override
    public void createEmailVerification(Email email, String token) {
        String emailText = email.getEmailText();
        jpaEmailVerificationRepository.findByEmail(emailText)
            .ifPresentOrElse(i -> {
                if (i.isVerified()) {
                    throw new IllegalArgumentException("이미 인증된 이메일 입니다.");
                }
                i.updateToken(token);
            }, () -> jpaEmailVerificationRepository.save(
                new EmailVerificationEntity(emailText, token)));
    }
}
