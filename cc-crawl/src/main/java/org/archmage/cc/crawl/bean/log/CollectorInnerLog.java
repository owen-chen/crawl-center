/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-18
 * File Name      : CollectorInnerLog.java
 */

package org.archmage.cc.crawl.bean.log;

import org.archmage.cc.crawl.bean.BackupHistoryDataBean;
import org.archmage.cc.framework.log.InnerLog;

/**
 * collector inner log
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-18
 */
public class CollectorInnerLog extends InnerLog {
    /** retrieved data size */
    private int retrievedDataSize;

    /** the elapsed time to retrieve data */
    private long retrieveElapsedTime;

    /** the elapsed time to persist */
    private long persistElapsedTime;

    /** the count succeed to persist */
    private int succeedToPersistCount;

    /** total elapsed time */
    private long totalElapsedTime;

    /** {@link BackupHistoryDataBean} */
    private BackupHistoryDataBean backupHistoryDataBean;

    /**
     * getter method
     * 
     * @see CollectorInnerLog#retrievedDataSize
     * @return the retrievedDataSize
     */
    public int getRetrievedDataSize() {
        return retrievedDataSize;
    }

    /**
     * setter method
     * 
     * @see CollectorInnerLog#retrievedDataSize
     * @param retrievedDataSize
     *            the retrievedDataSize to set
     */
    public void setRetrievedDataSize(int retrievedDataSize) {
        this.retrievedDataSize = retrievedDataSize;
    }

    /**
     * getter method
     * 
     * @see CollectorInnerLog#retrieveElapsedTime
     * @return the retrieveElapsedTime
     */
    public long getRetrieveElapsedTime() {
        return retrieveElapsedTime;
    }

    /**
     * setter method
     * 
     * @see CollectorInnerLog#retrieveElapsedTime
     * @param retrieveElapsedTime
     *            the retrieveElapsedTime to set
     */
    public void setRetrieveElapsedTime(long retrieveElapsedTime) {
        this.retrieveElapsedTime = retrieveElapsedTime;
    }

    /**
     * getter method
     * 
     * @see CollectorInnerLog#persistElapsedTime
     * @return the persistElapsedTime
     */
    public long getPersistElapsedTime() {
        return persistElapsedTime;
    }

    /**
     * setter method
     * 
     * @see CollectorInnerLog#persistElapsedTime
     * @param persistElapsedTime
     *            the persistElapsedTime to set
     */
    public void setPersistElapsedTime(long persistElapsedTime) {
        this.persistElapsedTime = persistElapsedTime;
    }

    /**
     * getter method
     * 
     * @see CollectorInnerLog#succeedToPersistCount
     * @return the succeedToPersistCount
     */
    public int getSucceedToPersistCount() {
        return succeedToPersistCount;
    }

    /**
     * setter method
     * 
     * @see CollectorInnerLog#succeedToPersistCount
     * @param succeedToPersistCount
     *            the succeedToPersistCount to set
     */
    public void setSucceedToPersistCount(int succeedToPersistCount) {
        this.succeedToPersistCount = succeedToPersistCount;
    }

    /**
     * getter method
     * 
     * @see CollectorInnerLog#totalElapsedTime
     * @return the totalElapsedTime
     */
    public long getTotalElapsedTime() {
        return totalElapsedTime;
    }

    /**
     * setter method
     * 
     * @see CollectorInnerLog#totalElapsedTime
     * @param totalElapsedTime
     *            the totalElapsedTime to set
     */
    public void setTotalElapsedTime(long totalElapsedTime) {
        this.totalElapsedTime = totalElapsedTime;
    }

    @Override
    public String toString() {
        return "CollectorInnerLog [retrievedDataSize=" + retrievedDataSize + ", retrieveElapsedTime=" + retrieveElapsedTime + ", persistElapsedTime=" + persistElapsedTime
               + ", succeedToPersistCount=" + succeedToPersistCount + ", totalElapsedTime=" + totalElapsedTime + ", backupHistoryDataBean=" + backupHistoryDataBean + "]";
    }

    /**
     * getter method
     * 
     * @see CollectorInnerLog#backupHistoryDataBean
     * @return the backupHistoryDataBean
     */
    public BackupHistoryDataBean getBackupHistoryDataBean() {
        return backupHistoryDataBean;
    }

    /**
     * setter method
     * 
     * @see CollectorInnerLog#backupHistoryDataBean
     * @param backupHistoryDataBean
     *            the backupHistoryDataBean to set
     */
    public void setBackupHistoryDataBean(BackupHistoryDataBean backupHistoryDataBean) {
        this.backupHistoryDataBean = backupHistoryDataBean;
    }
}
