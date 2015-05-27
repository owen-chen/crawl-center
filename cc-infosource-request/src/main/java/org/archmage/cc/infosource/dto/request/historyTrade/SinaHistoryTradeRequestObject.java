/*
 * Create Author  : chen.chen.9
 * Create Date    : May 25, 2015
 * File Name      : SinaHistoryTradeRequestObject.java
 */

package org.archmage.cc.infosource.dto.request.historyTrade;

import org.archmage.cc.infosource.dto.request.RequestObject;

/**
 * sina history trade request object
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 25, 2015
 */
public class SinaHistoryTradeRequestObject extends RequestObject {
    /** symbol of stock */
    private String symbol;

    /** year */
    private int year;

    /** month */
    private int month;

    /** day */
    private int day;

    /**
     * constructor
     */
    public SinaHistoryTradeRequestObject() {
        super();
    }

    /**
     * constructor
     * 
     * @param code
     * @param symbol
     * @param year
     * @param month
     * @param day
     */
    public SinaHistoryTradeRequestObject(String code, String symbol, int year, int month, int day) {
        super(code);
        this.symbol = symbol;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * getter method
     * 
     * @see SinaHistoryTradeRequestObject#symbol
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeRequestObject#symbol
     * @param symbol
     *            the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * getter method
     * 
     * @see SinaHistoryTradeRequestObject#year
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeRequestObject#year
     * @param year
     *            the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * getter method
     * 
     * @see SinaHistoryTradeRequestObject#month
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeRequestObject#month
     * @param month
     *            the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * getter method
     * 
     * @see SinaHistoryTradeRequestObject#day
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeRequestObject#day
     * @param day
     *            the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "SinaHistoryTradeRequestObject [symbol=" + symbol + ", year=" + year + ", month=" + month + ", day=" + day + ", toString()=" + super.toString() + "]";
    }
}
