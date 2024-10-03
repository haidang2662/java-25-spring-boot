package com.example.homework01;

public class EmailExistedException extends Exception {
    public EmailExistedException() {
    }

    public EmailExistedException(String message) {
        super(message);
    }
}
