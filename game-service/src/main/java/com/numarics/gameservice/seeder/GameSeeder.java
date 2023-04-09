package com.numarics.gameservice.seeder;

import com.github.javafaker.Faker;
import com.numarics.gameservice.game.entities.Game;
import com.numarics.gameservice.game.enums.GameStatus;
import com.numarics.gameservice.game.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Component
public class GameSeeder implements ApplicationRunner {
    private final GameRepository gameRepository;

    private final Faker faker;

    @Autowired
    public GameSeeder(GameRepository gameRepository, Faker faker) {
        this.gameRepository = gameRepository;
        this.faker = faker;
    }

    @Override
    public void run(ApplicationArguments args) {
        IntStream.range(1, 51).forEach(i -> {
            Game game = new Game();
            game.setName(faker.esports().game());
            game.setStatus(GameStatus.NEW.getGameStatus());
            game.setCreatedAt(LocalDateTime.now());
            game.setUpdatedAt(LocalDateTime.now());
            gameRepository.save(game);
        });
    }
}


