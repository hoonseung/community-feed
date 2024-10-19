package com.backend.auth.application.dto;

public record LoginRequestDto(
    String email,
    String password
) {

}
