package com.monofi.exception;

public class EmailSendingException extends RuntimeException{

    private static final long serialVersionUID = 124L;

    public EmailSendingException(String message) {
        super(message);
    }
}
