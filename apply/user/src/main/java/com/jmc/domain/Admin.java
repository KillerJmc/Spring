package com.jmc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jmc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private int id;
    private String name;
    private String password;
}
