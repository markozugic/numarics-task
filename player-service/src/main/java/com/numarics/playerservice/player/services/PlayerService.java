package com.numarics.playerservice.player.services;

import com.numarics.playerservice.exceptions.custom.PlayerNotFoundException;
import com.numarics.playerservice.player.entities.Player;
import com.numarics.playerservice.player.events.PlayerDeletedEvent;
import com.numarics.playerservice.player.repositories.PlayerRepository;
import com.numarics.playerservice.player.responses.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, ApplicationEventPublisher publisher) {
        this.playerRepository = playerRepository;
        this.publisher = publisher;
    }

    public PlayerResponse getPlayer(String name) {
        Player player = playerRepository
                .findByName(name)
                .orElseThrow(
                        () -> new PlayerNotFoundException("Player with name: " + name + " not found")
                );

        return PlayerResponse
                .builder()
                .id(player.getId())
                .name(player.getName())
                .gameId(player.getGameId())
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
}
