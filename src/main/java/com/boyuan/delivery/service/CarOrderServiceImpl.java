/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.mapper.CarOrderMapper;
import com.boyuan.delivery.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarOrderServiceImpl implements CarOrderService {

    @Autowired
    private CarOrderMapper carOrderMapper;

    @Override
    public int createOrder(Order order) {
        return this.carOrderMapper.createOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        return this.carOrderMapper.updateOrder(order);
    }

    @Override
    public int deleteOrderById(long orderId) {
        return this.carOrderMapper.deleteOrderById(orderId);
    }

    @Override
    public Order getOrderById(long orderId) {
        return this.getOrderById(orderId);
    }
}
