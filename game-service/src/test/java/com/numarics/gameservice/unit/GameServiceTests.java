package com.numarics.gameservice.unit;

import com.numarics.gameservice.exceptions.custom.GameNotFoundException;
import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.enums.GameStatus;
import com.numarics.gameservice.game.repositories.GameRepository;
import com.numarics.gameservice.game.responses.GameResponse;
import com.numarics.gameservice.game.services.GameService;
import com.numarics.gameservice.play.requests.UpdateGameStatusRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameServiceTests {

    private static final Long GAME_ID = 1L;
    private static final String GAME_NAME = "Test Game";
    private static final GameStatus GAME_STATUS = GameStatus.NEW;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;

    @BeforeEach
    void setup() {
        Game game = new Game();
        game.setId(GAME_ID);
        game.setName(GAME_NAME);
        game.setStatus(GAME_STATUS.getGameStatus());
        game.setCreatedAt(LocalDateTime.now());
        game.setUpdatedAt(LocalDateTime.now());
        gameRepository.save(game);
    }

    @Test
    void testGetGameById() {
        // When
        GameResponse response = gameService.getGameById(GAME_ID);

        // Then
        assertNotNull(response);
        assertEquals(GAME_ID, response.getId());
        assertEquals(GAME_NAME, response.getGameName());
        assertEquals(GAME_STATUS, GameStatus.of(response.getStatus()));
    }

    @Test
    void testDeleteGame_whenGameFound() {
        // When
        gameService.deleteGame(GAME_ID);

        // Then
        assertEquals(Optional.empty(), gameRepository.findById(GAME_ID));
    }

    @Test
    void testDeleteGame_whenGameNotFound() {
        assertThrows(GameNotFoundException.class, () -> gameService.deleteGame(1000L));
    }

    @Test
    void testUpdateGameStatus() {
        // Given
        UpdateGameStatusRequest request = new UpdateGameStatusRequest();
        request.setStatus(GameStatus.FINISHED);

        // When
        Game updatedGame = gameService.updateGameStatus(GAME_ID, request);

        // Then
        assertNotNull(updatedGame);
        assertEquals(GAME_ID, updatedGame.getId());
        assertEquals(GAME_NAME, updatedGame.getName());
        assertEquals(GameStatus.FINISHED, GameStatus.of(updatedGame.getStatus()));
    }
}