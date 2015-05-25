/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-26
 * File Name      : CollectorManager.java
 */

package org.archmage.cc.crawl.collector.manager;

import org.archmage.cc.crawl.exception.CrawlErrorException;

/**
 * data collector manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-26
 */
public interface CollectorManager {
    /**
     * data collect
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     */
    void collect() throws CrawlErrorException;
}
