/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.model.Order;

/**
 * Car order service
 */
public interface CarOrderService {

    int createOrder(Order order);

    int updateOrder(Order order);

    int deleteOrder(long orderId);

}
