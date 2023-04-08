package com.numarics.gameservice.integration;

import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.responses.GameResponse;
import com.numarics.gameservice.game.services.GameService;
import com.numarics.gameservice.play.PlayController;
import com.numarics.gameservice.play.requests.PlayGameRequest;
import com.numarics.gameservice.play.requests.UpdateGameStatusRequest;
import com.numarics.gameservice.play.responses.PlayGameResponse;
import com.numarics.gameservice.play.services.PlayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayControllerTests {
    @Mock
    private PlayService playService;

    @Mock
    private GameService gameService;

    @InjectMocks
    private PlayController playController;

    @Test
    public void testPlay() {
        PlayGameRequest request = new PlayGameRequest();
        PlayGameResponse expectedResponse = new PlayGameResponse();
        when(playService.playGame(request)).thenReturn(expectedResponse);

        ResponseEntity<PlayGameResponse> responseEntity = playController.play(request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testUpdateGameStatus() {
        Long id = 1L;
        UpdateGameStatusRequest request = new UpdateGameStatusRequest();
        Game updatedGame = new Game();
        when(gameService.updateGameStatus(id, request)).thenReturn(updatedGame);

        ResponseEntity<GameResponse> responseEntity = playController.updateGameStatus(id, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        GameResponse expectedResponse = GameResponse.builder()
                .id(updatedGame.getId())
                .gameName(updatedGame.getName())
                .status(updatedGame.getStatus())
                .createdAt(updatedGame.getCreatedAt())
                .updatedAt(updatedGame.getUpdatedAt())
                .build();
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}


