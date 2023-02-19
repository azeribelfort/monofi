package com.monofi.exception.handler;

import com.monofi.dto.ErrorResponseDto;
import com.monofi.exception.NotFoundException;
import com.monofi.exception.TokenNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TokenNotSupportedExceptionHandler {
    @ExceptionHandler(TokenNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleTokenNotSupportedException(TokenNotSupportedException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, status);
    }
}
