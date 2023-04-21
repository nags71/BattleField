package org.battlefield.service;

import org.battlefield.model.Player;

public interface PlayerService {
    void addPlayer(Player player);
    Player getPlayer(String id);
    Player updatePlayer(Player player);
    void deletePlayer(String id);
}
