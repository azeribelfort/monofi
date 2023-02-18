package com.monofi.exception.handler;

import com.monofi.dto.ErrorResponseDto;
import com.monofi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(NotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, status);
    }
}
