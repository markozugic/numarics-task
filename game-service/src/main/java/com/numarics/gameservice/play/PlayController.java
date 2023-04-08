package com.numarics.gameservice.play;

import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.responses.GameResponse;
import com.numarics.gameservice.game.services.GameService;
import com.numarics.gameservice.play.requests.PlayGameRequest;
import com.numarics.gameservice.play.requests.UpdateGameStatusRequest;
import com.numarics.gameservice.play.responses.PlayGameResponse;
import com.numarics.gameservice.play.services.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/play")
public class PlayController {
    private final PlayService playService;
    private final GameService gameService;

    @Autowired
    public PlayController(PlayService playService, GameService gameService) {
        this.playService = playService;
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<PlayGameResponse> play(
            @Valid @RequestBody PlayGameRequest playGameRequest
    ) {
        PlayGameResponse response = playService.playGame(playGameRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> updateGameStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateGameStatusRequest updateGameStatusRequest
    ) {
        Game updatedGame = gameService.updateGameStatus(id, updateGameStatusRequest);

        GameResponse response = GameResponse
                .builder()
                .id(updatedGame.getId())
                .gameName(updatedGame.getName())
                .status(updatedGame.getStatus())
                .createdAt(updatedGame.getCreatedAt())
                .updatedAt(updatedGame.getUpdatedAt())
                .build();

        return ResponseEntity.ok(response);
    }
}
