package com.numarics.playerservice;

import com.github.javafaker.Faker;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Player Service API",
                version = "1.0",
                description = "This is a Numarics player-service API"
        )
)
public class PlayerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerServiceApplication.class, args);
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
