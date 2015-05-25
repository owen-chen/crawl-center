/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-20
 * File Name      : InfosourceConfig.java
 */

package org.archmage.cc.infosource.reader.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * infosource
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-20
 */
public class Infosource {
    /** infosource code */
    private String code;

    /** name */
    private String name;

    /** request handler class */
    private Class<?> requestHandlerClass;

    /** response class */
    private Class<?> responseClass;

    /** {@link SubInfosource} list */
    private List<SubInfosource> subInfosourceList = new ArrayList<SubInfosource>();

    /**
     * constructor
     */
    public Infosource() {
        super();
    }

    /**
     * constructor
     * 
     * @param code
     *            code
     * @param name
     *            name
     * @param requestHandlerClass
     *            requestHandlerClass
     * @param responseClass
     *            responseClass
     * @param subInfosourceList
     *            subInfosourceList
     */
    public Infosource(String code, String name, Class<?> requestHandlerClass, Class<?> responseClass, List<SubInfosource> subInfosourceList) {
        super();
        this.code = code;
        this.name = name;
        this.requestHandlerClass = requestHandlerClass;
        this.responseClass = responseClass;
        this.subInfosourceList = subInfosourceList;
    }

    /**
     * getter method
     * 
     * @see Infosource#subInfosourceList
     * @return the subInfosourceList
     */
    public List<SubInfosource> getSubInfosourceList() {
        return subInfosourceList;
    }

    /**
     * setter method
     * 
     * @see Infosource#subInfosourceList
     * @param subInfosourceList
     *            the subInfosourceList to set
     */
    public void setSubInfosourceList(List<SubInfosource> subInfosourceList) {
        this.subInfosourceList = subInfosourceList;
    }

    /**
     * getter method
     * 
     * @see Infosource#responseClass
     * @return the responseClass
     */
    public Class<?> getResponseClass() {
        return responseClass;
    }

    /**
     * setter method
     * 
     * @see Infosource#responseClass
     * @param responseClass
     *            the responseClass to set
     */
    public void setResponseClass(Class<?> responseClass) {
        this.responseClass = responseClass;
    }

    /**
     * getter method
     * 
     * @see Infosource#code
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * setter method
     * 
     * @see Infosource#code
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * getter method
     * 
     * @see Infosource#requestHandlerClass
     * @return the requestHandlerClass
     */
    public Class<?> getRequestHandlerClass() {
        return requestHandlerClass;
    }

    /**
     * setter method
     * 
     * @see Infosource#requestHandlerClass
     * @param requestHandlerClass
     *            the requestHandlerClass to set
     */
    public void setRequestHandlerClass(Class<?> requestHandlerClass) {
        this.requestHandlerClass = requestHandlerClass;
    }

    /**
     * getter method
     * 
     * @see Infosource#name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * setter method
     * 
     * @see Infosource#name
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Infosource [code=" + code + ", name=" + name + ", requestHandlerClass=" + requestHandlerClass + ", responseClass=" + responseClass + ", subInfosourceList="
               + subInfosourceList + "]";
    }

}
