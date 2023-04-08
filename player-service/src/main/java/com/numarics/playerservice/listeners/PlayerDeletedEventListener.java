package com.numarics.playerservice.listeners;

import com.numarics.playerservice.clients.GameServiceClient;
import com.numarics.playerservice.events.PlayerDeletedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PlayerDeletedEventListener implements ApplicationListener<PlayerDeletedEvent> {
    private final GameServiceClient gameServiceClient;

    @Autowired
    public PlayerDeletedEventListener(GameServiceClient gameServiceClient) {
        this.gameServiceClient = gameServiceClient;
    }

    @Override
    public void onApplicationEvent(PlayerDeletedEvent event) {
        gameServiceClient.getClient()
                .delete()
                .uri(uri -> uri
                        .path("/game/{id}")
                        .build(event.getPlayer().getGameId())
                )
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
