package com.numarics.gameservice.play.responses;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlayerResponse {
    private Long id;
    private String name;
    private Long gameId;
}
