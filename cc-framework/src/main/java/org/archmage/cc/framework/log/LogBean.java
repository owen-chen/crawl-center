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
    /** address */
    private String address;

    /** mashine IP */
    private String machine;

    /** start time（ms） */
    private Date starttime;

    /** end time（ms） */
    private Date endtime;

    /** elapsed time(ms) */
    private Long elapsedTime;

    /** error code */
    private Integer errorCode = 0;

    /** error message */
    private String errorMessage;

    /** created time */
    private Date createdtime;

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
    public LogBean(String errorMessage, String address, String machine, Date startTime, Date endTime, Long elapsedTime, Integer errorCode, Date createdTime,
                   List<InnerLog> innerLogList) {
        super();
        this.errorMessage = errorMessage;
        this.address = address;
        this.machine = machine;
        this.starttime = startTime;
        this.endtime = endTime;
        this.elapsedTime = elapsedTime;
        this.errorCode = errorCode;
        this.createdtime = createdTime;
        this.inner = innerLogList;
    }

    /**
     * getter method
     * 
     * @see LogBean#address
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * setter method
     * 
     * @see LogBean#address
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @see LogBean#starttime
     * @return the starttime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * setter method
     * 
     * @see LogBean#starttime
     * @param starttime
     *            the starttime to set
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * getter method
     * 
     * @see LogBean#endtime
     * @return the endtime
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * setter method
     * 
     * @see LogBean#endtime
     * @param endtime
     *            the endtime to set
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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
     * @see LogBean#createdtime
     * @return the createdtime
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * setter method
     * 
     * @see LogBean#createdtime
     * @param createdtime
     *            the createdtime to set
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
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

    @Override
    public String toString() {
        return "LogBean [address=" + address + ", machine=" + machine + ", starttime=" + starttime + ", endtime=" + endtime + ", elapsedTime=" + elapsedTime + ", errorCode="
               + errorCode + ", errorMessage=" + errorMessage + ", createdtime=" + createdtime + ", inner=" + inner + "]";
    }
}
