package com.mr.order.exeption;

public class DatabaseInteractionException extends RuntimeException {

    public DatabaseInteractionException(String message, Throwable cause) {
        super(message, cause);
    }
}
