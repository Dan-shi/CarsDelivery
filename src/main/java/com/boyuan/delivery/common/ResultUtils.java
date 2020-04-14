/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.common;

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
        //TODO replace message with proper field
        return buildResult(CommonResult.UNEXPECTED_ERROR.getCode(), CommonResult.UNEXPECTED_ERROR.getMessage(), null);
    }

    /**
     * Build result which only have result code and result message
     *
     * @param result
     * @return
     */
    public static Result buildResultByResultInfo(ResultInfo result) {
        return buildResult(result.getCode(), result.getMessage(), null);
    }

    /**
     * Build result which has result bode inside
     *
     * @param resultBody
     * @return
     */
    public static Result buildResultWithBody(Object resultBody) {
        return buildResult(CommonResult.SUCCESS.getCode(), CommonResult.SUCCESS.getMessage(), resultBody);
    }

}
