/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.enumeration;

public enum OrderResult implements ResultInfo{

    ORDER_EXIST(-201, "ORDER_EXIST");

    private int code;

    private String message;

    OrderResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public boolean equal(ResultInfo result) {
        return false;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
