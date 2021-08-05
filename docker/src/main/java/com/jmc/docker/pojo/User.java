package com.jmc.docker.pojo;

import lombok.Data;

/**
 * @author Jmc
 */
@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String password;
}
