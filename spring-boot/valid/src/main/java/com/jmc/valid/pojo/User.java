package com.jmc.valid.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {
    @NotBlank(message = "姓名不能为空！")
    private String name;

    @NotNull(message = "密码不能为空！")
    private String password;
}