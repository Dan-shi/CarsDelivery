/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.common.exception;

/**
 * The Byn Runtime Exception, all the unchecked exception defined in Byn should extend this exception type.
 */
public class BynRuntimeException extends RuntimeException {
    /**
     * The constructor
     *
     * @param message
     */
    public BynRuntimeException(String message) {
        super(message);
    }

    /**
     * The constructor
     *
     * @param message
     * @param cause
     */
    public BynRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * The constructor
     *
     * @param cause
     */
    public BynRuntimeException(Throwable cause) {
        super(cause);
    }
}
