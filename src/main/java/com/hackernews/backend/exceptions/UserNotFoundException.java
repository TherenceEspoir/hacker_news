package com.hackernews.backend.exceptions;

public class UserNotFoundException extends HackerNewsException{
    public UserNotFoundException(String username) {
        super(String.format("User '%s' not found", username));
    }
}
