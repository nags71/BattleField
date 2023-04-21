package org.battlefield.controller;

import lombok.AllArgsConstructor;
import org.battlefield.model.Player;
import org.battlefield.service.PlayerService;

@AllArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    public void addPlayer(Player player) {
        playerService.addPlayer(player);
    }

    public Player getPlayer(String id) {
        return playerService.getPlayer(id);
    }
}
