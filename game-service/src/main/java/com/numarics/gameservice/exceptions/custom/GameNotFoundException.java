package com.numarics.gameservice.exceptions.custom;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String message) {
        super(message);
    }

    public GameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

