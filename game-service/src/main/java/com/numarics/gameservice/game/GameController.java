package com.numarics.gameservice.game;

import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.requests.GameRequest;
import com.numarics.gameservice.game.responses.GameResponse;
import com.numarics.gameservice.game.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<Game>> getAllGames(
            GameRequest gameRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Game> response = gameService.getGames(
                gameRequest.getName(), gameRequest.getStatus(), pageable
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/player/{playerName}")
    public ResponseEntity<GameResponse> getAllGames(@PathVariable String playerName) {

        GameResponse response = gameService.getGameByPlayerName(playerName);

        return ResponseEntity.ok(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<GameResponse> game(@PathVariable Long id) {
        GameResponse response = gameService.getGameById(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}
