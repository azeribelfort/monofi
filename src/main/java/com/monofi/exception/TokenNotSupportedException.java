package com.monofi.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenNotSupportedException extends RuntimeException{

    private static final long serialVersionUID = 124L;

    public TokenNotSupportedException(String message) {
        super(message);
    }

}
