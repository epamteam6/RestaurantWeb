package com.service;

import com.dao.*;
import com.model.Dish;
import com.model.DishType;

import java.util.*;

public class MenuService {


    private DishTypeDAO dishTypeDAO = DishTypeDAO.getInstance();
    private DishDAO dishDAO = DishDAO.getInstance();
    private static MenuService instance;

    private MenuService() {
    }


    public void setDishTypeDAO(DishTypeDAO dishTypeDAO) {
        this.dishTypeDAO = dishTypeDAO;
    }

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public static MenuService getInstance() {
        if (instance == null) {
            instance = new MenuService();
        }
        return instance;
    }

    public Map<String, Map<String, Long>> getMenu() {
        Map<String, Map<String, Long>> menu = new HashMap<>();
        List<Dish> allDishes = dishDAO.getAll();
        List<DishType> allDishTypes = dishTypeDAO.getAll();

        Set<Long> allDishTypesNumbers = new HashSet<>();
        for (DishType dishType : allDishTypes) {
            allDishTypesNumbers.add(dishType.getId());
        }

        for (Long type : allDishTypesNumbers) {
            Map<String, Long> submenu = new HashMap<>();
            Optional<DishType> dishType = dishTypeDAO.getById(type);
            if (dishType.isPresent()) {
                menu.put(dishType.get().getDishType(), submenu);
            } else throw new NoSuchElementException("There is no such dishname type!");
        }

        for (Dish dish : allDishes) {
            if (dishTypeDAO.getById(dish.getDishTypeId()).isPresent()) {
                String dishTypeName = dishTypeDAO.getById(dish.getDishTypeId()).get().getDishType();
                Map<String, Long> submenu = menu.get(dishTypeName);
                submenu.put(dish.getDishname(), dish.getPrice());
                menu.put(dishTypeName, submenu);
            } else throw new NoSuchElementException("There is no such dishname type!");
        }
        return menu;
    }
}
