package com.numarics.gameservice.play.requests;

import lombok.Data;

@Data
public class RegisterPlayerRequest {
    private String playerName;
    private Long gameId;
}
