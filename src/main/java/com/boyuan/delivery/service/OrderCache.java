/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.model.Order;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OrderCache {

    @Cacheable(cacheNames = "orders", key = "#order.cusPhone + #order.fromLocation + #order.toLocation")
    public Order getOrderFromCache(Order order) {
        return null;
    }

    @CachePut(cacheNames = "orders", key = "#order.cusPhone + #order.fromLocation + #order.toLocation")
    public Order putOrderToCache(Order order) {
        return order;
    }
}
