/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-20
 * File Name      : SubInfosource.java
 */

package org.archmage.cc.infosource.reader.bean;

import org.archmage.cc.infosource.parse.Parser;

/**
 * subinfosource
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-20
 */
public class SubInfosource {
    /** name */
    private String name;

    /** request url */
    private String url;

    /** response class */
    private Class<?> responseClass;

    /** response type: html, json, xml */
    private String responseType;

    /** {@link Parser} */
    private Parser<?> parser;

    /** the timeout while requesting, timeunit is ms */
    private int timeout;

    /** the charset of response */
    private String responseCharset;

    /**
     * constructor
     */
    public SubInfosource() {
        super();
    }

    /**
     * constructor
     * 
     * @param name
     *            name
     * @param url
     *            url
     * @param responseClass
     *            responseClass
     * @param responseType
     *            responseType
     * @param parser
     *            {@link Parser}
     * @param timeout
     *            timeout
     * @param responseCharset
     *            responseCharset
     */
    public SubInfosource(String name, String url, Class<?> responseClass, String responseType, Parser<?> parser, int timeout, String responseCharset) {
        super();
        this.name = name;
        this.url = url;
        this.responseClass = responseClass;
        this.responseType = responseType;
        this.parser = parser;
        this.timeout = timeout;
        this.responseCharset = responseCharset;
    }

    /**
     * getter method
     * 
     * @see SubInfosource#name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * setter method
     * 
     * @see SubInfosource#name
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method
     * 
     * @see SubInfosource#url
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * setter method
     * 
     * @see SubInfosource#url
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * getter method
     * 
     * @see SubInfosource#responseClass
     * @return the responseClass
     */
    public Class<?> getResponseClass() {
        return responseClass;
    }

    /**
     * setter method
     * 
     * @see SubInfosource#responseClass
     * @param responseClass
     *            the responseClass to set
     */
    public void setResponseClass(Class<?> responseClass) {
        this.responseClass = responseClass;
    }

    /**
     * getter method
     * 
     * @see SubInfosource#responseType
     * @return the responseType
     */
    public String getResponseType() {
        return responseType;
    }

    /**
     * setter method
     * 
     * @see SubInfosource#responseType
     * @param responseType
     *            the responseType to set
     */
    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    /**
     * getter method
     * 
     * @see SubInfosource#parser
     * @return the parser
     */
    public Parser<?> getParser() {
        return parser;
    }

    /**
     * setter method
     * 
     * @see SubInfosource#parser
     * @param parser
     *            the parser to set
     */
    public void setParser(Parser<?> parser) {
        this.parser = parser;
    }

    /**
     * getter method
     * 
     * @see SubInfosource#timeout
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * setter method
     * 
     * @see SubInfosource#timeout
     * @param timeout
     *            the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "SubInfosource [name=" + name + ", url=" + url + ", responseClass=" + responseClass + ", responseType=" + responseType + ", parser=" + parser + ", timeout="
               + timeout + ", responseCharset=" + responseCharset + "]";
    }

    /**
     * getter method
     * 
     * @see SubInfosource#responseCharset
     * @return the responseCharset
     */
    public String getResponseCharset() {
        return responseCharset;
    }

    /**
     * setter method
     * 
     * @see SubInfosource#responseCharset
     * @param responseCharset
     *            the responseCharset to set
     */
    public void setResponseCharset(String responseCharset) {
        this.responseCharset = responseCharset;
    }
}
