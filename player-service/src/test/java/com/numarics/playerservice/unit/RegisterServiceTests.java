package com.numarics.playerservice.unit;

import com.numarics.playerservice.player.entities.Player;
import com.numarics.playerservice.player.repositories.PlayerRepository;
import com.numarics.playerservice.register.requests.RegisterPlayerRequest;
import com.numarics.playerservice.register.services.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegisterServiceTests {

    public static final String PLAYER_NAME = "John Doe";
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RegisterService registerService;

    @BeforeEach
    void setup() {
        Player player = new Player();
        player.setId(1L);
        player.setName(PLAYER_NAME);
        player.setGameId(456L);

        playerRepository.save(player);
    }

    @Test
    public void testRegisterPlayer_withExistingPlayer() {
        // Given
        Long gameId = 123L;
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(PLAYER_NAME, gameId);

        // When
        Player result = registerService.registerPlayer(registerPlayerRequest);

        // Then
        assertNotNull(result);
        assertEquals(PLAYER_NAME, result.getName());
        assertEquals(gameId, result.getGameId());
    }

    @Test
    public void testRegisterPlayer_withNewPlayer() {
        // Given
        String playerName = "Jane Doe";
        long gameId = 789;
        RegisterPlayerRequest registerPlayerRequest = new RegisterPlayerRequest(playerName, gameId);

        // When
        Player result = registerService.registerPlayer(registerPlayerRequest);

        // Then
        assertNotNull(result);
        assertEquals(playerName, result.getName());
        assertEquals(gameId, result.getGameId());
    }
}

