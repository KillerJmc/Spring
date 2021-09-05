package com.jmc.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jmc.product.api.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jmc
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
