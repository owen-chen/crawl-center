/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-17
 * File Name      : InfosourceRequestInnerLog.java
 */

package org.archmage.cc.infosource.bean;

import java.util.ArrayList;
import java.util.List;

import org.archmage.cc.framework.log.InnerLog;

/**
 * the log while requesting subinfosource
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-17
 */
public class SubInfosourceRequestInnerLog extends InnerLog {
    /** name */
    private String name;

    /** url */
    private String url;

    /** parsed result */
    private Object parsedResult;

    /** error code */
    private String errorCode;

    /** total elapsed time */
    private long totalElapsedTime;

    /** the time elapsed in generating url */
    private long urlElapsedTime;

    /** the time elapsed in requesting */
    private long requestElapsedTime;

    /** the time elapsed in parsing */
    private long parseElapsedTime;

    /** other elpased time */
    private long otherElapsedTime;

    /** record error message when exception occurs */
    private List<String> errorMessageInRequestList = new ArrayList<String>();

    /**
     * constructor
     * 
     * @param name
     */
    public SubInfosourceRequestInnerLog(String name) {
        super();
        this.name = name;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#url
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#url
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#name
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#urlElapsedTime
     * @return the urlElapsedTime
     */
    public long getUrlElapsedTime() {
        return urlElapsedTime;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#urlElapsedTime
     * @param urlElapsedTime
     *            the urlElapsedTime to set
     */
    public void setUrlElapsedTime(long urlElapsedTime) {
        this.urlElapsedTime = urlElapsedTime;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#requestElapsedTime
     * @return the requestElapsedTime
     */
    public long getRequestElapsedTime() {
        return requestElapsedTime;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#requestElapsedTime
     * @param requestElapsedTime
     *            the requestElapsedTime to set
     */
    public void setRequestElapsedTime(long requestElapsedTime) {
        this.requestElapsedTime = requestElapsedTime;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#parseElapsedTime
     * @return the parseElapsedTime
     */
    public long getParseElapsedTime() {
        return parseElapsedTime;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#parseElapsedTime
     * @param parseElapsedTime
     *            the parseElapsedTime to set
     */
    public void setParseElapsedTime(long parseElapsedTime) {
        this.parseElapsedTime = parseElapsedTime;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#otherElapsedTime
     * @return the otherElapsedTime
     */
    public long getOtherElapsedTime() {
        return otherElapsedTime;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#otherElapsedTime
     * @param otherElapsedTime
     *            the otherElapsedTime to set
     */
    public void setOtherElapsedTime(long otherElapsedTime) {
        this.otherElapsedTime = otherElapsedTime;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#totalElapsedTime
     * @return the totalElapsedTime
     */
    public long getTotalElapsedTime() {
        return totalElapsedTime;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#totalElapsedTime
     * @param totalElapsedTime
     *            the totalElapsedTime to set
     */
    public void setTotalElapsedTime(long totalElapsedTime) {
        this.totalElapsedTime = totalElapsedTime;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#errorMessageInRequestList
     * @return the errorMessageInRequestList
     */
    public List<String> getErrorMessageInRequestList() {
        return errorMessageInRequestList;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#errorMessageInRequestList
     * @param errorMessageInRequestList
     *            the errorMessageInRequestList to set
     */
    public void setErrorMessageInRequestList(List<String> errorMessageInRequestList) {
        this.errorMessageInRequestList = errorMessageInRequestList;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#errorCode
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#errorCode
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * getter method
     * 
     * @see SubInfosourceRequestInnerLog#parsedResult
     * @return the parsedResult
     */
    public Object getParsedResult() {
        return parsedResult;
    }

    /**
     * setter method
     * 
     * @see SubInfosourceRequestInnerLog#parsedResult
     * @param parsedResult
     *            the parsedResult to set
     */
    public void setParsedResult(Object parsedResult) {
        this.parsedResult = parsedResult;
    }

    @Override
    public String toString() {
        return "SubInfosourceRequestInnerLog [name=" + name + ", url=" + url + ", parsedResult=" + parsedResult + ", errorCode=" + errorCode + ", totalElapsedTime="
               + totalElapsedTime + ", urlElapsedTime=" + urlElapsedTime + ", requestElapsedTime=" + requestElapsedTime + ", parseElapsedTime=" + parseElapsedTime
               + ", otherElapsedTime=" + otherElapsedTime + ", errorMessageInRequestList=" + errorMessageInRequestList + "]";
    }
}
