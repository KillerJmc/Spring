package com.jmc.domain;

import lombok.Data;

@Data
public class Order {
    private int id;
    private double total;
    private Stu stu;
}
