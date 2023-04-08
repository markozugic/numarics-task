package com.numarics.gameservice.clients;

import com.numarics.gameservice.play.requests.RegisterPlayerRequest;
import com.numarics.gameservice.play.responses.PlayerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PlayerServiceClient {
    @Value("${player.service.url}")
    private String playerServiceUrl;

    public PlayerResponse registerUser(String playerName, Long gameId) {
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
        registerPlayerRequest.setPlayerName(playerName);
        registerPlayerRequest.setGameId(gameId);

        return getClient()
                .post()
                .uri(uri -> uri
                        .path("/register")
                        .build()
                )
                .bodyValue(registerPlayerRequest)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<PlayerResponse>() {
                })
                .block();
    }

    private WebClient getClient() {
        return WebClient.builder()
                .baseUrl(playerServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .build();
    }
}
