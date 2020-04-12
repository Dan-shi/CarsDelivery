/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.enumeration;

public enum CommonResult implements ResultInfo {
    SUCCESS(100, "SUCCESS"),
    CREATE_ERROR(-100, "CREATE_ERROR"),
    UPDATE_ERROR(-101, "UPDATE_ERROR"),
    DELETE_ERROR(-102, "DELETE_ERROR"),
    RETURN_ERROR(-103, "RETURN_ERROR"),
    VALIDATION_ERROR(-104, "VALIDATION_ERROR"),
    UNEXPECTED_ERROR(-105, "UNEXPECTED_ERROR");


    private int code;

    private String message;

    CommonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public boolean equal(ResultInfo result) {
        if (this.getCode() == result.getCode() && this.getMessage().equals(result.getMessage())) {
            return true;
        }
        return false;
    }
}
