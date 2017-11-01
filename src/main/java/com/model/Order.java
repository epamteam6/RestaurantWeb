package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Order {
    private int id;
    private int userId;
    private LocalDateTime dateTime;
    private int totalSum;
    private Status status;

    public enum Status {
        CREATED, CONFIRMED, READY, PAID
    }
}
