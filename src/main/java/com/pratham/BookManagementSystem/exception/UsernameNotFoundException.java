package com.pratham.BookManagementSystem.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String identifierType, Object identifierValue) {
        super("User not found with " + identifierType + ": " + identifierValue);
    }
}
