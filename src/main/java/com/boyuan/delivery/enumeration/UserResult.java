/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.enumeration;

public enum UserResult implements ResultInfo {
    USER_EXIST(-301, "USER_EXIST");

    private int code;

    private String message;

    UserResult(int code, String message) {
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
