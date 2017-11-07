package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
    private long id;
    private String dishname;
    private long dishTypeId;
    private long price;
}
