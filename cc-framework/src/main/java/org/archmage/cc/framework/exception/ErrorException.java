/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-10-11
 * File Name      : ErrorException.java
 */

package org.archmage.cc.framework.exception;

import org.archmage.cc.framework.bean.ErrorCode;

/**
 * error exception
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-10-11
 */
public class ErrorException extends Exception {
    /** Serial version UID */
    private static final long serialVersionUID = 423138987910192602L;

    /** {@link ErrorCode} */
    private ErrorCode errorCode;

    /**
     * constructor
     * 
     * @param errorCode
     *            {@link ErrorCode}
     */
    public ErrorException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * constructor
     * 
     * @param errorCode
     *            {@link ErrorCode}
     * @param errorMessage
     *            error message
     */
    public ErrorException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    /**
     * getter method
     * 
     * @see ErrorException#errorCode
     * @return the errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
