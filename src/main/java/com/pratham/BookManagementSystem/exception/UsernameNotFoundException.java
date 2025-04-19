package com.pratham.BookManagementSystem.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String userName) {
        super("User not found with username: " + userName);
    }
}
