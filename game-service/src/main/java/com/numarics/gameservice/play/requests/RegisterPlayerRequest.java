package com.numarics.gameservice.play.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPlayerRequest {
    private String playerName;
    private Long gameId;
}
