/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : TodoUrlContainer.java
 */

package org.archmage.cc.crawl.container.request;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.archmage.cc.crawl.container.AbstractContainer;
import org.archmage.cc.infosource.bean.RequestObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * todo request container
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public class TodoRequestContainer extends AbstractContainer<RequestObject> {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** todo request by LinkedBlockingQueue */
    private Queue<RequestObject> todoRequestQueue = new LinkedBlockingQueue<RequestObject>();

    /**
     * constructor
     */
    public TodoRequestContainer() {
        init();
    }

    @Override
    protected void init() {
    }

    @Override
    public int size() {
        return todoRequestQueue.size();
    }

    @Override
    public void destroy() {
    }

    @Override
    public synchronized boolean empty() {
        return todoRequestQueue.isEmpty();
    }

    @Override
    public synchronized boolean unshift(RequestObject requestObject) {
        try {
            return todoRequestQueue.offer(requestObject);
        }
        catch (NullPointerException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }

        return false;
    }

    @Override
    public synchronized RequestObject shift() {
        return empty() ? null : todoRequestQueue.poll();
    }

    @Override
    public boolean contains(RequestObject requestObject) {
        throw new UnsupportedOperationException(getClass().getName() + ":contains()");
    }
}
