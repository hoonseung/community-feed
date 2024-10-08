package com.backend.common.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "invalid input value"),
    NOT_FOUND(404, "not found"),
    INTERNAL_ERROR(500, "internal occurs error"),

    ;


    private final Integer code;
    private final String message;
}
