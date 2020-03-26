/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.Order;

public interface CarOrderMapper {

    int createOrder(Order order);

    int updateOrder(Order order);

    int deleteOrderById(long orderId);

    Order getOrderById(long orderId);
}
