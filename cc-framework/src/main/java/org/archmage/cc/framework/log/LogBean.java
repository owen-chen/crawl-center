/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-17
 * File Name      : LogBean.java
 */

package org.archmage.cc.framework.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * log bean
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-17
 */
public class LogBean {
    /** mashine IP */
    private String machine;

    /** start time（ms） */
    private Date startTime;

    /** end time（ms） */
    private Date endTime;

    /** elapsed time(ms) */
    private Long elapsedTime;

    /** error code */
    private Integer errorCode = 0;

    /** error message */
    private String errorMessage;

    /** created time */
    private Date createdTime;

    /** {@link InnerLog} list */
    private List<InnerLog> inner = new ArrayList<InnerLog>();

    /**
     * constructor
     */
    public LogBean() {
        super();
    }

    /**
     * constructor
     * 
     * @param errorMessage
     * @param startTime
     * @param endTime
     * @param elapsedTime
     * @param errorCode
     * @param errorMsg
     * @param desc
     * @param createdTime
     * @param innerLogList
     */
    public LogBean(String errorMessage, String machine, Date startTime, Date endTime, Long elapsedTime, Integer errorCode, Date createdTime, List<InnerLog> innerLogList) {
        super();
        this.errorMessage = errorMessage;
        this.machine = machine;
        this.startTime = startTime;
        this.endTime = endTime;
        this.elapsedTime = elapsedTime;
        this.errorCode = errorCode;
        this.createdTime = createdTime;
        this.inner = innerLogList;
    }

    /**
     * getter method
     * 
     * @see LogBean#machine
     * @return the machine
     */
    public String getMachine() {
        return machine;
    }

    /**
     * setter method
     * 
     * @see LogBean#machine
     * @param machine
     *            the machine to set
     */
    public void setMachine(String machine) {
        this.machine = machine;
    }

    /**
     * getter method
     * 
     * @see LogBean#errorCode
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * setter method
     * 
     * @see LogBean#errorCode
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * getter method
     * 
     * @see LogBean#inner
     * @return the inner
     */
    public List<InnerLog> getInner() {
        return inner;
    }

    /**
     * setter method
     * 
     * @see LogBean#inner
     * @param inner
     *            the inner to set
     */
    public void setInner(List<InnerLog> inner) {
        this.inner = inner;
    }

    /**
     * getter method
     * 
     * @see LogBean#errorMessage
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * setter method
     * 
     * @see LogBean#errorMessage
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * getter method
     * 
     * @see LogBean#elapsedTime
     * @return the elapsedTime
     */
    public Long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * setter method
     * 
     * @see LogBean#elapsedTime
     * @param elapsedTime
     *            the elapsedTime to set
     */
    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * getter method
     * 
     * @see LogBean#startTime
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * setter method
     * 
     * @see LogBean#startTime
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * getter method
     * 
     * @see LogBean#endTime
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * setter method
     * 
     * @see LogBean#endTime
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * getter method
     * 
     * @see LogBean#createdTime
     * @return the createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * setter method
     * 
     * @see LogBean#createdTime
     * @param createdTime
     *            the createdTime to set
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "LogBean [machine=" + machine + ", startTime=" + startTime + ", endTime=" + endTime + ", elapsedTime=" + elapsedTime + ", errorCode=" + errorCode
               + ", errorMessage=" + errorMessage + ", createdTime=" + createdTime + ", inner=" + inner + "]";
    }
}
