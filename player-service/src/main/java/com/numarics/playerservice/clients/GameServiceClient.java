package com.numarics.playerservice.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GameServiceClient {
    @Value("${game.service.url}")
    private String gameServiceUrl;

    public WebClient getClient() {
        return WebClient.builder()
                .baseUrl(gameServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .build();
    }
}
