package org.battlefield.service.impl;

import lombok.AllArgsConstructor;
import org.battlefield.dao.PlayerDao;
import org.battlefield.model.Player;
import org.battlefield.service.PlayerService;

@AllArgsConstructor
public class DefaultPlayerService implements PlayerService {
    private final PlayerDao playerDao;
    @Override
    public void addPlayer(Player player) {
        playerDao.save(player);
    }

    @Override
    public Player getPlayer(String id) {
        return playerDao.get(id);
    }

    @Override
    public Player updatePlayer(Player player) {
        return playerDao.update(player);
    }

    @Override
    public void deletePlayer(String id) {
        playerDao.delete(id);
    }
}
