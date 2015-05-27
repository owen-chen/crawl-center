/*
 * Create Author  : chen.chen.9
 * Create Date    : May 20, 2015
 * File Name      : StockResponseObject.java
 */

package org.archmage.cc.infosource.dto.response.stock;

import java.util.ArrayList;
import java.util.List;

import org.archmage.cc.infosource.dto.response.ResponseObject;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * sina stock response object
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 20, 2015
 */
public class SinaStockResponseObject extends ResponseObject {
    /** 是否给json字符串增加{"object: ...}，有的json字符串反序列化需要增加一个头 */
    public static final boolean XSTREAM_ROOT = true;

    /** the element on the root */
    public static final String XSTREAM_ALIAS = "object";

    @XStreamImplicit(itemFieldName = "object")
    private List<Result> resultList = new ArrayList<Result>();

    /**
     * constructor
     */
    public SinaStockResponseObject() {
        super();
    }

    /**
     * constructor
     * 
     * @param resultList
     */
    public SinaStockResponseObject(List<Result> resultList) {
        super();
        this.resultList = resultList;
    }

    /**
     * getter method
     * 
     * @see SinaStockResponseObject#resultList
     * @return the resultList
     */
    public List<Result> getResultList() {
        return resultList;
    }

    /**
     * setter method
     * 
     * @see SinaStockResponseObject#resultList
     * @param resultList
     *            the resultList to set
     */
    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "SinaStockResponseObject [resultList=" + resultList + ", toString()=" + super.toString() + "]";
    }
}
