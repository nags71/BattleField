package org.battlefield.service.impl;

import lombok.AllArgsConstructor;
import org.battlefield.dao.BaseDao;
import org.battlefield.model.BattleField;
import org.battlefield.model.Ship;
import org.battlefield.service.GameService;
import org.battlefield.service.ShipService;

import java.util.List;

@AllArgsConstructor
public class DefaultShipService implements ShipService {
    private final GameService gameService;
    private final BaseDao<Ship> shipDao;
    @Override
    public boolean addShip(List<Ship> ships) {
        BattleField battleField = null;
        for (Ship ship : ships) {
            battleField = gameService.getBattleField(ship.getBattleFieldId());
            battleField.getShips().add(ship.getId());
            int size = ship.getSize();
            int x = ship.getX() - 1;
            int y = ship.getY() - 1;
            boolean isSuccess = addShip(battleField, ship.getId(), x, y, size);
            if (!isSuccess) {
                return false;
            }
        }
        if (battleField != null) {
            gameService.updateBattleField(battleField);
            for(Ship ship : ships) {
                shipDao.save(ship);
            }
        }
        return true;
    }

    private boolean addShip(BattleField battleField, String id, int x, int y, int size) {
        String[][] grid = battleField.getGrid();

        if ((x + size) > grid.length || (y + size) > grid[0].length) {
            return false;
        }

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                grid[i][j] = id;
            }
        }
        battleField.setGrid(grid);
        return true;
    }
}
