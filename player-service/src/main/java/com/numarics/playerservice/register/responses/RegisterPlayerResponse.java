package com.numarics.playerservice.register.responses;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterPlayerResponse {
    private Long id;
    private String name;
    private Long gameId;
}
