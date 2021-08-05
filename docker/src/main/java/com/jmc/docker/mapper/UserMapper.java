package com.jmc.docker.mapper;

import com.jmc.docker.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jmc
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
