package com.numarics.playerservice.player.services;

import com.numarics.playerservice.clients.GameServiceClient;
import com.numarics.playerservice.exceptions.custom.PlayerNotFoundException;
import com.numarics.playerservice.player.entities.Player;
import com.numarics.playerservice.player.events.PlayerDeletedEvent;
import com.numarics.playerservice.player.repositories.PlayerRepository;
import com.numarics.playerservice.player.responses.PlayerResponse;
import com.numarics.playerservice.register.responses.GameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final GameServiceClient gameServiceClient;

    private final ApplicationEventPublisher publisher;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, GameServiceClient gameServiceClient, ApplicationEventPublisher publisher) {
        this.playerRepository = playerRepository;
        this.gameServiceClient = gameServiceClient;
        this.publisher = publisher;
    }

    public PlayerResponse getPlayer(String name) {
        Player player = playerRepository
                .findByName(name)
                .orElseThrow(
                        () -> new PlayerNotFoundException("Player with name: " + name + " not found")
                );

        GameResponse game = fetchGame(player);

        return PlayerResponse
                .builder()
                .id(player.getId())
                .name(player.getName())
                .game(game)
                .build();
    }

    public void deletePlayer(Long id) {
        Player player = playerRepository
                .findById(id)
                .orElseThrow(
                        () -> new PlayerNotFoundException("Player with name: " + id + " not found")
                );

        playerRepository.delete(player);

        publisher.publishEvent(new PlayerDeletedEvent(this, player));
    }

    private GameResponse fetchGame(Player player) {
        return gameServiceClient
                .getClient()
                .get()
                .uri(uri -> uri
                        .path("/game/{id}")
                        .build(player.getGameId())
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<GameResponse>() {
                })
                .block();
    }


}
