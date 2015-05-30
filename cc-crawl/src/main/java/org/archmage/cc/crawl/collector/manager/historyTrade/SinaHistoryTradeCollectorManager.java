/*
 * Create Author  : chen.chen.9
 * Create Date    : May 27, 2015
 * File Name      : StockHistoryTradeCollectorManager.java
 */

package org.archmage.cc.crawl.collector.manager.historyTrade;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.bean.EntireObject;
import org.archmage.cc.crawl.bean.ErrorCode;
import org.archmage.cc.crawl.collector.manager.AbstractCollectorManager;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.request.historyTrade.SinaHistoryTradeRequestObject;
import org.archmage.cc.infosource.dto.response.historyTrade.Result;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.model.stock.HistoryTrade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.CollectionUtils;

/**
 * sina history trade collector manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 27, 2015
 */
public class SinaHistoryTradeCollectorManager extends AbstractCollectorManager {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** infosource code */
    private final String INFOSOURCE_CODE = "10002";

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
        SinaHistoryTradeRequestObject requestObject = (SinaHistoryTradeRequestObject) entireObject.getRequestObject();
        SinaHistoryTradeResponseObject responseObject = (SinaHistoryTradeResponseObject) entireObject.getResponseObject();

        if (responseObject.isClosed()) {
            return;
        }

        List<Result> resultList = responseObject.getResultList();
        if (CollectionUtils.isEmpty(resultList)) {
            throw new CrawlErrorException(ErrorCode.NO_HISTORY_TRADE_THIS_DAY);
        }

        for (Result result : resultList) {
            HistoryTrade historyTrade = new HistoryTrade();

            historyTrade.setCurrent(Float.parseFloat(result.getCurrent()));
            historyTrade.setDealAmount(Integer.parseInt(result.getDealAmount()));
            historyTrade.setDealFigure(Integer.parseInt(result.getDealFigure()));

            String quoteTrend = result.getQuoteTrend();
            if (!StringUtils.equals(quoteTrend, "--")) {
                historyTrade.setQuoteTrend(Float.parseFloat(quoteTrend));
            }

            historyTrade.setSymbol(requestObject.getSymbol());
            String feature = result.getFeature();
            if (StringUtils.equals(feature, "买盘")) {
                historyTrade.setFeature(1);
            }
            else if (StringUtils.equals(feature, "中性盘")) {
                historyTrade.setFeature(0);
            }
            else if (StringUtils.equals(feature, "卖盘")) {
                historyTrade.setFeature(-1);
            }
            else {
                throw new CrawlErrorException(ErrorCode.UNKNOWN_HISTORY_TRADE_FEATURE);
            }

            Calendar calendar = Calendar.getInstance();
            calendar.set(requestObject.getYear(), requestObject.getMonth() - 1, requestObject.getDay(), Integer.parseInt(result.getHour()), Integer.parseInt(result.getMinute()),
                         Integer.parseInt(result.getSecond()));
            calendar.set(Calendar.MILLISECOND, 0);
            historyTrade.setCurrentTime(calendar.getTimeInMillis());

            daoSupport.getHibernateTemplate().save(historyTrade);
        }
    }

    @Override
    protected int getPageSize() {
        return 50;
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
        return SinaHistoryTradeResponseObject.class.getSimpleName();
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
     * @see SinaHistoryTradeCollectorManager#logContainer
     * @param logContainer
     *            the logContainer to set
     */
    public void setLogContainer(LogContainer logContainer) {
        this.logContainer = logContainer;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCollectorManager#adminMongoTemplate
     * @param adminMongoTemplate
     *            the adminMongoTemplate to set
     */
    public void setAdminMongoTemplate(MongoTemplate adminMongoTemplate) {
        this.adminMongoTemplate = adminMongoTemplate;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCollectorManager#sysconfig
     * @param sysconfig
     *            the sysconfig to set
     */
    public void setSysconfig(XmlConfiguration sysconfig) {
        this.sysconfig = sysconfig;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCollectorManager#daoSupport
     * @param daoSupport
     *            the daoSupport to set
     */
    public void setDaoSupport(ExtendedDaoSupport daoSupport) {
        this.daoSupport = daoSupport;
    }
}
