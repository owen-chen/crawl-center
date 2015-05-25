/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-5
 * File Name      : EntireObject.java
 */

package org.archmage.cc.crawl.bean;

import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.response.ResponseObject;

/**
 * including {@link ResponseObject} and {@link RequestObject}
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-5
 */
public class EntireObject {
    /** {@link ResponseObject} */
    private ResponseObject responseObject;

    /** {@link RequestObject} */
    private RequestObject requestObject;

    /**
     * constructor
     * 
     * @param responseObject
     *            {@link ResponseObject}
     * @param requestObject
     *            {@link RequestObject}
     */
    public EntireObject(RequestObject requestObject, ResponseObject responseObject) {
        super();
        this.responseObject = responseObject;
        this.requestObject = requestObject;
    }

    /**
     * getter method
     * 
     * @see EntireObject#responseObject
     * @return the responseObject
     */
    public ResponseObject getResponseObject() {
        return responseObject;
    }

    /**
     * setter method
     * 
     * @see EntireObject#responseObject
     * @param responseObject
     *            the responseObject to set
     */
    public void setResponseObject(ResponseObject responseObject) {
        this.responseObject = responseObject;
    }

    /**
     * getter method
     * 
     * @see EntireObject#requestObject
     * @return the requestObject
     */
    public RequestObject getRequestObject() {
        return requestObject;
    }

    /**
     * setter method
     * 
     * @see EntireObject#requestObject
     * @param requestObject
     *            the requestObject to set
     */
    public void setRequestObject(RequestObject requestObject) {
        this.requestObject = requestObject;
    }

    @Override
    public String toString() {
        return "EntireObject [responseObject=" + responseObject + ", requestObject=" + requestObject + "]";
    }
}
