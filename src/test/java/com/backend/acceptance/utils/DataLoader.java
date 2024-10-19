package com.backend.acceptance.utils;

import static com.backend.acceptance.steps.SignUpAcceptanceSteps.registerUser;
import static com.backend.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static com.backend.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static com.backend.acceptance.steps.UserAcceptanceSteps.requestFollowUser;

import com.backend.auth.application.dto.CreateUserAuthRequestDto;
import com.backend.auth.application.dto.SendEmailRequestDto;
import com.backend.user.application.dto.FollowUserRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Slf4j
@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        log.info("Load data");

        createUser("hong@email.com");
        createUser("shlee@email.com");
        createUser("gangchan@email.com");

        requestFollowUser(new FollowUserRequestDto(1L, 2L));
        requestFollowUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email) {
        return entityManager.createNativeQuery(
                "select token from community_email_verification where email = ?",
                String.class)
            .setParameter(1, email)
            .getSingleResult().toString();
    }

    public boolean isEmailVerified(String email) {
        return entityManager.createQuery(
                "select isVerified from EmailVerificationEntity where email =:email", Boolean.class)
            .setParameter("email", email)
            .getSingleResult();
    }

    public Long getUserId(String email) {
        return entityManager.createQuery(
                "select u.userId from UserAuthEntity u where u.email = :email",
                Long.class)
            .setParameter("email", email)
            .getSingleResult();
    }

    private void createUser(String email) {
        requestSendEmail(new SendEmailRequestDto(email));
        String emailToken = getEmailToken(email);
        requestVerifyEmail(email, emailToken);
        registerUser(new CreateUserAuthRequestDto(email, "password", "USER", "hong", "imageUrl"));
    }
}
