package com.dao;

import com.model.Entity;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public interface DAO <T extends Entity> {

    void setDataSource(DataSource dataSource);

    Optional<T> getById(long id);

    List<T> getAll();
}
