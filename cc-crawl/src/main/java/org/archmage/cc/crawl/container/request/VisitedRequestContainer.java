/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : VisitedUrlContainer.java
 */

package org.archmage.cc.crawl.container.request;

import java.util.HashSet;
import java.util.Set;

import org.archmage.cc.crawl.container.AbstractContainer;

/**
 * visited request container
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public class VisitedRequestContainer extends AbstractContainer<String> {
    /** visited request by hashset */
    private Set<String> visitedRequestSet = new HashSet<String>();

    /**
     * constructor
     */
    public VisitedRequestContainer() {
        init();
    }

    @Override
    protected void init() {
    }

    @Override
    public int size() {
        return visitedRequestSet.size();
    }

    @Override
    public void destroy() {
    }

    @Override
    public synchronized boolean empty() {
        return visitedRequestSet.isEmpty();
    }

    @Override
    public synchronized boolean unshift(String requestObject) {
        if (requestObject == null) {
            return false;
        }

        return contains(requestObject) ? false : visitedRequestSet.add(requestObject.toString());
    }

    @Override
    public String shift() {
        throw new UnsupportedOperationException(getClass().getName() + ":shift()");
    }

    @Override
    public synchronized boolean contains(String requestObject) {
        return visitedRequestSet.contains(requestObject);
    }
}
