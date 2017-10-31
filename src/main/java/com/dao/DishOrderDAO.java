package com.dao;

import javax.sql.DataSource;

public class DishOrderDAO {

    private static DishOrderDAO instance;
    private DataSource dataSource;

    private DishOrderDAO() {

    }

    public static DishOrderDAO getInstance() {
        if (instance == null)
            instance = new DishOrderDAO();

        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}
