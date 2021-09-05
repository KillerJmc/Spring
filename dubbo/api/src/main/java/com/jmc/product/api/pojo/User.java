package com.jmc.product.api.pojo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jmc
 */
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer age;
    private String password;
}
