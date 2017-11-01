package com.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Order {
    private long id;
    private long userId;
    private LocalDateTime dateTime;
    private long totalSum;
    private Status status;

    public enum Status {
        CREATED, CONFIRMED, READY, PAID
    }
}
