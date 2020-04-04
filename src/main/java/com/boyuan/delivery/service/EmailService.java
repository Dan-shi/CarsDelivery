/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.service;

import com.boyuan.delivery.model.Order;

public interface EmailService {

    void sendOrderEmail(Order order);
}
