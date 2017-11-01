package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DishType implements Entity {

    private long id;
    private String dishType;
}
