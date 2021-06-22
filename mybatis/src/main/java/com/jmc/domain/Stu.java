package com.jmc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stu {
    private int id;
    private String name;
    private String password;
    private List<Order> orderList;
    private List<Role> roleList;

    public Stu(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
