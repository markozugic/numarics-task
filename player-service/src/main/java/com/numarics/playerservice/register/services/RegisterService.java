package com.numarics.playerservice.register.services;

import com.numarics.playerservice.player.entities.Player;
import com.numarics.playerservice.player.repositories.PlayerRepository;
import com.numarics.playerservice.register.requests.RegisterPlayerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final PlayerRepository playerRepository;

    @Autowired
    public RegisterService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player registerPlayer(RegisterPlayerRequest registerPlayerRequest) {
        Player player = playerRepository.findByName(registerPlayerRequest.getPlayerName()).orElse(null);
        if (player != null) {
            player.setGameId(registerPlayerRequest.getGameId());
            playerRepository.save(player);
        } else {
            Player playerToPersist = new Player();
            playerToPersist.setName(registerPlayerRequest.getPlayerName());
            playerToPersist.setGameId(registerPlayerRequest.getGameId());
            player = playerRepository.save(playerToPersist);
        }

        return player;
    }

}
