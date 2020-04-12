/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.controller;

import com.boyuan.delivery.common.ResultUtils;
import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.constant.CommonConstant.UserRole;
import com.boyuan.delivery.enumeration.CommonResult;
import com.boyuan.delivery.enumeration.ResultInfo;
import com.boyuan.delivery.model.Order;
import com.boyuan.delivery.model.Result;
import com.boyuan.delivery.service.CarOrderService;
import com.boyuan.delivery.service.EmailService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    protected final Log logger = LogFactory.getLog(OrderController.class);

    @Autowired
    private CarOrderService carOrderService;

    @Autowired
    private EmailService emailService;

    /**
     * Create new order
     *
     * @param order
     * @return
     */
    @Secured(UserRole.USER)
    @PostMapping("create")
    public Result createOrder(@RequestBody Order order) {

        try {
            ResultInfo result = this.carOrderService.createOrder(order);
            if (result == CommonResult.SUCCESS) {
                //send email
//                this.emailService.sendOrderEmail(order);

            }
            return ResultUtils.buildResultByResultInfo(result);
        } catch (Exception e) {
            logger.error("CreateOrder error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }

    }

    /**
     * Update order
     *
     * @param order
     * @return
     */
    @Secured(UserRole.ADMIN)
    @PostMapping("update")
    public Result updateOrder(@RequestBody Order order) {

        try {
            ResultInfo result = this.carOrderService.updateOrder(order);
            return ResultUtils.buildResultByResultInfo(result);
        } catch (Exception e) {
            logger.error("UpdateOrder error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }

    }

    /**
     * Delete order
     *
     * @param orderId
     * @return
     */
    @Secured(UserRole.ADMIN)
    @GetMapping("delete")
    public Result deleteOrder(@PathVariable(value = "orderId") Long orderId) {

        try {
            ResultInfo result = this.carOrderService.deleteOrderById(orderId);
            return ResultUtils.buildResultByResultInfo(result);
        } catch (Exception e) {
            logger.error("DeleteOrder error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }

    }

    /**
     * Get order by order id
     *
     * @param orderId
     * @return
     */
    @Secured(UserRole.USER)
    @GetMapping("getOrder")
    public Result getOrderById(@PathVariable(value = "orderId") Long orderId) {
        try {
            return ResultUtils.buildResultWithBody(this.carOrderService.getOrderById(orderId));
        } catch (Exception e) {
            logger.error("Get order error", e);
            return ResultUtils.buildErrorResult(e.getMessage());
        }
    }

}
