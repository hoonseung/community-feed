package com.backend.auth.ui;

import com.backend.auth.application.AuthService;
import com.backend.auth.application.dto.LoginRequestDto;
import com.backend.auth.application.dto.UserAccessTokenResponseDto;
import com.backend.common.ui.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public ApiResponse<UserAccessTokenResponseDto> login(
        @RequestBody LoginRequestDto loginRequestDto) {
        return ApiResponse.success(authService.login(loginRequestDto));
    }
}
