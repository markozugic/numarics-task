package com.numarics.playerservice.player.events;

import com.numarics.playerservice.player.entities.Player;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class PlayerDeletedEvent extends ApplicationEvent {
    private Player player;

    public PlayerDeletedEvent(Object source, Player player) {
        super(source);
        this.player = player;
    }
}

