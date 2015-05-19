/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : SeedUrlContainer.java
 */

package org.archmage.cc.crawl.container.request;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.archmage.cc.crawl.container.AbstractContainer;
import org.archmage.cc.infosource.bean.RequestObject;

/**
 * seed container
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public class SeedRequestContainer extends AbstractContainer<RequestObject> {
    /** seed request by LinkedBlockingQueue */
    private Queue<RequestObject> seedRequestQueue = new LinkedBlockingQueue<RequestObject>();

    /**
     * contructor
     */
    public SeedRequestContainer() {
        init();
    }

    @Override
    protected void init() {
    }

    @Override
    public int size() {
        return seedRequestQueue.size();
    }

    @Override
    public void destroy() {
    }

    @Override
    public synchronized boolean empty() {
        return seedRequestQueue.isEmpty();
    }

    @Override
    public synchronized boolean unshift(RequestObject url) {
        return seedRequestQueue.offer(url);
    }

    @Override
    public synchronized RequestObject shift() {
        return empty() ? null : seedRequestQueue.poll();
    }

    @Override
    public boolean contains(RequestObject requestObject) {
        throw new UnsupportedOperationException(getClass().getName() + ":contains()");
    }
}
