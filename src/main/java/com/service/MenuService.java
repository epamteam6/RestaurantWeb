package com.service;

import com.dao.*;
import com.model.Dish;
import com.model.DishType;
import lombok.Data;

import java.util.*;


@Data
public class MenuService {

    private DishTypeDAO dishTypeDAO = DishTypeDAO.getInstance();
    private DishDAO dishDAO = DishDAO.getInstance();

    private static MenuService instance;

    private MenuService() {
    }

    public static MenuService getInstance() {
        if (instance == null) {
            instance = new MenuService();
        }
        return instance;
    }

    public Map<String, Map<String, Long>> getMenu() {

        List<DishType> allDishTypes = dishTypeDAO.getAll();
        Set<Long> allDishTypesNumbers = new HashSet<>();
        for (DishType dishType : allDishTypes) {
            allDishTypesNumbers.add(dishType.getId());
        }

        Map<String, Map<String, Long>> menu = new HashMap<>();
        for (Long type : allDishTypesNumbers) {
            Map<String, Long> submenu = new HashMap<>();
            Optional<DishType> dishType = dishTypeDAO.getById(type);
            if (dishType.isPresent()) {
                menu.put(dishType.get().getDishType(), submenu);
            } else throw new NoSuchElementException("There is no such dish type!");
        }

        List<Dish> allDishes = dishDAO.getAll();
        for (Dish dish : allDishes) {
            Optional<DishType> dishTypeById = dishTypeDAO.getById(dish.getDishTypeId());
            if (dishTypeById.isPresent()) {
                String dishTypeName = dishTypeById.get().getDishType();
                Map<String, Long> submenu = menu.get(dishTypeName);
                submenu.put(dish.getDishname(), dish.getPrice());
                menu.put(dishTypeName, submenu);
            } else throw new NoSuchElementException("There is no such dish type!");
        }
        return menu;
    }
}
