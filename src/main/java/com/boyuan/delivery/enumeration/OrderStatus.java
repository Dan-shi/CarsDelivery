/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.enumeration;

public enum OrderStatus {

    SUBMIT("SUBMIT", "提交", 0),
    START("START", "开始", 1),
    PROCESSING("PROCESSING", "进行中", 2),
    FINISH("FINISH", "完成", 3),
    CANCEL("CANCEL", "取消", 4);

    private String key;
    private String desc;
    private int value;

    OrderStatus(String key, String desc, int value) {
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
