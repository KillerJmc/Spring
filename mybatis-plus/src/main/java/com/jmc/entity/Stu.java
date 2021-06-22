package com.jmc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Stu {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    /**
     * 设置该字段在表中的对应名称
     * 并且设置不被select（做到数据保护）
     */
    @TableField(value = "password", select = false)
    private String pwd;

    /**
     * 设置该字段在表中不存在
     */
    @TableField(exist = false)
    private String no;

    public Stu(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }
}
