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
    UNRUNNABLE_CRAWL(201),

    /** two more stocks are found with the same symbol */
    TWO_MORE_SIMILAR_STOCKS(1000),

    TEMP_DATA_NOT_EXIST_IN_MONGODB(501), SUCCESS_RATE_NOT_EXCEED_THRESHOLD(502), RESPONSE_DATA_COULD_NOT_BE_EMPTY(503),

    NO_STOCK_INFO(2001), NO_HISTORY_TRADE_THIS_DAY(2100), UNKNOWN_HISTORY_TRADE_FEATURE(2101);

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
