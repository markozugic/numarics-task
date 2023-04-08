package com.numarics.gameservice.game.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GameResponse {
    private Long id;
    private String gameName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
}
