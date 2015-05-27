/*
 * Create Author  : chen.chen.9
 * Create Date    : May 25, 2015
 * File Name      : SinaHistoryTradeResponseObject.java
 */

package org.archmage.cc.infosource.dto.response.historyTrade;

import java.util.ArrayList;
import java.util.List;

import org.archmage.cc.infosource.dto.response.ResponseObject;

/**
 * sina history trade response object
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 25, 2015
 */
public class SinaHistoryTradeResponseObject extends ResponseObject {
    /** {@link Result} list */
    private List<Result> resultList = new ArrayList<Result>();

    /** whether the stock market is closed */
    private boolean isClosed;

    /**
     * constructor
     */
    public SinaHistoryTradeResponseObject() {
        super();
    }

    /**
     * constructor
     * 
     * @param resultList
     * @param isClosed
     */
    public SinaHistoryTradeResponseObject(List<Result> resultList, boolean isClosed) {
        super();
        this.resultList = resultList;
        this.isClosed = isClosed;
    }

    /**
     * getter method
     * 
     * @see SinaHistoryTradeResponseObject#resultList
     * @return the resultList
     */
    public List<Result> getResultList() {
        return resultList;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeResponseObject#resultList
     * @param resultList
     *            the resultList to set
     */
    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "SinaHistoryTradeResponseObject [resultList=" + resultList + ", isClosed=" + isClosed + ", toString()=" + super.toString() + "]";
    }

    /**
     * getter method
     * 
     * @see SinaHistoryTradeResponseObject#isClosed
     * @return the isClosed
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeResponseObject#isClosed
     * @param isClosed
     *            the isClosed to set
     */
    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
}
