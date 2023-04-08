package com.numarics.gameservice.unit;

import com.numarics.gameservice.clients.PlayerServiceClient;
import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.enums.GameStatus;
import com.numarics.gameservice.game.services.GameService;
import com.numarics.gameservice.play.requests.PlayGameRequest;
import com.numarics.gameservice.play.responses.PlayGameResponse;
import com.numarics.gameservice.play.responses.PlayerResponse;
import com.numarics.gameservice.play.services.PlayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayServiceTests {
    @Mock
    private PlayerServiceClient playerServiceClient;

    @Mock
    private GameService gameService;

    @InjectMocks
    private PlayService playService;

    @Test
    public void testPlayGame() {
        // Create test data
        PlayGameRequest playGameRequest = new PlayGameRequest();
        playGameRequest.setGameName("testGame");
        playGameRequest.setPlayerName("testPlayer");

        Game game = new Game();
        game.setId(1L);
        game.setName("testGame");
        game.setGameStatusEnum(GameStatus.NEW);
        when(gameService.saveNewGame(any())).thenReturn(game);

        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.setId(1L);
        playerResponse.setName("testPlayer");
        playerResponse.setGameId(game.getId());

        when(playerServiceClient.registerUser(any(), any())).thenReturn(playerResponse);

        // Call the method being tested
        PlayGameResponse playGameResponse = playService.playGame(playGameRequest);

        // Verify the response
        assertThat(playGameResponse.getGameName()).isEqualTo("testGame");
        assertThat(playGameResponse.getPlayerName()).isEqualTo("testPlayer");
    }
}

