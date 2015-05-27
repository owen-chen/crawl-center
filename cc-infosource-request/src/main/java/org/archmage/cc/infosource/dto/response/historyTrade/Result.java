/*
 * Create Author  : chen.chen.9
 * Create Date    : May 25, 2015
 * File Name      : Result.java
 */

package org.archmage.cc.infosource.dto.response.historyTrade;

/**
 * inner result
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 25, 2015
 */
public class Result {
    /** deal hour */
    private String hour;

    /** deal minute */
    private String minute;

    /** deal second */
    private String second;

    /** current quote */
    private String current;

    /** deal quote trend */
    private String quoteTrend;

    /** deal amount. unit is hand */
    private String dealAmount;

    /** deal figure. unit is RMB */
    private String dealFigure;

    /** feature */
    private String feature;

    /**
     * constructor
     */
    public Result() {
        super();
    }

    /**
     * constructor
     * 
     * @param hour
     * @param minute
     * @param second
     * @param current
     * @param quoteTrend
     * @param dealAmount
     * @param dealFigure
     * @param feature
     */
    public Result(String hour, String minute, String second, String current, String quoteTrend, String dealAmount, String dealFigure, String feature) {
        super();
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.current = current;
        this.quoteTrend = quoteTrend;
        this.dealAmount = dealAmount;
        this.dealFigure = dealFigure;
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Result [hour=" + hour + ", minute=" + minute + ", second=" + second + ", current=" + current + ", quoteTrend=" + quoteTrend + ", dealAmount=" + dealAmount
               + ", dealFigure=" + dealFigure + ", feature=" + feature + "]";
    }

    /**
     * getter method
     * 
     * @see Result#hour
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * setter method
     * 
     * @see Result#hour
     * @param hour
     *            the hour to set
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * getter method
     * 
     * @see Result#minute
     * @return the minute
     */
    public String getMinute() {
        return minute;
    }

    /**
     * setter method
     * 
     * @see Result#minute
     * @param minute
     *            the minute to set
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }

    /**
     * getter method
     * 
     * @see Result#second
     * @return the second
     */
    public String getSecond() {
        return second;
    }

    /**
     * setter method
     * 
     * @see Result#second
     * @param second
     *            the second to set
     */
    public void setSecond(String second) {
        this.second = second;
    }

    /**
     * getter method
     * 
     * @see Result#current
     * @return the current
     */
    public String getCurrent() {
        return current;
    }

    /**
     * setter method
     * 
     * @see Result#current
     * @param current
     *            the current to set
     */
    public void setCurrent(String current) {
        this.current = current;
    }

    /**
     * getter method
     * 
     * @see Result#quoteTrend
     * @return the quoteTrend
     */
    public String getQuoteTrend() {
        return quoteTrend;
    }

    /**
     * setter method
     * 
     * @see Result#quoteTrend
     * @param quoteTrend
     *            the quoteTrend to set
     */
    public void setQuoteTrend(String quoteTrend) {
        this.quoteTrend = quoteTrend;
    }

    /**
     * getter method
     * 
     * @see Result#dealAmount
     * @return the dealAmount
     */
    public String getDealAmount() {
        return dealAmount;
    }

    /**
     * setter method
     * 
     * @see Result#dealAmount
     * @param dealAmount
     *            the dealAmount to set
     */
    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }

    /**
     * getter method
     * 
     * @see Result#dealFigure
     * @return the dealFigure
     */
    public String getDealFigure() {
        return dealFigure;
    }

    /**
     * setter method
     * 
     * @see Result#dealFigure
     * @param dealFigure
     *            the dealFigure to set
     */
    public void setDealFigure(String dealFigure) {
        this.dealFigure = dealFigure;
    }

    /**
     * getter method
     * 
     * @see Result#feature
     * @return the feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * setter method
     * 
     * @see Result#feature
     * @param feature
     *            the feature to set
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }
}
