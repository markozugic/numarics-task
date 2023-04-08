package com.numarics.gameservice.play.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayGameResponse {
    private Long gameId;
    private String gameName;
    private String gameStatus;
    private Long playerId;
    private String playerName;
}
