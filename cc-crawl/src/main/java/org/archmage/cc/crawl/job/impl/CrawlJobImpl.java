/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-27
 * File Name      : CrawlJobImpl.java
 */

package org.archmage.cc.crawl.job.impl;

import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.job.CrawlJob;
import org.archmage.cc.crawl.manager.historyTrade.SinaHistoryTradeCrawlManager;
import org.archmage.cc.crawl.manager.stock.SinaStockCrawlManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * scheduled crawl job
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-27
 */
public class CrawlJobImpl implements CrawlJob {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** sysconfig */
    private XmlConfiguration sysconfig;

    /** {@link SinaStockCrawlManager} */
    private SinaStockCrawlManager sinaStockCrawlManager;

    /** {@link SinaHistoryTradeCrawlManager} */
    private SinaHistoryTradeCrawlManager sinaHistoryTradeCrawlManager;

    @Override
    public void crawlSinaStockJob() {
        if (isTestEnvironment()) {
            return;
        }

        sinaStockCrawlManager.crawl();
    }

    @Override
    public void crawlSinaHistoryTradeJob() {
        if (isTestEnvironment()) {
            return;
        }

        sinaHistoryTradeCrawlManager.crawl();
    }

    /**
     * 判断是否是单元测试环境
     * <p>
     * 
     * @author owen.chen, 2012-5-3
     * @return is test environment
     */
    private boolean isTestEnvironment() {
        boolean isTestEnvironment = sysconfig.getBoolean("IsTestEnvironment");
        if (isTestEnvironment) {
            LOGGER.info("Do not need to run crawl job in test environment");
        }

        return isTestEnvironment;
    }

    /**
     * setter method
     * 
     * @see CrawlJobImpl#sysconfig
     * @param sysconfig
     *            the sysconfig to set
     */
    public void setSysconfig(XmlConfiguration sysconfig) {
        this.sysconfig = sysconfig;
    }

    /**
     * setter method
     * 
     * @see CrawlJobImpl#sinaStockCrawlManager
     * @param sinaStockCrawlManager
     *            the sinaStockCrawlManager to set
     */
    public void setSinaStockCrawlManager(SinaStockCrawlManager sinaStockCrawlManager) {
        this.sinaStockCrawlManager = sinaStockCrawlManager;
    }

    /**
     * setter method
     * 
     * @see CrawlJobImpl#sinaHistoryTradeCrawlManager
     * @param sinaHistoryTradeCrawlManager
     *            the sinaHistoryTradeCrawlManager to set
     */
    public void setSinaHistoryTradeCrawlManager(SinaHistoryTradeCrawlManager sinaHistoryTradeCrawlManager) {
        this.sinaHistoryTradeCrawlManager = sinaHistoryTradeCrawlManager;
    }
}
