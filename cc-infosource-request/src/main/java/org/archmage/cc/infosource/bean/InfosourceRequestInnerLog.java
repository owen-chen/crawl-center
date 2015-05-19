/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-17
 * File Name      : InfosourceRequestInnerLog.java
 */

package org.archmage.cc.infosource.bean;

import org.archmage.cc.framework.log.InnerLog;

/**
 * the log while requesting infosource
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-17
 */
public class InfosourceRequestInnerLog extends InnerLog {
    /** infosource code */
    private String code;

    /** elapsed time while requesting */
    private long requestElapsedTime;

    /** total elapsed time */
    private long totalElapsedTime;

    /**
     * getter method
     * 
     * @see InfosourceRequestInnerLog#requestElapsedTime
     * @return the requestElapsedTime
     */
    public long getRequestElapsedTime() {
        return requestElapsedTime;
    }

    /**
     * setter method
     * 
     * @see InfosourceRequestInnerLog#requestElapsedTime
     * @param requestElapsedTime
     *            the requestElapsedTime to set
     */
    public void setRequestElapsedTime(long requestElapsedTime) {
        this.requestElapsedTime = requestElapsedTime;
    }

    /**
     * getter method
     * 
     * @see InfosourceRequestInnerLog#totalElapsedTime
     * @return the totalElapsedTime
     */
    public long getTotalElapsedTime() {
        return totalElapsedTime;
    }

    /**
     * setter method
     * 
     * @see InfosourceRequestInnerLog#totalElapsedTime
     * @param totalElapsedTime
     *            the totalElapsedTime to set
     */
    public void setTotalElapsedTime(long totalElapsedTime) {
        this.totalElapsedTime = totalElapsedTime;
    }

    /**
     * getter method
     * 
     * @see InfosourceRequestInnerLog#code
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * setter method
     * 
     * @see InfosourceRequestInnerLog#code
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "InfosourceRequestInnerLog [code=" + code + ", requestElapsedTime=" + requestElapsedTime + ", totalElapsedTime=" + totalElapsedTime + "]";
    }
}
