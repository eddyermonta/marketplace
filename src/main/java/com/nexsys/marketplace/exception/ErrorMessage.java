package com.nexsys.marketplace.exception;

public record ErrorMessage( String message) {
    public static ErrorMessage from(final String message) {
        return new ErrorMessage(message);
    }
}
