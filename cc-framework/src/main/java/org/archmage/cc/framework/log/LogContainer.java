/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-17
 * File Name      : LogContainer.java
 */

package org.archmage.cc.framework.log;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * use log-container to cache log in the whole crawl period<br />
 * key: thread.id, value: {@link LogBean}
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-17
 */
public class LogContainer {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** map used for caching log */
    private Map<Long, LogBean> logMap = new HashMap<Long, LogBean>();

    /**
     * retrieve log bean
     * <p>
     * 
     * @author chen.chen.9, 2013-9-17
     * @param currentThreadId
     *            current thread id
     * @return {@link LogBean}
     */
    public LogBean retrieveLogBean(long currentThreadId) {
        LogBean logBean = logMap.get(currentThreadId);
        if (logBean == null) {
            LOGGER.error("Could not find LogBean for {} to retrieve", currentThreadId);
        }

        return logBean;
    }

    /**
     * remove {@link LogBean} after the end of crawl period
     * <p>
     * 
     * @author chen.chen.9, 2013-9-17
     * @param currentThreadId
     *            current thread id
     * @return whether successful to remove {@link LogBean}
     */
    public boolean removeLogBean(long currentThreadId) {
        LogBean logBean = logMap.remove(currentThreadId);

        if (logBean == null) {
            LOGGER.error("Could not find LogBean for {} to remove", currentThreadId);
        }

        return logBean != null;
    }

    /**
     * initialize {@link LogBean} on the start
     * <p>
     * 
     * @author chen.chen.9, 2013-9-17
     * @param currentThreadId
     *            current thread id
     * @param t
     *            {link LogBean}
     * @return {@link LogBean}
     */
    public LogBean initializeLogBean(long currentThreadId, LogBean logBean) {
        logMap.put(currentThreadId, logBean);

        return logBean;
    }
}
