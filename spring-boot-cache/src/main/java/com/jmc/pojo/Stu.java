package com.jmc.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Stu implements Serializable {
    private Integer id;
    private String name;
    private String password;
}
