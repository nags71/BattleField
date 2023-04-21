package org.battlefield.service;

import org.battlefield.model.BattleField;

import java.util.List;

public interface GameService {
    void initGame(int N, String id, List<String> players);
    void startGame(String id);
    void displayBattleField(String id);
    BattleField getBattleField(String gameId);
    BattleField updateBattleField(BattleField battleField);
}
