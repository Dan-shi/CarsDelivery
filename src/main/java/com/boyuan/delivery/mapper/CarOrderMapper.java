/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.mapper;

import com.boyuan.delivery.model.Order;

import java.util.List;

public interface CarOrderMapper {

    int createOrder(Order order);

    int updateOrder(Order order);

    int deleteOrderById(long orderId);

    Order getOrderById(long orderId);
//TODO get orders
//    int getPageCount(boolean isActive, int limit);

//    int getAllPageCount(int limit);

//    List<Order> getOrdersPage(boolean isActive, int offset, int limit);

//    List<Order> getAllOrdersPage(int offset, int limit);

//    List<Order> getOrdersPageByOrderId(boolean isActive, long lastOrderId, int limit);

//    List<Order> getAllOrdersByOrderId(long lastOrderId, int limit);

//    List<Order> getOrdersPageByCusPhone(boolean isActive, String phone, int offset, int limit);

//    List<Order> getAllOrdersPageByCusPhone(String phone, int offset, int limit);

//    List<Order> getOrdersPageByStatus(boolean isActive, Integer status, int offset, int limit);

//    List<Order> getAllOrdersPageByStatus(Integer status, int offset, int limit);
}
