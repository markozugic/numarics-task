package com.numarics.playerservice.register;

import com.numarics.playerservice.player.entities.Player;
import com.numarics.playerservice.register.requests.RegisterPlayerRequest;
import com.numarics.playerservice.register.responses.RegisterPlayerResponse;
import com.numarics.playerservice.register.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<RegisterPlayerResponse> register(
            @Valid @RequestBody RegisterPlayerRequest registerPlayerRequest
    ) {
        Player player = registerService.registerPlayer(registerPlayerRequest);

        RegisterPlayerResponse response = RegisterPlayerResponse
                .builder()
                .id(player.getId())
                .name(player.getName())
                .gameId(player.getGameId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
