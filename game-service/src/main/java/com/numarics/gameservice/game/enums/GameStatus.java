package com.numarics.gameservice.game.enums;

import java.util.stream.Stream;

public enum GameStatus {
    NEW("new"),
    FINISHED("finished"),
    DROPPED("dropped");

    private String gameStatus;

    private GameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public static GameStatus of(String gameStatus) {
        return Stream.of(GameStatus.values())
                .filter(p -> p.getGameStatus().equals(gameStatus))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getGameStatus() {
        return gameStatus;
    }
}


