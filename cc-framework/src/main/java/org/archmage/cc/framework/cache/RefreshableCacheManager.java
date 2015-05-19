/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-14
 * File Name      : RefreshableCacheManager.java
 */

package org.archmage.cc.framework.cache;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * refreshable cache manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-14
 */
public abstract class RefreshableCacheManager<K, V> implements Refreshable<K, V> {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** last cache refreshed time */
    private long lastRefreshCacheTime = 0;

    /** is loading data source */
    private boolean isLoading = false;

    /** refresh lock */
    private Object refreshLock = new Object();

    @Override
    public V retrieve(K k) {
        refresh();

        return getMap().get(k);
    }

    @Override
    public void refresh(boolean force) {
        cleanAndRefresh(true);
    }

    @Override
    public void refresh() {
        // refresh cache if (last-refresh-time + interval) > current-time
        long refreshIntervalInSecond = getRefreshIntervalInSecond() * 1000;
        if (getLastRefreshCacheTime() == 0 || getLastRefreshCacheTime() + refreshIntervalInSecond < new Date().getTime()) {
            cleanAndRefresh(false);
        }
    }

    /**
     * clean and refresh cache
     * <p>
     * 
     * @author chen.chen.9, 2013-8-14
     */
    private void cleanAndRefresh(boolean force) {
        if (isLoading) {
            LOGGER.debug("Another thread is refreshing data, skip the refresh process!");

            return;
        }

        long lastRefresh = getLastRefreshCacheTime();

        synchronized (refreshLock) {
            setLastRefreshCacheTime(new Date().getTime());

            isLoading = true;
        }

        if (force || 0 == lastRefresh) {
            doRefresh();
            isLoading = false;
        }
        else {
            // update data in thread:
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doRefresh();
                    isLoading = false;
                }
            }).start();
        }
    }

    /**
     * refresh cache
     * <p>
     * 
     * @author chen.chen.9, 2013-8-14
     */
    private void doRefresh() {
        Map<K, V> map = readData();

        if (MapUtils.isNotEmpty(map)) {
            setMap(map);

            LOGGER.info("Refresh data successful for {} ", this.getClass().getSimpleName());
        }
        else {
            LOGGER.warn("Refresh data failed for {} ", this.getClass().getSimpleName());
        }
    }

    /**
     * getter method
     * 
     * @see RefreshableCacheManager#map
     * @return the map
     */
    protected abstract Map<K, V> getMap();

    /**
     * setter method
     * 
     * @see RefreshableCacheManager#map
     * @param map
     *            the map to set
     */
    protected abstract void setMap(Map<K, V> map);

    /**
     * get sysconfig
     * <p>
     * 
     * @author chen.chen.9, 2013-8-14
     * @return {@link XmlConfiguration}
     */
    protected abstract XmlConfiguration getSysconfig();

    /**
     * read data
     * <p>
     * 
     * @author chen.chen.9, 2013-8-14
     * @return data
     */
    protected abstract Map<K, V> readData();

    /**
     * get the interval time
     * <p>
     * 
     * @author chen.chen.9, 2013-8-14
     * @return interval
     */
    protected abstract long getRefreshIntervalInSecond();

    /**
     * getter method
     * 
     * @see RefreshableCacheManager#lastRefreshCacheTime
     * @return the lastRefreshCacheTime
     */
    protected long getLastRefreshCacheTime() {
        return lastRefreshCacheTime;
    }

    /**
     * setter method
     * 
     * @see RefreshableCacheManager#lastRefreshCacheTime
     * @param lastRefreshCacheTime
     *            the lastRefreshCacheTime to set
     */
    public void setLastRefreshCacheTime(long lastRefreshCacheTime) {
        this.lastRefreshCacheTime = lastRefreshCacheTime;
    }
}
