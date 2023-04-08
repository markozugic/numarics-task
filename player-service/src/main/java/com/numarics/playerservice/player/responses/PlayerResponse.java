package com.numarics.playerservice.player.responses;

import com.numarics.playerservice.register.responses.GameResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerResponse {
    private Long id;
    private String name;
    private GameResponse game;
}
