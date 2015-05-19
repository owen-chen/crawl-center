/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : CrawlDriver.java
 */

package org.archmage.cc.crawl.driver;

import org.archmage.cc.infosource.bean.ResponseObject;

/**
 * crawl driver
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public interface CrawlDriver<T extends ResponseObject> {
    /**
     * crawl
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     * @return is successful
     */
    boolean capture();

    /**
     * obtain todo request
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     * @return todo request size
     */
    int obtainTodoRequest();

    /**
     * is crawl finish
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     * @return is finish
     */
    boolean isCrawlFinish();
}