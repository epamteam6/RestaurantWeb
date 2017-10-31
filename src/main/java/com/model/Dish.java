package com.model;

import lombok.Data;

@Data
public class Dish {
    private int id;
    private String dish;
    private String dishTypeId;
    private int price;
}
