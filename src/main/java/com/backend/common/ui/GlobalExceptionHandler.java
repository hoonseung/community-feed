package com.backend.common.ui;


import com.backend.common.domain.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Void> illegalArgumentExceptionHandle(IllegalArgumentException iae) {
        log.error("occurs illegalArgument error : {}", iae.getMessage());
        return ApiResponse.fail(ErrorCode.INVALID_INPUT_VALUE);
    }


    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> exceptionHandle(Exception e) {
        log.error("occurs internal error : {}", e.getMessage());
        return ApiResponse.fail(ErrorCode.INTERNAL_ERROR);
    }
}

