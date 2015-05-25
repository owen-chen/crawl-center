/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-10-29
 * File Name      : InfosourceErrorException.java
 */

package org.archmage.cc.crawl.exception;

import org.archmage.cc.crawl.bean.ErrorCode;

/**
 * crawl error exception
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-10-29
 */
public class CrawlErrorException extends Exception {
    /** Serial version UID */
    private static final long serialVersionUID = -2242424810841518598L;

    /** {@link ErrorCode} */
    private ErrorCode errorCode;

    /**
     * constructor
     * 
     * @param errorCode
     *            {@link ErrorCode}
     */
    public CrawlErrorException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * getter method
     * 
     * @see CrawlErrorException#errorCode
     * @return the errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
