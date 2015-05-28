/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-19
 * File Name      : ResponseObject.java
 */

package org.archmage.cc.infosource.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * response object
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-19
 */
public class ResponseObject implements Serializable {
    /** Serial version UID */
    private static final long serialVersionUID = 213631724939950777L;

    /** whether successful */
    private boolean isSuccess;

    /** error code */
    private int errorCode;

    /** errorcode */
    private List<String> errorCodeInRequestList = new ArrayList<String>();

    /**
     * getter method
     * 
     * @see ResponseObject#isSuccess
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * setter method
     * 
     * @see ResponseObject#isSuccess
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * getter method
     * 
     * @see ResponseObject#errorCodeInRequestList
     * @return the errorCodeInRequestList
     */
    public List<String> getErrorCodeInRequestList() {
        return errorCodeInRequestList;
    }

    /**
     * setter method
     * 
     * @see ResponseObject#errorCodeInRequestList
     * @param errorCodeInRequestList
     *            the errorCodeInRequestList to set
     */
    public void setErrorCodeInRequestList(List<String> errorCodeInRequestList) {
        this.errorCodeInRequestList = errorCodeInRequestList;
    }

    @Override
    public String toString() {
        return "ResponseObject [isSuccess=" + isSuccess + ", errorCode=" + errorCode + ", errorCodeInRequestList=" + errorCodeInRequestList + "]";
    }

    /**
     * getter method
     * 
     * @see ResponseObject#errorCode
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * setter method
     * 
     * @see ResponseObject#errorCode
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}