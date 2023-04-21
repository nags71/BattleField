package org.battlefield.controller;

import lombok.AllArgsConstructor;
import org.battlefield.model.BattleField;
import org.battlefield.service.GameService;

import java.util.List;

@AllArgsConstructor
public class GameController {
    private final GameService gameService;

    public void viewBattleField(String id) {
        gameService.displayBattleField(id);
    }

    public void initGame(int N, String id, List<String> players) {
        gameService.initGame(N, id, players);
    }
    public void startGame(String id) {
        gameService.startGame(id);
    }

    public BattleField getBattleField(String id) {
        return gameService.getBattleField(id);
    }
}
