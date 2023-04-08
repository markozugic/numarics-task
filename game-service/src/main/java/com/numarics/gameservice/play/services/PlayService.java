package com.numarics.gameservice.play.services;

import com.numarics.gameservice.clients.PlayerServiceClient;
import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.enums.GameStatus;
import com.numarics.gameservice.game.repositories.GameRepository;
import com.numarics.gameservice.play.requests.PlayGameRequest;
import com.numarics.gameservice.play.requests.RegisterPlayerRequest;
import com.numarics.gameservice.play.responses.PlayGameResponse;
import com.numarics.gameservice.play.responses.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class PlayService {
    private final PlayerServiceClient playerServiceClient;

    private final GameRepository gameRepository;

    @Autowired
    public PlayService(PlayerServiceClient playerServiceClient, GameRepository gameRepository) {
        this.playerServiceClient = playerServiceClient;
        this.gameRepository = gameRepository;
    }

    public PlayGameResponse playGame(PlayGameRequest playGameRequest) {
        Game newGame = createNewGame(playGameRequest);
        PlayerResponse player = getOrCreatePlayer(playGameRequest.getPlayerName(), newGame.getId());

        return PlayGameResponse
                .builder()
                .gameId(newGame.getId())
                .gameName(newGame.getName())
                .gameStatus(newGame.getStatus())
                .playerId(player.getId())
                .playerName(player.getName())
                .build();
    }

    private Game createNewGame(PlayGameRequest playGameRequest) {
        Game game = new Game();
        game.setName(playGameRequest.getGameName());
        game.setGameStatusEnum(GameStatus.NEW);
        return gameRepository.save(game);
    }

    private PlayerResponse getOrCreatePlayer(String playerName, Long gameId) {
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest();
        registerPlayerRequest.setPlayerName(playerName);
        registerPlayerRequest.setGameId(gameId);

        return playerServiceClient
                .getClient()
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
}
