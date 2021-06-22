package com.jmc.mapper;

import com.jmc.domain.Role;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    /**
     * 多对多所需
     */
    @Select("select * from role r, stu_role sr where role_id = r.id and stu_id=#{stuId}")
    @Results({
            @Result(column = "role_id", property = "id"),
            @Result(column = "role_name", property = "name")
    })
    List<Role> getRoleByStuId(int stuId);
}
