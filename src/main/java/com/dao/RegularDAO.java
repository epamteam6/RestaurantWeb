package com.dao;

public interface RegularDAO<T> extends DAO {

      boolean create(T t);

      boolean remove(long id);

      boolean update(T t);
}
