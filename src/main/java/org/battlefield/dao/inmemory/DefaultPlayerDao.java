package org.battlefield.dao.inmemory;

import org.battlefield.dao.BaseDao;
import org.battlefield.dao.PlayerDao;
import org.battlefield.model.BattleField;
import org.battlefield.model.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DefaultPlayerDao implements PlayerDao {
    private final Map<String, Player> players = new HashMap<>();

    @Override
    public void save(Player obj) {
        players.putIfAbsent(obj.getId(), obj);
    }

    @Override
    public Player get(String id) {
        validateIfPlayerPresent(id);
        return players.get(id);
    }

    private void validateIfPlayerPresent(String id) {
        Optional.ofNullable(players.get(id)).orElseThrow(() -> new RuntimeException("player doesn't exist"));
    }

    @Override
    public Player update(Player obj) {
        validateIfPlayerPresent(obj.getId());
        return players.put(obj.getId(), obj);
    }

    @Override
    public void delete(String id) {
        validateIfPlayerPresent(id);
        players.remove(id);
    }

    @Override
    public List<BattleField> getAllGamesPlayed() {
        throw new NotImplementedException();
    }

    @Override
    public List<BattleField> getGamesAsWinner() {
        throw new NotImplementedException();
    }
}
