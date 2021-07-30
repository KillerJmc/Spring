package com.jmc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jmc.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jmc
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
