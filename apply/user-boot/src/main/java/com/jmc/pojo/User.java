package com.jmc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jmc.config.valid.Group;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Jmc
 */
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @NotNull(message = "id不能为空", groups = Group.Update.class)
    private Integer id;

    @NotEmpty(message = "姓名不能为空", groups = {Group.Insert.class, Group.Update.class, Group.Login.class})
    private String name;

    @NotNull(message = "年龄不能为空", groups = {Group.Insert.class, Group.Update.class})
    private Integer age;

    @NotEmpty(message = "密码不能为空", groups = {Group.Insert.class, Group.Update.class, Group.Login.class})
    private String password;
}
