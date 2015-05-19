/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-18
 * File Name      : CrawlLogBean.java
 */

package org.archmage.cc.crawl.bean.log;

import java.util.ArrayList;
import java.util.List;

import org.archmage.cc.crawl.model.CrawlStatus;
import org.archmage.cc.framework.log.LogBean;

/**
 * the crawl job log bean
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-18
 */
public class CrawlJobLogBean extends LogBean {
    /** {@link CrawlStatus} */
    private CrawlStatus crawlStatus;

    /** manager class name */
    private String crawlManagerClassName;

    /** todo size */
    private Integer todoSize;

    /** the elapsed time to obtain todo request */
    private Long obtainTodoRequestElapsedTime;

    /** the elapsed time to drop collection */
    private Long dropCollectionElapsedTime;

    /** the elapsed time to crawl */
    private Long crawlElapsedTime;

    /** {@link CollectorInnerLog} */
    private CollectorInnerLog collectorInnerLog;

    /** {@link CrawlTaskLogBean} list */
    private List<CrawlTaskLogBean> crawlTaskLogList = new ArrayList<CrawlTaskLogBean>();

    /**
     * getter method
     * 
     * @see CrawlJobLogBean#crawlStatus
     * @return the crawlStatus
     */
    public CrawlStatus getCrawlStatus() {
        return crawlStatus;
    }

    /**
     * setter method
     * 
     * @see CrawlJobLogBean#crawlStatus
     * @param crawlStatus
     *            the crawlStatus to set
     */
    public void setCrawlStatus(CrawlStatus crawlStatus) {
        this.crawlStatus = crawlStatus;
    }

    /**
     * getter method
     * 
     * @see CrawlJobLogBean#crawlManagerClassName
     * @return the crawlManagerClassName
     */
    public String getCrawlManagerClassName() {
        return crawlManagerClassName;
    }

    /**
     * setter method
     * 
     * @see CrawlJobLogBean#crawlManagerClassName
     * @param crawlManagerClassName
     *            the crawlManagerClassName to set
     */
    public void setCrawlManagerClassName(String crawlManagerClassName) {
        this.crawlManagerClassName = crawlManagerClassName;
    }

    /**
     * getter method
     * 
     * @see CrawlJobLogBean#todoSize
     * @return the todoSize
     */
    public Integer getTodoSize() {
        return todoSize;
    }

    /**
     * setter method
     * 
     * @see CrawlJobLogBean#todoSize
     * @param todoSize
     *            the todoSize to set
     */
    public void setTodoSize(Integer todoSize) {
        this.todoSize = todoSize;
    }

    /**
     * getter method
     * 
     * @see CrawlJobLogBean#obtainTodoRequestElapsedTime
     * @return the obtainTodoRequestElapsedTime
     */
    public Long getObtainTodoRequestElapsedTime() {
        return obtainTodoRequestElapsedTime;
    }

    /**
     * setter method
     * 
     * @see CrawlJobLogBean#obtainTodoRequestElapsedTime
     * @param obtainTodoRequestElapsedTime
     *            the obtainTodoRequestElapsedTime to set
     */
    public void setObtainTodoRequestElapsedTime(Long obtainTodoRequestElapsedTime) {
        this.obtainTodoRequestElapsedTime = obtainTodoRequestElapsedTime;
    }

    /**
     * getter method
     * 
     * @see CrawlJobLogBean#dropCollectionElapsedTime
     * @return the dropCollectionElapsedTime
     */
    public Long getDropCollectionElapsedTime() {
        return dropCollectionElapsedTime;
    }

    /**
     * setter method
     * 
     * @see CrawlJobLogBean#dropCollectionElapsedTime
     * @param dropCollectionElapsedTime
     *            the dropCollectionElapsedTime to set
     */
    public void setDropCollectionElapsedTime(Long dropCollectionElapsedTime) {
        this.dropCollectionElapsedTime = dropCollectionElapsedTime;
    }

    /**
     * getter method
     * 
     * @see CrawlJobLogBean#crawlElapsedTime
     * @return the crawlElapsedTime
     */
    public Long getCrawlElapsedTime() {
        return crawlElapsedTime;
    }

    /**
     * setter method
     * 
     * @see CrawlJobLogBean#crawlElapsedTime
     * @param crawlElapsedTime
     *            the crawlElapsedTime to set
     */
    public void setCrawlElapsedTime(Long crawlElapsedTime) {
        this.crawlElapsedTime = crawlElapsedTime;
    }

    /**
     * getter method
     * 
     * @see CrawlJobLogBean#collectorInnerLog
     * @return the collectorInnerLog
     */
    public CollectorInnerLog getCollectorInnerLog() {
        return collectorInnerLog;
    }

    /**
     * setter method
     * 
     * @see CrawlJobLogBean#collectorInnerLog
     * @param collectorInnerLog
     *            the collectorInnerLog to set
     */
    public void setCollectorInnerLog(CollectorInnerLog collectorInnerLog) {
        this.collectorInnerLog = collectorInnerLog;
    }

    /**
     * getter method
     * 
     * @see CrawlJobLogBean#crawlTaskLogList
     * @return the crawlTaskLogList
     */
    public List<CrawlTaskLogBean> getCrawlTaskLogList() {
        return crawlTaskLogList;
    }

    /**
     * setter method
     * 
     * @see CrawlJobLogBean#crawlTaskLogList
     * @param crawlTaskLogList
     *            the crawlTaskLogList to set
     */
    public void setCrawlTaskLogList(List<CrawlTaskLogBean> crawlTaskLogList) {
        this.crawlTaskLogList = crawlTaskLogList;
    }

    @Override
    public String toString() {
        return "CrawlJobLogBean [crawlStatus=" + crawlStatus + ", crawlManagerClassName=" + crawlManagerClassName + ", todoSize=" + todoSize + ", obtainTodoRequestElapsedTime="
               + obtainTodoRequestElapsedTime + ", dropCollectionElapsedTime=" + dropCollectionElapsedTime + ", crawlElapsedTime=" + crawlElapsedTime + ", collectorInnerLog="
               + collectorInnerLog + ", crawlTaskLogList=" + crawlTaskLogList + ", toString()=" + super.toString() + "]";
    }
}
