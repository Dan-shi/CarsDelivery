/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.enumeration.ResultInfo;
import com.boyuan.delivery.model.Order;

/**
 * Car order service
 */
public interface CarOrderService {

    ResultInfo createOrder(Order order);

    ResultInfo updateOrder(Order order);

    ResultInfo deleteOrderById(long orderId);

    Order getOrderById(long orderId);

}
