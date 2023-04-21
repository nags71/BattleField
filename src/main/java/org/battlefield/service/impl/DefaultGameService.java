package org.battlefield.service.impl;

import lombok.AllArgsConstructor;
import org.battlefield.dao.BattleFieldDao;
import org.battlefield.model.BattleField;
import org.battlefield.service.GameService;
import org.battlefield.strategy.BattleStrategy;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DefaultGameService implements GameService {
    private final BattleFieldDao battleFieldDao;
    private final BattleStrategy battleStrategy;

    @Override
    public void initGame(int N, String id, List<String> players) {
        String[][] grid = new String[N][N];
        fillGridWithWater(grid);
        BattleField battleField = new BattleField(id, new ArrayList<>(), new ArrayList<>(), grid, players, "");
        battleFieldDao.save(battleField);
    }

    @Override
    public void startGame(String id) {
        displayBattleField(id);
        battleStrategy.play(id);
        displayBattleField(id);
    }
    @Override
    public BattleField updateBattleField(BattleField battleField) {
        return battleFieldDao.update(battleField);
    }

    @Override
    public void displayBattleField(String id) {
        String[][] grid = battleFieldDao.get(id).getGrid();
        System.out.println("\n=========********************************====\n");
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(grid[row][col] +  "  ");
            }
            System.out.println();
        }
    }

    @Override
    public BattleField getBattleField(String gameId) {
        return battleFieldDao.get(gameId);
    }

    private void fillGridWithWater(String[][] grid) {
        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = ".";
            }
        }
    }
}
