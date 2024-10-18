package com.backend.auth.repository;

import com.backend.auth.application.interfaces.EmailVerificationRepository;
import com.backend.auth.domain.Email;
import com.backend.auth.repository.entity.EmailVerificationEntity;
import com.backend.auth.repository.jpa.JpaEmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    // 처음 로그인 시 인증되지 않은 엔티티 생성
    @Transactional
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
                EmailVerificationEntity.createEmailVerificationEntity(emailText, token)));
    }

    // 인증 요청 시 인증 전 엔티티 가져와서 인증 상태로 변경
    @Transactional
    @Override
    public void verifyEmail(Email email, String token) {
        String emailText = email.getEmailText();
        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(emailText)
            .orElseThrow(() -> new IllegalArgumentException("인증 요청하지 않은 이메일입니다."));

        if (!entity.hasSameToken(token)) {
            throw new IllegalArgumentException("토큰값이 유효하지 않습니다.");
        }

        if (entity.isVerified()) {
            throw new IllegalArgumentException("이미 인증된 이메일입니다.");
        }

        entity.verified();
    }

    @Override
    public boolean isEmailVerified(Email email) {
        return jpaEmailVerificationRepository.findByEmail(email.getEmailText())
            .orElseThrow(() -> new IllegalArgumentException("인증 요청하지 않은 이메일입니다."))
            .isVerified();
    }
}
