package com.monofi.exception.handler;

import com.monofi.dto.ErrorResponseDto;
import com.monofi.exception.EmailAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EmailAlreadyUsedExceptionHandler {
    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyUsedException(EmailAlreadyUsedException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                e.getMessage(),
                HttpStatus.CONFLICT,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, status);
    }
}
