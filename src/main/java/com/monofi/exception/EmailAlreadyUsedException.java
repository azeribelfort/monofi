package com.monofi.exception;

public class EmailAlreadyUsedException extends RuntimeException{

    public static final String EMAIL_ALREADY_USED = "Email \"%s\" already registered,try different email";

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException(String email) {
        super(String.format(EMAIL_ALREADY_USED, email));
    }
}