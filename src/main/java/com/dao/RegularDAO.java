package com.dao;

import com.model.Entity;

public interface RegularDAO<T extends Entity> extends DAO {

      boolean create(T t);

      boolean remove(long id);

      boolean update(T t);
}
