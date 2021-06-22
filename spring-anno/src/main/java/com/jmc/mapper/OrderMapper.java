package com.jmc.mapper;

import com.jmc.domain.Order;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    /**
     * 一对一
     */
    @Select("select * from orders")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "total", property = "total"),
            @Result(column = "sid", property = "stu",
                    one = @One(select = "com.jmc.mapper.StuMapper.getStuById"))
    })
    List<Order> getAllWithStu();

    /**
     * 一对多所需
     */
    @Select("select * from orders where sid=#{sid}")
    List<Order> getOrderBySid(int sid);
}
