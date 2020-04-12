/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.enumeration;

public interface ResultInfo {

    /**
     * Get result message
     *
     * @return
     */
    String getMessage();

    /**
     * Get result code
     *
     * @return
     */
    int getCode();

    /**
     * Check two result is equal or not
     *
     * @param result
     * @return
     */
    boolean equal(ResultInfo result);
}
