/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : CrawlStatus.java
 */

package org.archmage.cc.crawl.model;

/**
 * crawl status
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public class CrawlStatus {
    /** primary key */
    private String _id;

    /** The infosource code in crawl-config.xml */
    private String code;

    /** Is the crawl job running */
    private Boolean runnable;

    /** The status of the crawl job */
    private Integer status;

    /** number of crawl threads */
    private Integer threadPoolSize;

    /** time out */
    private Long timeout;

    /**
     * constructor
     */
    public CrawlStatus() {
        super();
    }

    /**
     * constructor
     * 
     * @param name
     *            name
     * @param runnable
     *            runnable
     * @param status
     *            status
     * @param threadPoolSize
     *            threadPoolSize
     */
    public CrawlStatus(String code, Boolean runnable, Integer status, Integer threadPoolSize, long timeout) {
        super();
        this.code = code;
        this.runnable = runnable;
        this.status = status;
        this.threadPoolSize = threadPoolSize;
        this.timeout = timeout;
    }

    /**
     * final crawl status enumeration
     * <p>
     * 
     * @author : chen.chen.9
     * @date : 2013-8-23
     */
    public static final class Status {
        /** ready for start */
        public static final int READY_FOR_START = 1;

        /** started: start to obtain todo urls */
        public static final int STARTED = 2;

        /** crawling: start to crawl website from todo response */
        public static final int CRAWLING = 3;

        /** failed in crawling */
        public static final int CRAWLING_FAILURE = -3;

        /**
         * finished: all of todo urls has been crawled and website's contents
         * has been parsed
         */
        public static final int FINISHED = 5;

        /** data_collecor: collecting data */
        public static final int DATA_COLLECTOR = 4;

        /** data_collecor: failed to collecting data */
        public static final int DATA_COLLECTOR_FAILURE = -4;
    }

    /**
     * getter method
     * 
     * @see CrawlStatus#runnable
     * @return the runnable
     */
    public Boolean getRunnable() {
        return runnable;
    }

    /**
     * setter method
     * 
     * @see CrawlStatus#runnable
     * @param runnable
     *            the runnable to set
     */
    public void setRunnable(Boolean runnable) {
        this.runnable = runnable;
    }

    /**
     * getter method
     * 
     * @see CrawlStatus#status
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * setter method
     * 
     * @see CrawlStatus#status
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * getter method
     * 
     * @see CrawlStatus#threadPoolSize
     * @return the threadPoolSize
     */
    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

    /**
     * setter method
     * 
     * @see CrawlStatus#threadPoolSize
     * @param threadPoolSize
     *            the threadPoolSize to set
     */
    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    @Override
    public String toString() {
        return "CrawlStatus [_id=" + _id + ", code=" + code + ", runnable=" + runnable + ", status=" + status + ", threadPoolSize=" + threadPoolSize + ", timeout=" + timeout + "]";
    }

    /**
     * getter method
     * 
     * @see CrawlStatus#_id
     * @return the _id
     */
    public String get_id() {
        return _id;
    }

    /**
     * setter method
     * 
     * @see CrawlStatus#_id
     * @param _id
     *            the _id to set
     */
    public void set_id(String _id) {
        this._id = _id;
    }

    /**
     * getter method
     * 
     * @see CrawlStatus#code
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * setter method
     * 
     * @see CrawlStatus#code
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * getter method
     * 
     * @see CrawlStatus#timeout
     * @return the timeout
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * setter method
     * 
     * @see CrawlStatus#timeout
     * @param timeout
     *            the timeout to set
     */
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
