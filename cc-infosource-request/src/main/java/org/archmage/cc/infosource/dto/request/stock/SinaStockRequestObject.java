/*
 * Create Author  : chen.chen.9
 * Create Date    : May 20, 2015
 * File Name      : StockRequestObject.java
 */

package org.archmage.cc.infosource.dto.request.stock;

import org.archmage.cc.infosource.dto.request.RequestObject;

/**
 * stock info request object from sina
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 20, 2015
 */
public class SinaStockRequestObject extends RequestObject {
    /** page no */
    private int pageNo;

    /**
     * constructor
     */
    public SinaStockRequestObject() {
        super();
    }

    /**
     * constructor
     * 
     * @param code
     * @param pageNo
     */
    public SinaStockRequestObject(String code, int pageNo) {
        super(code);
        this.pageNo = pageNo;
    }

    /**
     * getter method
     * 
     * @see SinaStockRequestObject#pageNo
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * setter method
     * 
     * @see SinaStockRequestObject#pageNo
     * @param pageNo
     *            the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "SinaStockRequestObject [pageNo=" + pageNo + ", toString()=" + super.toString() + "]";
    }

}
