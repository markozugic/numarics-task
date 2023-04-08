package com.numarics.gameservice.play.services;

import com.numarics.gameservice.clients.PlayerServiceClient;
import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.services.GameService;
import com.numarics.gameservice.play.requests.PlayGameRequest;
import com.numarics.gameservice.play.responses.PlayGameResponse;
import com.numarics.gameservice.play.responses.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayService {
    private final PlayerServiceClient playerServiceClient;

    private final GameService gameService;

    @Autowired
    public PlayService(PlayerServiceClient playerServiceClient, GameService gameService) {
        this.playerServiceClient = playerServiceClient;
        this.gameService = gameService;
    }

    public PlayGameResponse playGame(PlayGameRequest playGameRequest) {
        Game newGame = gameService.saveNewGame(playGameRequest);
        PlayerResponse player = playerServiceClient.registerUser(playGameRequest.getPlayerName(), newGame.getId());

        return PlayGameResponse
                .builder()
                .gameId(newGame.getId())
                .gameName(newGame.getName())
                .gameStatus(newGame.getStatus())
                .playerId(player.getId())
                .playerName(player.getName())
                .build();
    }
}
