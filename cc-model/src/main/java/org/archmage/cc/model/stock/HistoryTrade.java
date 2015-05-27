/*
 * Create Author  : chen.chen.9
 * Create Date    : May 27, 2015
 * File Name      : HistoryTrade.java
 */

package org.archmage.cc.model.stock;

/**
 * history trade
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 27, 2015
 * @hibernate.class dynamic-insert="true" dynamic-update="true"
 *                  table="history_trade" lazy="false"
 */
public class HistoryTrade {
    /** primary key */
    private long id;

    /** symbol */
    private String symbol;

    /** current time */
    private Long currentTime;

    /** current quote */
    private float current;

    /** deal quote trend */
    private float quoteTrend;

    /** deal amount. unit is hand */
    private int dealAmount;

    /** deal figure. unit is RMB */
    private long dealFigure;

    /** feature */
    private int feature;

    /**
     * constructor
     */
    public HistoryTrade() {
        super();
    }

    /**
     * constructor
     * 
     * @param id
     * @param symbol
     * @param currentTime
     * @param current
     * @param quoteTrend
     * @param dealAmount
     * @param dealFigure
     * @param feature
     */
    public HistoryTrade(long id, String symbol, Long currentTime, float current, float quoteTrend, int dealAmount, long dealFigure, int feature) {
        super();
        this.id = id;
        this.symbol = symbol;
        this.currentTime = currentTime;
        this.current = current;
        this.quoteTrend = quoteTrend;
        this.dealAmount = dealAmount;
        this.dealFigure = dealFigure;
        this.feature = feature;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#id
     * @return the id
     * @hibernate.id generator-class="native" type="long" unsaved-value="0"
     */
    public long getId() {
        return id;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#id
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#symbol
     * @return the symbol
     * @hibernate.property type="string"
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#symbol
     * @param symbol
     *            the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#currentTime
     * @return the currentTime
     * @hibernate.property type="long"
     */
    public Long getCurrentTime() {
        return currentTime;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#currentTime
     * @param currentTime
     *            the currentTime to set
     */
    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#current
     * @return the current
     * @hibernate.property type="float"
     */
    public float getCurrent() {
        return current;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#current
     * @param current
     *            the current to set
     */
    public void setCurrent(float current) {
        this.current = current;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#quoteTrend
     * @return the quoteTrend
     * @hibernate.property type="float"
     */
    public float getQuoteTrend() {
        return quoteTrend;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#quoteTrend
     * @param quoteTrend
     *            the quoteTrend to set
     */
    public void setQuoteTrend(float quoteTrend) {
        this.quoteTrend = quoteTrend;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#dealAmount
     * @return the dealAmount
     * @hibernate.property type="int"
     */
    public int getDealAmount() {
        return dealAmount;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#dealAmount
     * @param dealAmount
     *            the dealAmount to set
     */
    public void setDealAmount(int dealAmount) {
        this.dealAmount = dealAmount;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#dealFigure
     * @return the dealFigure
     * @hibernate.property type="long"
     */
    public long getDealFigure() {
        return dealFigure;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#dealFigure
     * @param dealFigure
     *            the dealFigure to set
     */
    public void setDealFigure(long dealFigure) {
        this.dealFigure = dealFigure;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#feature
     * @return the feature
     * @hibernate.property type="int"
     */
    public int getFeature() {
        return feature;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#feature
     * @param feature
     *            the feature to set
     */
    public void setFeature(int feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "HistoryTrade [id=" + id + ", symbol=" + symbol + ", currentTime=" + currentTime + ", current=" + current + ", quoteTrend=" + quoteTrend + ", dealAmount="
               + dealAmount + ", dealFigure=" + dealFigure + ", feature=" + feature + "]";
    }
}
