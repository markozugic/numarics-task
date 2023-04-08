package com.numarics.gameservice.play.exceptions;

public class PlayerHasAGameException extends RuntimeException {
    public PlayerHasAGameException(String message) {
        super(message);
    }

    public PlayerHasAGameException(String message, Throwable cause) {
        super(message, cause);
    }
}
