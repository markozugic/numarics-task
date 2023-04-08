package com.numarics.gameservice.play.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayGameResponse {
    private Long gameId;
    private String gameName;
    private String gameStatus;
    private Long playerId;
    private String playerName;
}
