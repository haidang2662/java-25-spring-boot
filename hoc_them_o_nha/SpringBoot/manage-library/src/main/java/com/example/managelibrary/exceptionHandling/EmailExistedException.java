package com.example.managelibrary.exceptionHandling;

public class EmailExistedException extends Exception{
    public EmailExistedException() {
    }

    public EmailExistedException(String message) {
        super(message);
    }
}
