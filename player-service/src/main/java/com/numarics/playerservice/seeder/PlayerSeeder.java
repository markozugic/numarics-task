package com.numarics.playerservice.seeder;

import com.github.javafaker.Faker;
import com.numarics.playerservice.player.entities.Player;
import com.numarics.playerservice.player.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

@Component
public class PlayerSeeder implements ApplicationRunner {

    private final PlayerRepository playerRepository;

    private final Faker faker;

    @Autowired
    public PlayerSeeder(PlayerRepository playerRepository, Faker faker) {
        this.playerRepository = playerRepository;
        this.faker = faker;
    }

    @Override
    public void run(ApplicationArguments args) {
        LongStream.range(1, 51).forEach(i -> {
            Player player = new Player();
            player.setName(faker.name().username());
            player.setGameId(i);
            playerRepository.save(player);
        });
    }
}


