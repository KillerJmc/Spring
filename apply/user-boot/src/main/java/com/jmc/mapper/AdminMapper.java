package com.jmc.mapper;

import com.jmc.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jmc
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from admin where name=#{name} and password=#{password}")
    Admin getByNameAndPassword(Admin a);
}
