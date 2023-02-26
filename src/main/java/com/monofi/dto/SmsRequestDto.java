package com.monofi.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequestDto {
    @NotBlank
    private String phoneNumber;
}
