package com.numarics.gameservice.play.requests;

import com.numarics.gameservice.game.enums.GameStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
public class UpdateGameStatusRequest {
    @NotNull
    @Enumerated(EnumType.STRING)
    public GameStatus status;
}
