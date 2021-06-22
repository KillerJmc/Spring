package com.jmc.mapper;

import com.jmc.domain.Order;

import java.util.List;

public interface OrderMapper {
    /**
     * 一对一
     */
    List<Order> getAllWithStu();
}
