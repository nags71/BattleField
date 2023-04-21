package org.battlefield.dao;

import java.util.Optional;

public interface BaseDao<T> {
    void save(T obj);
    T get(String id);
    T update(T obj);
    void delete(String id);
}
