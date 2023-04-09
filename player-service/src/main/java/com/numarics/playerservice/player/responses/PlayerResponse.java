package com.numarics.playerservice.player.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerResponse {
    private Long id;
    private String name;
    private Long gameId;
}
