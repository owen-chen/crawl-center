/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : AbstractContainer.java
 */

package org.archmage.cc.crawl.container;

/**
 * abstract container
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public abstract class AbstractContainer<T> implements Container<T> {
    /**
     * Initialize when server starts up
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     */
    protected abstract void init();
}
