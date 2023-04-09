package com.numarics.gameservice.game.repositories;

import com.numarics.gameservice.game.entities.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Page<Game> findByNameAndStatus(String name, String status, Pageable pageable);

    Page<Game> findByName(String name, Pageable pageable);

    Page<Game> findByStatus(String status, Pageable pageable);
}

