package com.jmc.mapper;

import com.jmc.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jmc
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where name=#{name}")
    User getByName(String name);

    @Select("select * from user where name=#{name} and password=#{password}")
    User getByNameAndPassword(User u);

    @Select("delete from user where name=#{name}")
    void deleteByName(String name);
}
