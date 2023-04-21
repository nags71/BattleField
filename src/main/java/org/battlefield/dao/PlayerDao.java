package org.battlefield.dao;

import org.battlefield.model.BattleField;
import org.battlefield.model.Player;

import java.util.List;

public interface PlayerDao extends BaseDao<Player> {
    List<BattleField> getAllGamesPlayed();
    List<BattleField> getGamesAsWinner();
}
