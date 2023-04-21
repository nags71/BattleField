package org.battlefield.dao.inmemory;

import org.battlefield.dao.BaseDao;
import org.battlefield.model.Missile;

import java.util.HashMap;
import java.util.Map;

public class DefaultMissileDao implements BaseDao<Missile> {
    private final Map<String, Missile> missiles = new HashMap<>();
    @Override
    public void save(Missile missile) {
        missiles.putIfAbsent(missile.getId(), missile);
    }

    @Override
    public Missile get(String id) {
        return missiles.get(id);
    }

    @Override
    public Missile update(Missile obj) {
        throw new UnsupportedOperationException("Missile once fired can't be modified");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Missile once fired can't be undone");
    }
}
