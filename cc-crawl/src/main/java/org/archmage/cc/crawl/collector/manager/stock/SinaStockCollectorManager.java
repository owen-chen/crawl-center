/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : SinaStockCollectorManager.java
 */

package org.archmage.cc.crawl.collector.manager.stock;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.bean.EntireObject;
import org.archmage.cc.crawl.bean.ErrorCode;
import org.archmage.cc.crawl.collector.manager.AbstractCollectorManager;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.response.stock.Result;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.archmage.cc.model.stock.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * sina stock collector manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 21, 2015
 */
public class SinaStockCollectorManager extends AbstractCollectorManager {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** infosource code */
    private final String INFOSOURCE_CODE = "10001";

    /** {@link LogContainer} */
    private LogContainer logContainer;

    /** {@link MongoTemplate} */
    private MongoTemplate adminMongoTemplate;

    /** sysconfig */
    private XmlConfiguration sysconfig;

    /** {@link ExtendedDaoSupport} */
    private ExtendedDaoSupport daoSupport;

    @Override
    protected void putIntoDB(EntireObject entireObject) throws CrawlErrorException {
        SinaStockResponseObject stockResponseObject = (SinaStockResponseObject) entireObject.getResponseObject();

        List<Result> resultList = stockResponseObject.getResultList();
        if (CollectionUtils.isEmpty(resultList)) {
            throw new CrawlErrorException(ErrorCode.RESPONSE_DATA_COULD_NOT_BE_EMPTY);
        }

        for (Result result : resultList) {
            List<Stock> stockList = daoSupport.getHibernateTemplate().findByNamedParam("SELECT s FROM Stock s WHERE s.symbol = :symbol", "symbol", result.getSymbol());
            if (CollectionUtils.isNotEmpty(stockList) && stockList.size() >= 2) {

                throw new CrawlErrorException(ErrorCode.TWO_MORE_SIMILAR_STOCKS);
            }

            if (CollectionUtils.isNotEmpty(stockList) && stockList.size() == 1) {
                continue;
            }

            Stock stock = new Stock();

            stock.setCode(result.getCode());
            stock.setSymbol(result.getSymbol());
            stock.setName(result.getName());
            stock.setAddTime(new Date().getTime());

            daoSupport.getHibernateTemplate().save(stock);
        }
    }

    @Override
    protected String getInfosourceCode() {
        return INFOSOURCE_CODE;
    }

    @Override
    protected ExtendedDaoSupport getDaoSupport() {
        return daoSupport;
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
    protected Logger getLOGGER() {
        return LOGGER;
    }

    @Override
    protected MongoTemplate getAdminMongoTemplate() {
        return adminMongoTemplate;
    }

    @Override
    protected XmlConfiguration getSysconfig() {
        return sysconfig;
    }

    /**
     * setter method
     * 
     * @see SinaStockCollectorManager#logContainer
     * @param logContainer
     *            the logContainer to set
     */
    public void setLogContainer(LogContainer logContainer) {
        this.logContainer = logContainer;
    }

    /**
     * setter method
     * 
     * @see SinaStockCollectorManager#adminMongoTemplate
     * @param adminMongoTemplate
     *            the adminMongoTemplate to set
     */
    public void setAdminMongoTemplate(MongoTemplate adminMongoTemplate) {
        this.adminMongoTemplate = adminMongoTemplate;
    }

    /**
     * setter method
     * 
     * @see SinaStockCollectorManager#sysconfig
     * @param sysconfig
     *            the sysconfig to set
     */
    public void setSysconfig(XmlConfiguration sysconfig) {
        this.sysconfig = sysconfig;
    }

    /**
     * setter method
     * 
     * @see SinaStockCollectorManager#daoSupport
     * @param daoSupport
     *            the daoSupport to set
     */
    public void setDaoSupport(ExtendedDaoSupport daoSupport) {
        this.daoSupport = daoSupport;
    }
}
