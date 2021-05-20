package com.jmc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jmc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private int age;
    private String password;

    public User(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
}
