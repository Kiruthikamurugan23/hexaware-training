package com.hexaware.exception;

public class InvalidAccountException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidAccountException(String message) {
        super(message);
    }
}
