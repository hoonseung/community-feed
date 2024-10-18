package com.backend.auth.ui;

import com.backend.auth.application.AuthService;
import com.backend.auth.application.EmailService;
import com.backend.auth.application.dto.CreateUserAuthRequestDto;
import com.backend.auth.application.dto.SendEmailRequestDto;
import com.backend.common.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/signup")
@RequiredArgsConstructor
@RestController
public class SignUpController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/send-verification-email")
    public ApiResponse<Void> sendEmail(@RequestBody SendEmailRequestDto sendEmailRequestDto) {
        emailService.sendEmail(sendEmailRequestDto);
        return ApiResponse.success(null);
    }

    @GetMapping("/verify-token")
    public ApiResponse<Void> verifyEmail(@RequestParam String email, @RequestParam String token) {
        emailService.verifyEmail(email, token);
        return ApiResponse.success(null);
    }

    @PostMapping("/register")
    public ApiResponse<Long> register(
        @RequestBody CreateUserAuthRequestDto createUserAuthRequestDto) {
        return ApiResponse.success(authService.registerUser(createUserAuthRequestDto));
    }

}
