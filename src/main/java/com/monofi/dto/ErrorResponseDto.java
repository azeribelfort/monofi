package com.monofi.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {
    private String message;
    private HttpStatus status;
    private LocalDateTime dateTime;
}
