package com.yrkim.springsecurity.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2960371216464418197L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(final String message) {
        super(message);
    }

    public UserNotFoundException(final Throwable cause) {
        super(cause);
    }
}
