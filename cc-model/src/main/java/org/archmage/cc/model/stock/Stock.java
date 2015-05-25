/*
 * Create Author  : chen.chen.9
 * Create Date    : May 22, 2015
 * File Name      : Stock.java
 */

package org.archmage.cc.model.stock;

/**
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 22, 2015
 * @hibernate.class dynamic-insert="true" dynamic-update="true" table="stock"
 *                  lazy="false"
 */
public class Stock {
    /** primary key */
    private long id;

    /** stock name */
    private String name;

    /** stock code */
    private String code;

    /** symbol */
    private String symbol;

    /** add time */
    private Long addTime;

    /**
     * constructor
     */
    public Stock() {
        super();
    }

    /**
     * constructor
     * 
     * @param id
     */
    public Stock(long id) {
        super();
        this.id = id;
    }

    /**
     * constructor
     * 
     * @param id
     * @param name
     * @param code
     * @param symbol
     * @param bondType
     * @param addTime
     */
    public Stock(long id, String name, String code, String symbol, Long addTime) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.symbol = symbol;
        this.addTime = addTime;
    }

    /**
     * getter method
     * 
     * @see Stock#id
     * @return the id
     * @hibernate.id generator-class="native" type="long" unsaved-value="0"
     */
    public long getId() {
        return id;
    }

    /**
     * setter method
     * 
     * @see Stock#id
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * getter method
     * 
     * @see Stock#name
     * @return the name
     * @hibernate.property type="string"
     */
    public String getName() {
        return name;
    }

    /**
     * setter method
     * 
     * @see Stock#name
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method
     * 
     * @see Stock#code
     * @return the code
     * @hibernate.property type="string"
     */
    public String getCode() {
        return code;
    }

    /**
     * setter method
     * 
     * @see Stock#code
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * getter method
     * 
     * @see Stock#symbol
     * @return the symbol
     * @hibernate.property type="string"
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * setter method
     * 
     * @see Stock#symbol
     * @param symbol
     *            the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * getter method
     * 
     * @see Stock#addTime
     * @return the addTime
     * @hibernate.property type="long"
     */
    public Long getAddTime() {
        return addTime;
    }

    /**
     * setter method
     * 
     * @see Stock#addTime
     * @param addTime
     *            the addTime to set
     */
    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Stock [id=" + id + ", name=" + name + ", code=" + code + ", symbol=" + symbol + ", addTime=" + addTime + "]";
    }
}
