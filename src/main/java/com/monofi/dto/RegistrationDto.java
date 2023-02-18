package com.monofi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationDto {

    private String name;
    private String surname;
    private String username;
    private String password;
}
