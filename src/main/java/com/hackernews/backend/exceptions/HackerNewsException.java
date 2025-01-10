package com.hackernews.backend.exceptions;

public class HackerNewsException extends RuntimeException {
    public HackerNewsException(String message) {
        super(message);
    }
}
