package com.example.adminweb.exceptionHandling;

public class EmailExistedException extends Exception {
    public EmailExistedException() {
    }

    public EmailExistedException(String message) {
        super(message);
    }
}
