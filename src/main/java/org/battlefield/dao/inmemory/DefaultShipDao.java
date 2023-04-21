package org.battlefield.dao.inmemory;

import org.battlefield.dao.BaseDao;
import org.battlefield.model.Ship;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultShipDao implements BaseDao<Ship> {
    private final Map<String, Ship> ships = new HashMap<>();
    @Override
    public void save(Ship ship) {
        ships.putIfAbsent(ship.getId(), ship);
    }

    @Override
    public Ship get(String id) {
        return ships.get(id);
    }

    @Override
    public Ship update(Ship ship) {
        validateIfShipPresent(ship);
        return ships.put(ship.getId(), ship);
    }

    private void validateIfShipPresent(Ship ship) {
        Optional.ofNullable(ship).orElseThrow(() -> new RuntimeException("Ship is not available"));
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Ship once placed can't be moved");
    }
}
