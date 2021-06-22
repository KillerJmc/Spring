package com.jmc.mapper;

import com.jmc.domain.Stu;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StuMapper {
    /**
     * 一对一所需
     */
    @Select("select * from stu where id=#{id}")
    Stu getStuById(int id);

    /**
     * 一对多
     */
    @Select("select * from stu")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
            @Result(column = "id", property = "orderList",
                    many = @Many(select = "com.jmc.mapper.OrderMapper.getOrderBySid"))
    })
    List<Stu> getAllWithOrders();

    /**
     * 多对多
     */
    @Select("select * from stu")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
            @Result(column = "id", property = "roleList",
                    many = @Many(select = "com.jmc.mapper.RoleMapper.getRoleByStuId"))
    })
    List<Stu> getAllWithRoles();

    @Select("select * from stu")
    List<Stu> getAllStu();

    @Insert("insert into stu(name, password) values(#{name}, #{password})")
    void saveStu(Stu s);

    @Update("update stu set name=#{name}, password=#{password} where id=#{id}")
    void updateStuById(Stu s);

    @Delete("delete from stu where id=#{id}")
    void deleteStuById(int id);
}
