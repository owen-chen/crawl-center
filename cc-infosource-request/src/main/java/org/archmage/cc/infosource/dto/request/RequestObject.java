/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-16
 * File Name      : RequestObject.java
 */

package org.archmage.cc.infosource.dto.request;

/**
 * request object
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-16
 */
public class RequestObject {
    /** infosource code */
    private String code;

    /**
     * constructor
     */
    public RequestObject() {
        super();
    }

    /**
     * constructor
     * 
     * @param code
     */
    public RequestObject(String code) {
        super();
        this.code = code;
    }

    /**
     * getter method
     * 
     * @see RequestObject#code
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * setter method
     * 
     * @see RequestObject#code
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RequestObject [code=" + code + "]";
    }
}