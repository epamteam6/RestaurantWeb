package com.dao;

import com.model.Dish;

import java.util.List;


public interface RestrauntDao {

    List<Dish> getAllDishes();

    void addDish(Dish dish);
    void updateDish (Dish dish);
    void deleteDish (long id);

}
