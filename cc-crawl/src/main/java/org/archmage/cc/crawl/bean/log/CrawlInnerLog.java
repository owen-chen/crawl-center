/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-18
 * File Name      : CrawlInnerLog.java
 */

package org.archmage.cc.crawl.bean.log;

import java.util.Date;

import org.archmage.cc.framework.log.InnerLog;
import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.response.ResponseObject;

/**
 * crawl inner log
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-18
 */
public class CrawlInnerLog extends InnerLog {
    /** {@link RequestObject} */
    private RequestObject requestObject;

    /** {@link ResponseObject} */
    private ResponseObject responseObject;

    /** start time（ms） */
    private Date startTime;

    /** end time（ms） */
    private Date endTime;

    /** 总耗时(ms) */
    private long elapsedTime;

    /** 返回码 */
    private int errorCode;

    /** 错误信息 */
    @Deprecated
    private String errorMsg;

    /**
     * getter method
     * 
     * @see CrawlInnerLog#requestObject
     * @return the requestObject
     */
    public RequestObject getRequestObject() {
        return requestObject;
    }

    /**
     * setter method
     * 
     * @see CrawlInnerLog#requestObject
     * @param requestObject
     *            the requestObject to set
     */
    public void setRequestObject(RequestObject requestObject) {
        this.requestObject = requestObject;
    }

    /**
     * getter method
     * 
     * @see CrawlInnerLog#responseObject
     * @return the responseObject
     */
    public ResponseObject getResponseObject() {
        return responseObject;
    }

    /**
     * setter method
     * 
     * @see CrawlInnerLog#responseObject
     * @param responseObject
     *            the responseObject to set
     */
    public void setResponseObject(ResponseObject responseObject) {
        this.responseObject = responseObject;
    }

    @Override
    public String toString() {
        return "CrawlInnerLog [requestObject=" + requestObject + ", responseObject=" + responseObject + ", startTime=" + startTime + ", endTime=" + endTime + ", elapsedTime="
               + elapsedTime + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
    }

    /**
     * getter method
     * 
     * @see CrawlInnerLog#startTime
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * setter method
     * 
     * @see CrawlInnerLog#startTime
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * getter method
     * 
     * @see CrawlInnerLog#endTime
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * setter method
     * 
     * @see CrawlInnerLog#endTime
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * getter method
     * 
     * @see CrawlInnerLog#elapsedTime
     * @return the elapsedTime
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * setter method
     * 
     * @see CrawlInnerLog#elapsedTime
     * @param elapsedTime
     *            the elapsedTime to set
     */
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * getter method
     * 
     * @see CrawlInnerLog#errorCode
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * setter method
     * 
     * @see CrawlInnerLog#errorCode
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * getter method
     * 
     * @see CrawlInnerLog#errorMsg
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * setter method
     * 
     * @see CrawlInnerLog#errorMsg
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
