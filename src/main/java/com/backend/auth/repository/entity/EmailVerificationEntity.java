package com.backend.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "community_email_verification")
@Entity
public class EmailVerificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String token;
    private Boolean isVerified;

    public static EmailVerificationEntity createEmailVerificationEntity(String email, String token){
        return new EmailVerificationEntity(email, token);
    }

    private EmailVerificationEntity(String email, String token) {
        this.email = email;
        this.token = token;
        this.isVerified = false;
    }


    public boolean isVerified() {
        return isVerified;
    }

    public void verified() {
        this.isVerified = true;
    }

    public void updateToken(String token) {
        this.token = token;
    }

    public boolean hasSameToken(String token) {
        return this.token.equals(token);
    }
}
