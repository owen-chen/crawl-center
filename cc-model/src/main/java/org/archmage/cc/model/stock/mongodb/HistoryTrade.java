/*
 * Create Author  : chen.chen.9
 * Create Date    : May 27, 2015
 * File Name      : HistoryTrade.java
 */

package org.archmage.cc.model.stock.mongodb;

/**
 * history trade for mongodb
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 27, 2015
 */
public class HistoryTrade {
    /** symbol */
    private String symbol;

    /** current time */
    private String currentTime;

    /** year */
    private String year;

    /** month */
    private String month;

    /** day */
    private String day;

    /** hour */
    private String hour;

    /** minute */
    private String minute;

    /** second */
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
    public HistoryTrade() {
        super();
    }

    /**
     * constructor
     * 
     * @param symbol
     * @param currentTime
     * @param current
     * @param quoteTrend
     * @param dealAmount
     * @param dealFigure
     * @param feature
     */
    public HistoryTrade(String symbol, String currentTime, String current, String quoteTrend, String dealAmount, String dealFigure, String feature, String year, String month,
                        String day, String hour, String minute, String second) {
        super();
        this.symbol = symbol;
        this.currentTime = currentTime;
        this.current = current;
        this.quoteTrend = quoteTrend;
        this.dealAmount = dealAmount;
        this.dealFigure = dealFigure;
        this.feature = feature;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
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
     */
    public String getCurrentTime() {
        return currentTime;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#currentTime
     * @param currentTime
     *            the currentTime to set
     */
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#year
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#year
     * @param year
     *            the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#month
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#month
     * @param month
     *            the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#day
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#day
     * @param day
     *            the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#hour
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#hour
     * @param hour
     *            the hour to set
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#minute
     * @return the minute
     */
    public String getMinute() {
        return minute;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#minute
     * @param minute
     *            the minute to set
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#current
     * @return the current
     */
    public String getCurrent() {
        return current;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#current
     * @param current
     *            the current to set
     */
    public void setCurrent(String current) {
        this.current = current;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#quoteTrend
     * @return the quoteTrend
     */
    public String getQuoteTrend() {
        return quoteTrend;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#quoteTrend
     * @param quoteTrend
     *            the quoteTrend to set
     */
    public void setQuoteTrend(String quoteTrend) {
        this.quoteTrend = quoteTrend;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#dealAmount
     * @return the dealAmount
     */
    public String getDealAmount() {
        return dealAmount;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#dealAmount
     * @param dealAmount
     *            the dealAmount to set
     */
    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#dealFigure
     * @return the dealFigure
     */
    public String getDealFigure() {
        return dealFigure;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#dealFigure
     * @param dealFigure
     *            the dealFigure to set
     */
    public void setDealFigure(String dealFigure) {
        this.dealFigure = dealFigure;
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#feature
     * @return the feature
     */
    public String getFeature() {
        return feature;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#feature
     * @param feature
     *            the feature to set
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "HistoryTrade [symbol=" + symbol + ", currentTime=" + currentTime + ", year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", minute=" + minute
               + ", second=" + second + ", current=" + current + ", quoteTrend=" + quoteTrend + ", dealAmount=" + dealAmount + ", dealFigure=" + dealFigure + ", feature="
               + feature + "]";
    }

    /**
     * getter method
     * 
     * @see HistoryTrade#second
     * @return the second
     */
    public String getSecond() {
        return second;
    }

    /**
     * setter method
     * 
     * @see HistoryTrade#second
     * @param second
     *            the second to set
     */
    public void setSecond(String second) {
        this.second = second;
    }
}
