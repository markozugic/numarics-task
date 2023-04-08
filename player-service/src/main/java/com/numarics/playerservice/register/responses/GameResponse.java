package com.numarics.playerservice.register.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameResponse {
    private Long id;
    private String gameName;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
