package com.numarics.playerservice.register.requests;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterPlayerRequest {
    @NotBlank
    private String playerName;
    @Nullable
    private Long gameId;
}
