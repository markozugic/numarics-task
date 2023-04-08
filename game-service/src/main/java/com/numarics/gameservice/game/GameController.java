package com.numarics.gameservice.game;

import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.responses.GameResponse;
import com.numarics.gameservice.game.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("{id}")
    public ResponseEntity<GameResponse> game(@PathVariable Long id) {
        Game game = gameService.getGameById(id);

        GameResponse response = GameResponse
                .builder()
                .id(game.getId())
                .gameName(game.getName())
                .status(game.getStatus())
                .createdAt(game.getCreatedAt())
                .updatedAt(game.getUpdatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}
