/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.common.exception;

/**
 * The json exception
 */
public class JsonException extends BynRuntimeException {
    /**
     * The constructor
     *
     * @param message
     */
    public JsonException(String message) {
        super(message);
    }

    /**
     * The constructor
     *
     * @param message
     * @param cause
     */
    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * The constructor
     *
     * @param cause
     */
    public JsonException(Throwable cause) {
        super(cause);
    }
}
