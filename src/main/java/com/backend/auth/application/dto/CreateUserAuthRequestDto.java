package com.backend.auth.application.dto;

public record CreateUserAuthRequestDto(
    // 회원가입전 인증한 이메일
    String email,
    String password,
    String role,
    String name,
    String profileImageUrl
) {

}
