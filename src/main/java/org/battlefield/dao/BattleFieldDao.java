package org.battlefield.dao;

import org.battlefield.model.BattleField;

import java.util.List;

public interface BattleFieldDao extends BaseDao<BattleField> {
    List<BattleField> getAllGames();
}
