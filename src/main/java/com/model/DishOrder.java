package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DishOrder {

    private long id;
    private long orderId;
    private long dishId;
    private long dishAmount;
    private long dishSum;
}
