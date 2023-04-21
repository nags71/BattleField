package org.battlefield.strategy.impl;

import lombok.AllArgsConstructor;
import org.battlefield.dao.BaseDao;
import org.battlefield.dao.BattleFieldDao;
import org.battlefield.model.BattleField;
import org.battlefield.model.Missile;
import org.battlefield.model.Ship;
import org.battlefield.service.GameService;
import org.battlefield.strategy.BattleStrategy;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DefaultBattleStrategy implements BattleStrategy {
    private final BaseDao<Missile> missileDao;
    private final BaseDao<Ship> shipDao;
    private final BattleFieldDao battleFieldDao;
    @Override
    public void play(String battleFieldId) {
        BattleField battleField = battleFieldDao.get(battleFieldId);
        boolean[][] visited = new boolean[battleField.getGrid().length][battleField.getGrid().length];
        boolean playA = true;
        Random random = new Random();
        do {
            int x = 1;
            int y = 1;
            do {
                x = random.nextInt(battleField.getGrid().length);
                y = random.nextInt(battleField.getGrid().length);
            } while (visited[x][y]);
            visited[x][y] = true;
            String playerId = battleField.getPlayers().get(playA ? 0 : 1);
            playA = !playA;
            Missile missile = new Missile(UUID.randomUUID().toString(), x, y, playerId,battleField.getId());
            missileDao.save(missile);
            battleField.getMissiles().add(missile.getId());
            fireMissile(x, y, battleField);
        } while (!isGameComplete(battleField));
    }

    private void fireMissile(int x, int y, BattleField battleField) {
        String[][] grid = battleField.getGrid();
        String point = grid[x][y];
        if (!point.equals(".") && !point.equals("D")) {
            Ship ship = shipDao.get(point);
            ship.setIsDestroyed();
            shipDao.update(ship);
            for (int i = ship.getX() - 1; i < ship.getX() + ship.getSize() - 1; i++) {
                for (int j = ship.getY() - 1; j < ship.getY() + ship.getSize() - 1; j++) {
                    grid[i][j] = "D";
                }
            }
        }
        battleField.setGrid(grid);
        battleFieldDao.update(battleField);
    }

    private boolean isGameComplete(BattleField battleField) {
        boolean gameCompleted = false;
        for (String playerId : battleField.getPlayers()) {
            gameCompleted = gameCompleted || battleField.getShips()
                    .stream()
                    .filter(x -> shipDao.get(x).getPlayerId().equals(playerId))
                    .allMatch(x -> shipDao.get(x).isDestroyed());
            if (gameCompleted) {
                battleField.setLoserId(playerId);
                return true;
            }
        }
        return gameCompleted;
    }
}
