/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.common;

import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.model.Result;

import java.sql.Timestamp;

/**
 * This class is used generated result for each controller
 */
public class ResultUtils {

    /**
     * Buld result for given parameter
     * @param resultCode
     * @param resultBody
     * @return
     */
    public static Result buildResult(int resultCode, Object resultBody){
        return Result
                .builder()
                .resultCode(resultCode)
                .returnTime(new Timestamp(System.currentTimeMillis()))
                .resultBody(resultBody)
                .build();
    }
}
