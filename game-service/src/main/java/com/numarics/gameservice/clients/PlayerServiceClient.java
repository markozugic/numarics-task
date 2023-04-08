package com.numarics.gameservice.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PlayerServiceClient {
    @Value("${player.service.url}")
    private String playerServiceUrl;

    public WebClient getClient() {
        return WebClient.builder()
                .baseUrl(playerServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .build();
    }
}
