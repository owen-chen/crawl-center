/*
 * Create Author  : chen.chen.9
 * Create Date    : May 27, 2015
 * File Name      : SinaHistoryTradeCrawlManager.java
 */

package org.archmage.cc.crawl.manager.historyTrade;

import org.archmage.cc.common.thread.ConcurrentTaskExecutor;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.collector.manager.CollectorManager;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.driver.CrawlDriver;
import org.archmage.cc.crawl.driver.historyTrade.SinaHistoryTradeCrawlDriver;
import org.archmage.cc.crawl.manager.AbstractCrawlManager;
import org.archmage.cc.crawl.reader.CrawlConfigReader;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.response.ResponseObject;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * sina history trade crawl manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 27, 2015
 */
public class SinaHistoryTradeCrawlManager extends AbstractCrawlManager {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** sysconfig */
    private XmlConfiguration sysconfig;

    /** {@link LogContainer} */
    private LogContainer logContainer;

    /** {@link CrawlConfigReader} */
    private CrawlConfigReader crawlConfigReader;

    /** {@link JdbcTemplate} */
    private ExtendedDaoSupport daoSupport;

    /** {@link InfosourceRequestFactory} */
    private InfosourceRequestFactory<SinaHistoryTradeResponseObject> infosourceRequestFactory;

    /** {@link CollectorManager} */
    private CollectorManager collectorManager;

    /** {@link ConcurrentTaskExecutor} */
    private ConcurrentTaskExecutor concurrentTaskExecutor;

    @Override
    protected ConcurrentTaskExecutor getConcurrentTaskExecutor() {
        return concurrentTaskExecutor;
    }

    @Override
    protected LogContainer getLogContainer() {
        return logContainer;
    }

    @Override
    protected String getResponseClassSimpleName() {
        return SinaHistoryTradeResponseObject.class.getSimpleName();
    }

    @Override
    protected CollectorManager getCollectorManager() {
        return collectorManager;
    }

    @Override
    protected CrawlDriver<? extends ResponseObject> getCrawlDriver() {
        return new SinaHistoryTradeCrawlDriver(logContainer, daoSupport, infosourceRequestFactory);
    }

    @Override
    protected XmlConfiguration getSysconfig() {
        return sysconfig;
    }

    @Override
    protected Logger getLOGGER() {
        return LOGGER;
    }

    @Override
    protected CrawlConfigReader getCrawlConfigReader() {
        return crawlConfigReader;
    }

    @Override
    protected MongoTemplate getMongoTemplate() {
        return daoSupport.getMongoTemplate();
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlManager#sysconfig
     * @param sysconfig
     *            the sysconfig to set
     */
    public void setSysconfig(XmlConfiguration sysconfig) {
        this.sysconfig = sysconfig;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlManager#logContainer
     * @param logContainer
     *            the logContainer to set
     */
    public void setLogContainer(LogContainer logContainer) {
        this.logContainer = logContainer;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlManager#crawlConfigReader
     * @param crawlConfigReader
     *            the crawlConfigReader to set
     */
    public void setCrawlConfigReader(CrawlConfigReader crawlConfigReader) {
        this.crawlConfigReader = crawlConfigReader;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlManager#daoSupport
     * @param daoSupport
     *            the daoSupport to set
     */
    public void setDaoSupport(ExtendedDaoSupport daoSupport) {
        this.daoSupport = daoSupport;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlManager#infosourceRequestFactory
     * @param infosourceRequestFactory
     *            the infosourceRequestFactory to set
     */
    public void setInfosourceRequestFactory(InfosourceRequestFactory<SinaHistoryTradeResponseObject> infosourceRequestFactory) {
        this.infosourceRequestFactory = infosourceRequestFactory;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlManager#collectorManager
     * @param collectorManager
     *            the collectorManager to set
     */
    public void setCollectorManager(CollectorManager collectorManager) {
        this.collectorManager = collectorManager;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlManager#concurrentTaskExecutor
     * @param concurrentTaskExecutor
     *            the concurrentTaskExecutor to set
     */
    public void setConcurrentTaskExecutor(ConcurrentTaskExecutor concurrentTaskExecutor) {
        this.concurrentTaskExecutor = concurrentTaskExecutor;
    }

    /**
     * getter method
     * 
     * @see SinaHistoryTradeCrawlManager#daoSupport
     * @return the daoSupport
     */
    public ExtendedDaoSupport getDaoSupport() {
        return daoSupport;
    }

    /**
     * getter method
     * 
     * @see SinaHistoryTradeCrawlManager#infosourceRequestFactory
     * @return the infosourceRequestFactory
     */
    public InfosourceRequestFactory<SinaHistoryTradeResponseObject> getInfosourceRequestFactory() {
        return infosourceRequestFactory;
    }
}
