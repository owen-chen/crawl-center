/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-26
 * File Name      : CrawlFinished.java
 */

package org.archmage.cc.crawl.bean;

/**
 * is crawl task finished
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-26
 */
public class CrawlFinished {
    /** is finished */
    private boolean isFinished;

    /**
     * constructor
     */
    public CrawlFinished() {
    }

    /**
     * constructor
     * 
     * @param isFinished
     *            isFinished
     */
    public CrawlFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * getter method
     * 
     * @see CrawlFinished#isFinished
     * @return the isFinished
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * setter method
     * 
     * @see CrawlFinished#isFinished
     * @param isFinished
     *            the isFinished to set
     */
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    @Override
    public String toString() {
        return "CrawlFinished [isFinished=" + isFinished + "]";
    }
}
