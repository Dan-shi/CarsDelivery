/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.common;

import com.alibaba.fastjson.JSONObject;
import com.boyuan.delivery.constant.CommonConstant;
import com.boyuan.delivery.enumeration.CommonResult;
import com.boyuan.delivery.enumeration.ResultInfo;
import com.boyuan.delivery.model.Result;

import java.sql.Timestamp;

/**
 * This class is used generated result for each controller
 */
public class ResultUtils {

    /**
     * Buld result for given parameter
     *
     * @param resultCode
     * @param resultMessage
     * @param resultBody
     * @return
     */
    public static Result buildResult(int resultCode, String resultMessage, Object resultBody) {
        return Result
                .builder()
                .resultCode(resultCode)
                .resultMessage(resultMessage)
                .returnTime(new Timestamp(System.currentTimeMillis()))
                .resultBody(resultBody)
                .build();
    }

    /**
     * Build unexpected error result
     *
     * @return
     */
    public static Result buildErrorResult(String message) {
        return ResultUtils.buildResult(CommonResult.UNEXPECTED_ERROR.getCode(), message, null);
    }

    /**
     * Build result which only have result code and result message
     *
     * @param result
     * @return
     */
    public static Result buildResultByResultInfo(ResultInfo result) {
        return ResultUtils.buildResult(result.getCode(), result.getMessage(), null);
    }

    /**
     * Build result which has result bode inside
     *
     * @param resultBody
     * @return
     */
    public static Result buildResultWithBody(Object resultBody) {
        return ResultUtils.buildResult(CommonResult.SUCCESS.getCode(), CommonResult.SUCCESS.getMessage(), resultBody);
    }

}
