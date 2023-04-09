package com.numarics.gameservice.game.services;

import com.numarics.gameservice.clients.PlayerServiceClient;
import com.numarics.gameservice.exceptions.custom.GameNotFoundException;
import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.enums.GameStatus;
import com.numarics.gameservice.game.repositories.GameRepository;
import com.numarics.gameservice.game.responses.GameResponse;
import com.numarics.gameservice.play.requests.PlayGameRequest;
import com.numarics.gameservice.play.requests.UpdateGameStatusRequest;
import com.numarics.gameservice.play.responses.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    private final PlayerServiceClient playerServiceClient;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerServiceClient playerServiceClient) {
        this.gameRepository = gameRepository;
        this.playerServiceClient = playerServiceClient;
    }

    public GameResponse getGameById(Long id) {
        Game game = getGame(id);

        return GameResponse
                .builder()
                .id(game.getId())
                .gameName(game.getName())
                .status(game.getStatus())
                .createdAt(game.getCreatedAt())
                .updatedAt(game.getUpdatedAt())
                .build();
    }

    public void deleteGame(Long id) {
        Game game = getGame(id);
        gameRepository.delete(game);
    }

    private Game getGame(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id: " + id + " not found"));
    }

    public Game updateGameStatus(Long id, UpdateGameStatusRequest updateGameStatusRequest) {
        Game game = getGame(id);
        game.setStatus(updateGameStatusRequest.status.getGameStatus());

        return gameRepository.save(game);
    }

    public Game saveNewGame(PlayGameRequest playGameRequest) {
        Game game = new Game();
        game.setName(playGameRequest.getGameName());
        game.setGameStatusEnum(GameStatus.NEW);
        return gameRepository.save(game);
    }

    public Page<Game> getGames(String name, String status, Pageable pageable) {
        if (name != null && status != null) {
            return gameRepository.findByNameAndStatus(name, status, pageable);
        }

        if (name != null) {
            return gameRepository.findByName(name, pageable);
        }

        if (status != null) {
            return gameRepository.findByStatus(status, pageable);
        }

        return Page.empty();
    }

    public GameResponse getGameByPlayerName(String playerName) {
        PlayerResponse playerResponse = playerServiceClient.getPlayerByName(playerName);
        if (playerResponse == null) {
            throw new GameNotFoundException("Game not found for player name: " + playerName);
        }

        Long gameId = playerResponse.getGameId();
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isEmpty()) {
            throw new GameNotFoundException("Game not found for id: " + gameId);
        }

        Game game = gameOptional.get();

        return GameResponse
                .builder()
                .id(game.getId())
                .gameName(game.getName())
                .status(game.getStatus())
                .createdAt(game.getCreatedAt())
                .updatedAt(game.getUpdatedAt())
                .build();
    }
}

