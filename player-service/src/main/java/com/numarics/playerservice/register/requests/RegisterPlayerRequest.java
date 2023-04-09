package com.numarics.playerservice.register.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPlayerRequest {
    @NotBlank
    private String playerName;
    @Nullable
    private Long gameId;
}
