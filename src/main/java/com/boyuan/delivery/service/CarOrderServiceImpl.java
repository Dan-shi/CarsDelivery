/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.common.SecurityUtils;
import com.boyuan.delivery.common.ValidationUtils;
import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.mapper.CarOrderMapper;
import com.boyuan.delivery.model.Order;
import com.boyuan.delivery.model.ValidationResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarOrderServiceImpl implements CarOrderService {

    protected final Log logger = LogFactory.getLog(CarOrderServiceImpl.class);


    @Autowired
    private CarOrderMapper carOrderMapper;

    @Override
    public int createOrder(Order order) {
        if (!this.validatorOrderInfo(order)) {
            return CommonConstant.Status.ERROR;
        }

        if (this.carOrderMapper.createOrder(order) > 0) {
            return CommonConstant.Status.SUCCESS;
        }

        return CommonConstant.Status.ERROR;
    }

    @Override
    public int updateOrder(Order order) {
        if (!this.validatorOrderInfo(order) || order.getOrderId() == null) {
            return CommonConstant.Status.ERROR;
        }

        if (this.carOrderMapper.updateOrder(order) > 0) {
            return CommonConstant.Status.SUCCESS;
        }

        return CommonConstant.Status.ERROR;
    }

    @Override
    public int deleteOrderById(long orderId) {
        if (this.carOrderMapper.deleteOrderById(orderId) > 0) {
            return CommonConstant.Status.SUCCESS;
        }

        return CommonConstant.Status.ERROR;
    }

    @Override
    public Order getOrderById(long orderId) {
        return this.getOrderById(orderId);
    }

    /**
     * Validate order field
     *
     * @param order
     * @return true: order is legal; false: order is not legal
     */
    private boolean validatorOrderInfo(Order order) {
        SecurityUtils.trimStringFieldOrSetNull(order);

        ValidationResult result = ValidationUtils.validateEntity(order);
        if (result.isHasErrors()) {
            logger.error("Order validation ERROR: " + result.getErrorMsg().toString());
            return false;
        }

        return true;
    }
}
