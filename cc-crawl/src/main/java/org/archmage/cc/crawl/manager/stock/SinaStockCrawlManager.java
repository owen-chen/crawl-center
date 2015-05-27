/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : SinaStockCrawlManager.java
 */

package org.archmage.cc.crawl.manager.stock;

import org.archmage.cc.common.thread.ConcurrentTaskExecutor;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.collector.manager.CollectorManager;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.driver.CrawlDriver;
import org.archmage.cc.crawl.driver.stock.SinaStockCrawlDriver;
import org.archmage.cc.crawl.manager.AbstractCrawlManager;
import org.archmage.cc.crawl.reader.CrawlConfigReader;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.response.ResponseObject;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * sina stock crawl manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 21, 2015
 */
public class SinaStockCrawlManager extends AbstractCrawlManager {
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
    private InfosourceRequestFactory<SinaStockResponseObject> infosourceRequestFactory;

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
        return SinaStockResponseObject.class.getSimpleName();
    }

    @Override
    protected CollectorManager getCollectorManager() {
        return collectorManager;
    }

    @Override
    protected CrawlDriver<? extends ResponseObject> getCrawlDriver() {
        return new SinaStockCrawlDriver(logContainer, daoSupport, infosourceRequestFactory);
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
     * @see SinaStockCrawlManager#sysconfig
     * @param sysconfig
     *            the sysconfig to set
     */
    public void setSysconfig(XmlConfiguration sysconfig) {
        this.sysconfig = sysconfig;
    }

    /**
     * setter method
     * 
     * @see SinaStockCrawlManager#logContainer
     * @param logContainer
     *            the logContainer to set
     */
    public void setLogContainer(LogContainer logContainer) {
        this.logContainer = logContainer;
    }

    /**
     * setter method
     * 
     * @see SinaStockCrawlManager#crawlConfigReader
     * @param crawlConfigReader
     *            the crawlConfigReader to set
     */
    public void setCrawlConfigReader(CrawlConfigReader crawlConfigReader) {
        this.crawlConfigReader = crawlConfigReader;
    }

    /**
     * setter method
     * 
     * @see SinaStockCrawlManager#daoSupport
     * @param daoSupport
     *            the daoSupport to set
     */
    public void setDaoSupport(ExtendedDaoSupport daoSupport) {
        this.daoSupport = daoSupport;
    }

    /**
     * setter method
     * 
     * @see SinaStockCrawlManager#infosourceRequestFactory
     * @param infosourceRequestFactory
     *            the infosourceRequestFactory to set
     */
    public void setInfosourceRequestFactory(InfosourceRequestFactory<SinaStockResponseObject> infosourceRequestFactory) {
        this.infosourceRequestFactory = infosourceRequestFactory;
    }

    /**
     * setter method
     * 
     * @see SinaStockCrawlManager#collectorManager
     * @param collectorManager
     *            the collectorManager to set
     */
    public void setCollectorManager(CollectorManager collectorManager) {
        this.collectorManager = collectorManager;
    }

    /**
     * setter method
     * 
     * @see SinaStockCrawlManager#concurrentTaskExecutor
     * @param concurrentTaskExecutor
     *            the concurrentTaskExecutor to set
     */
    public void setConcurrentTaskExecutor(ConcurrentTaskExecutor concurrentTaskExecutor) {
        this.concurrentTaskExecutor = concurrentTaskExecutor;
    }
}
