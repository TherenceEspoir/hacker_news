package com.hackernews.backend.exceptions;

public class UsernameAlreadyExistsException extends HackerNewsException {
    public UsernameAlreadyExistsException(String username) {
        super(String.format("Username '%s' already exists", username));
    }
}
