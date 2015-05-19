/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-18
 * File Name      : BackupHistoryDatasBean.java
 */

package org.archmage.cc.crawl.bean;

/**
 * backed history data bean
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-18
 */
public class BackupHistoryDataBean {
    /** original db name */
    private String originalName;

    /** new db name */
    private String toName;

    /** the elapsed time */
    private long elapsedTime;

    /** is successful */
    private boolean successful;

    /** command */
    private String command;

    /**
     * constructor
     * 
     * @param originalName
     *            originalName
     * @param toName
     *            toName
     * @param elapsedTime
     *            elapsedTime
     * @param successful
     *            successful
     * @param command
     *            command
     */
    public BackupHistoryDataBean(String originalName, String toName, long elapsedTime, boolean successful, String command) {
        super();
        this.originalName = originalName;
        this.toName = toName;
        this.elapsedTime = elapsedTime;
        this.successful = successful;
        this.command = command;
    }

    /**
     * getter method
     * 
     * @see BackupHistoryDataBean#originalName
     * @return the originalName
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * setter method
     * 
     * @see BackupHistoryDataBean#originalName
     * @param originalName
     *            the originalName to set
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * getter method
     * 
     * @see BackupHistoryDataBean#toName
     * @return the toName
     */
    public String getToName() {
        return toName;
    }

    /**
     * setter method
     * 
     * @see BackupHistoryDataBean#toName
     * @param toName
     *            the toName to set
     */
    public void setToName(String toName) {
        this.toName = toName;
    }

    /**
     * getter method
     * 
     * @see BackupHistoryDataBean#elapsedTime
     * @return the elapsedTime
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * setter method
     * 
     * @see BackupHistoryDataBean#elapsedTime
     * @param elapsedTime
     *            the elapsedTime to set
     */
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * getter method
     * 
     * @see BackupHistoryDataBean#successful
     * @return the successful
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * setter method
     * 
     * @see BackupHistoryDataBean#successful
     * @param successful
     *            the successful to set
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /**
     * getter method
     * 
     * @see BackupHistoryDataBean#command
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * setter method
     * 
     * @see BackupHistoryDataBean#command
     * @param command
     *            the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "BackupHistoryDatasBean [originalName=" + originalName + ", toName=" + toName + ", elapsedTime=" + elapsedTime + ", successful=" + successful + ", command="
               + command + "]";
    }
}
