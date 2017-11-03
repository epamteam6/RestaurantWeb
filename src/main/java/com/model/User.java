package com.model;

import lombok.*;

@AllArgsConstructor
@Data
public class User {

    @Setter(AccessLevel.PRIVATE)
    private long id;

    @Setter(AccessLevel.PRIVATE)
    private String userName;

    private boolean isAdmin;
}
