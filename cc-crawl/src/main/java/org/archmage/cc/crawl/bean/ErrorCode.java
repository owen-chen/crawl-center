/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-18
 * File Name      : ErrorCode.java
 */

package org.archmage.cc.crawl.bean;

/**
 * error code
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-18
 */
public enum ErrorCode {
    /** the url has been crawled */
    VISITED(100),

    /** no crawl status */
    NO_CRAWL_STATUS(200),

    /** the crawl could not be run */
    UNRUNNABLE_CRAWL(201);

    /** value */
    private int value;

    /**
     * constructor
     * 
     * @param value
     *            value
     */
    private ErrorCode(int value) {
        this.value = value;
    }

    /**
     * getter method
     * 
     * @see ErrorCode#value
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
