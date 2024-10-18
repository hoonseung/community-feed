package com.backend.auth.application;

import com.backend.auth.application.dto.CreateUserAuthRequestDto;
import com.backend.auth.application.interfaces.EmailVerificationRepository;
import com.backend.auth.application.interfaces.UserAuthRepository;
import com.backend.auth.domain.Email;
import com.backend.auth.domain.UserAuth;
import com.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;


    public Long registerUser(CreateUserAuthRequestDto createUserAuthRequestDto) {
        Email email = Email.create(createUserAuthRequestDto.email());

        if (!emailVerificationRepository.isEmailVerified(email)){
            throw new IllegalArgumentException("인증되지 않은 이메일입니다.");
        }

        UserAuth userAuth = UserAuth.createUserAuth(
            createUserAuthRequestDto.email(),
            createUserAuthRequestDto.password(),
            createUserAuthRequestDto.role());

        User user = User.createUser(createUserAuthRequestDto.name(),
            createUserAuthRequestDto.profileImageUrl());

        UserAuth userAuthPs = userAuthRepository.registerUser(userAuth, user);

        return userAuthPs.getUserId();
    }
}
