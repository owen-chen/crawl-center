/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-14
 * File Name      : Refreshable.java
 */

package org.archmage.cc.framework.cache;

/**
 * data refreshable interface
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-14
 */
public interface Refreshable<K, V> {
    /**
     * retrieve value of key
     * <p>
     * 
     * @author chen.chen.9, 2013-10-21
     * @param k
     *            key
     * @return value
     */
    V retrieve(K k);

    /**
     * force to refresh data source <br />
     * triggered, either method invoked at the first time or cached data expired
     * <p>
     * 
     * @author chen.chen.9, 2013-8-14
     */
    void refresh();

    /**
     * refresh data source<br />
     * triggered when data source changed
     * <p>
     * 
     * @author chen.chen.9, 2013-9-23
     * @param force
     *            is forced
     */
    void refresh(boolean force);
}
