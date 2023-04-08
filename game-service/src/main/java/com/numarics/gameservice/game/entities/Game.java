package com.numarics.gameservice.game.entities;

import com.numarics.gameservice.game.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Basic
    @Size(max = 25)
    private String status;

    @Transient
    private GameStatus gameStatusEnum;

    @PostLoad
    void fillTransient() {
        if (status != null) {
            this.gameStatusEnum = GameStatus.of(status);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (gameStatusEnum != null) {
            this.status = gameStatusEnum.getGameStatus();
        }

        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

