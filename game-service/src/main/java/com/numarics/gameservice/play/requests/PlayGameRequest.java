package com.numarics.gameservice.play.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PlayGameRequest {
    @NotBlank(message = "Player name is required")
    private String playerName;

    @NotBlank(message = "Game name is required")
    private String gameName;
}
