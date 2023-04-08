package com.numarics.gameservice.game.services;

import com.numarics.gameservice.exceptions.custom.GameNotFoundException;
import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.enums.GameStatus;
import com.numarics.gameservice.game.repositories.GameRepository;
import com.numarics.gameservice.play.requests.PlayGameRequest;
import com.numarics.gameservice.play.requests.UpdateGameStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGameById(Long id) {
        return getGame(id);
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
}
