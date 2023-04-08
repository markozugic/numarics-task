package com.numarics.gameservice.play.responses;

import lombok.Data;

@Data
public class PlayerResponse {
    private Long id;
    private String name;
    private Long gameId;
}
