package com.backend.common.ui;


import com.backend.common.domain.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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

    // @Repository 빈들에 예외 변환 어드바이스를 적용하고 예외가 발생하면 스프링 데이터 예외로 변환함
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ApiResponse<Void> invalidDataAccessApiUsageException(
        InvalidDataAccessApiUsageException iae) {
        log.error("occurs invalidDataAccessApiUsageException error : {}", iae.getMessage());
        if (iae.getRootCause() != null) {
            log.error("occurs origin cause error: {}", iae.getRootCause().toString());
        }
        return ApiResponse.fail(ErrorCode.INVALID_INPUT_VALUE);
    }


    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> exceptionHandle(Exception e) {
        log.error("occurs internal error : {}", e.getMessage());
        return ApiResponse.fail(ErrorCode.INTERNAL_ERROR);
    }
}

