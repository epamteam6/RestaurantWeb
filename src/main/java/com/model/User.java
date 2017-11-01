package com.model;

import lombok.*;

@AllArgsConstructor
@Data
public class User implements Entity {

    @Setter(AccessLevel.PRIVATE)
    private int id;

    @Setter(AccessLevel.PRIVATE)
    private String userName;

    private boolean isAdmin;
}
