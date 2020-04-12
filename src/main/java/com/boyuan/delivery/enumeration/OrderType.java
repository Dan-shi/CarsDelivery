/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.enumeration;

public enum OrderType {
    NORMAL("NORMAL", "正常", 0);

    private String key;
    private String desc;
    private int value;

    OrderType(String key, String desc, int value) {
        this.key = key;
        this.desc = desc;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public int getValue() {
        return value;
    }
}
