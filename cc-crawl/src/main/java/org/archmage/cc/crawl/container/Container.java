/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : Container.java
 */

package org.archmage.cc.crawl.container;

/**
 * container
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public interface Container<T> {
    /**
     * Destroy when server shuts down
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     */
    void destroy();

    /**
     * whether or not container is empty
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     * @return whether or not container is empty
     */
    boolean empty();

    /**
     * add element to container
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     * @param t
     *            element
     * @return is successful to add element
     */
    boolean unshift(T t);

    /**
     * return element from container and remove it
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     * @return element
     */
    T shift();

    /**
     * whether or not container contains the element
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     * @param t
     *            element
     * @return whether or not container contains the element
     */
    boolean contains(T t);

    /**
     * retrieve size of elements in container
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     * @return size of elements in container
     */
    int size();
}
