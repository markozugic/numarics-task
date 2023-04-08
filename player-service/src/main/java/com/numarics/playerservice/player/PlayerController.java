package com.numarics.playerservice.player;

import com.numarics.playerservice.player.responses.PlayerResponse;
import com.numarics.playerservice.player.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<PlayerResponse> getByName(@PathVariable String name) {
        PlayerResponse response = playerService.getPlayer(name);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}
