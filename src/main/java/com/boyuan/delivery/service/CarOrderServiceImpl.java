/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.common.utility.ValidationUtils;
import com.boyuan.delivery.enumeration.CommonResult;
import com.boyuan.delivery.enumeration.OrderResult;
import com.boyuan.delivery.enumeration.ResultInfo;
import com.boyuan.delivery.mapper.CarOrderMapper;
import com.boyuan.delivery.model.Order;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarOrderServiceImpl implements CarOrderService {

    protected final Log logger = LogFactory.getLog(CarOrderServiceImpl.class);


    @Autowired
    private CarOrderMapper carOrderMapper;

    @Autowired
    private OrderCache orderCache;

    @Transactional(rollbackFor = {RuntimeException.class, Error.class, Exception.class}, timeout = 300)
    @Override
    public ResultInfo createOrder(Order order) {
        if (!ValidationUtils.validatorObjInfo(order)) {
            return CommonResult.VALIDATION_ERROR;
        }

        if (this.orderCache.getOrderFromCache(order) != null) {
            return OrderResult.ORDER_EXIST;
        }

        if (this.carOrderMapper.createOrder(order) <= 0) {
            return CommonResult.CREATE_ERROR;
        }

        this.orderCache.putOrderToCache(order);
        return CommonResult.SUCCESS;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class, Exception.class}, timeout = 300)
    @Override
    public ResultInfo updateOrder(Order order) {
        if (!ValidationUtils.validatorObjInfo(order) || order.getOrderId() == null) {
            return CommonResult.VALIDATION_ERROR;
        }

        if (this.carOrderMapper.updateOrder(order) <= 0) {
            return CommonResult.UPDATE_ERROR;
        }

        return CommonResult.SUCCESS;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class, Exception.class}, timeout = 300)
    @Override
    public ResultInfo deleteOrderById(long orderId) {
        if (this.carOrderMapper.deleteOrderById(orderId) <= 0) {
            return CommonResult.DELETE_ERROR;
        }

        return CommonResult.SUCCESS;
    }

    @Override
    public Order getOrderById(long orderId) {
        return this.getOrderById(orderId);
    }
}
