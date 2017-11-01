package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish implements Entity {
    private long id;
    private String dish;
    private long dishTypeId;
    private long price;
}
