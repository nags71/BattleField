package org.battlefield.dao.inmemory;

import org.battlefield.dao.BattleFieldDao;
import org.battlefield.model.BattleField;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DefaultBattleFieldDao implements BattleFieldDao {
    private final Map<String, BattleField> games = new HashMap<>();

    @Override
    public void save(BattleField game) {
        games.putIfAbsent(game.getId(), game);
    }

    @Override
    public BattleField get(String id) {
        validateIfGamePresent(games.get(id));
        return games.get(id);
    }

    @Override
    public BattleField update(BattleField game) {
        validateIfGamePresent(game);
        return games.put(game.getId(), game);
    }

    @Override
    public void delete(String id) {
        validateIfGamePresent(games.get(id));
        games.remove(id);
    }

    @Override
    public List<BattleField> getAllGames() {
        throw new NotImplementedException();
    }

    private void validateIfGamePresent(BattleField battleField) {
        Optional.ofNullable(battleField).orElseThrow(() -> new RuntimeException("Requested game doesn't exist"));
    }
}
