package com.backend.auth.ui;

import com.backend.auth.application.EmailService;
import com.backend.auth.application.dto.SendEmailRequestDto;
import com.backend.common.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/signup")
@RequiredArgsConstructor
@RestController
public class SignUpController {

    private final EmailService emailService;

    @PostMapping("/send-verification-email")
    public ApiResponse<Void> sendEmail(@RequestBody SendEmailRequestDto sendEmailRequestDto) {
        emailService.sendEmail(sendEmailRequestDto);
        return ApiResponse.success(null);
    }
}
