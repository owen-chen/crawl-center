/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : Result.java
 */

package org.archmage.cc.infosource.dto.response.stock;

/**
 * inner result
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 21, 2015
 */
public class Result {
    /** stock symbol */
    private String symbol;

    /** code */
    private String code;

    /** stock name */
    private String name;

    /**
     * constructor
     */
    public Result() {
        super();
    }

    /**
     * constructor
     * 
     * @param symbol
     * @param code
     * @param name
     */
    public Result(String symbol, String code, String name) {
        super();
        this.symbol = symbol;
        this.code = code;
        this.name = name;
    }

    /**
     * getter method
     * 
     * @see Result#symbol
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * setter method
     * 
     * @see Result#symbol
     * @param symbol
     *            the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * getter method
     * 
     * @see Result#code
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * setter method
     * 
     * @see Result#code
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * getter method
     * 
     * @see Result#name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * setter method
     * 
     * @see Result#name
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Result [symbol=" + symbol + ", code=" + code + ", name=" + name + "]";
    }
}
