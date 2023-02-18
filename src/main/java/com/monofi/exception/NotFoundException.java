package com.monofi.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 124L;

    public NotFoundException(String message) {
        super(message);
    }

}