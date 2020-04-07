/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.controller;

import com.boyuan.delivery.common.ResultUtils;
import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.model.Order;
import com.boyuan.delivery.model.Result;
import com.boyuan.delivery.service.CarOrderService;
import com.boyuan.delivery.service.EmailService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param order
     * @return
     */
    @PostMapping("create")
    public Result createOrder(@RequestBody Order order){

        try {
            int result = this.carOrderService.createOrder(order);
            System.out.println("order id: "+order.getOrderId());
            if(result > 0){
                //send email
//                this.emailService.sendOrderEmail(order);

            }
            return ResultUtils.buildResult(CommonConstant.Status.SUCCESS, result);
        } catch (Exception e){
            logger.error("createOrder error", e);
            return ResultUtils.buildResult(CommonConstant.Status.ERROR, null);
        }

    }

}
