/*
 * Create Author  : chen.chen.9
 * Create Date    : May 26, 2015
 * File Name      : SinaHistoryTradeCrawlDriver.java
 */

package org.archmage.cc.crawl.driver.historyTrade;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.archmage.cc.crawl.bean.ErrorCode;
import org.archmage.cc.crawl.container.Container;
import org.archmage.cc.crawl.container.request.TodoRequestContainer;
import org.archmage.cc.crawl.container.request.VisitedRequestContainer;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.driver.AbstractCrawlDriver;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.request.historyTrade.SinaHistoryTradeRequestObject;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;
import org.archmage.cc.model.stock.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.CollectionUtils;

/**
 * sina history trade crawl driver
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 26, 2015
 */
public class SinaHistoryTradeCrawlDriver extends AbstractCrawlDriver<SinaHistoryTradeResponseObject> {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** infosource code */
    public static final String INFOSOURCE_CODE = "10002";

    /** {@link LogContainer} */
    private LogContainer logContainer;

    /** {@link MongoTemplate} */
    private MongoTemplate mongoTemplate;

    /** {@link HibernateTemplate} */
    private HibernateTemplate hibernateTemplate;

    /** {@link InfosourceRequestFactory} */
    private InfosourceRequestFactory<SinaHistoryTradeResponseObject> infosourceRequestFactory;

    /** todoRequestContainer */
    private Container<RequestObject> todoRequestContainer = new TodoRequestContainer();

    /** visitedRequestContainer */
    private Container<String> visitedRequestContainer = new VisitedRequestContainer();

    /**
     * constructor
     * 
     * @param logContainer
     *            {@link LogContainer}
     * @param extendedDaoSupport
     *            {@link ExtendedDaoSupport}
     * @param infosourceRequestFactory
     *            {@link InfosourceRequestFactory}
     */
    public SinaHistoryTradeCrawlDriver(LogContainer logContainer, ExtendedDaoSupport extendedDaoSupport,
                                       InfosourceRequestFactory<SinaHistoryTradeResponseObject> infosourceRequestFactory) {
        this.logContainer = logContainer;
        this.mongoTemplate = extendedDaoSupport.getMongoTemplate();
        this.infosourceRequestFactory = infosourceRequestFactory;
        this.hibernateTemplate = extendedDaoSupport.getHibernateTemplate();
    }

    @Override
    public int obtainTodoRequest() throws CrawlErrorException {
        List<Stock> stockList = hibernateTemplate.loadAll(Stock.class);
        if (CollectionUtils.isEmpty(stockList)) {
            throw new CrawlErrorException(ErrorCode.NO_STOCK_INFO);
        }

        for (Stock stock : stockList) {
            if (!StringUtils.startsWith(stock.getSymbol(), "sh")) {
                continue;
            }

            for (int year = 2015; year <= 2015; year++) {
                for (int month = 1; month <= 5; month++) {
                    for (int day = 1; day <= 31; day++) {
                        SinaHistoryTradeRequestObject sinaHistoryTradeRequestObject = new SinaHistoryTradeRequestObject();

                        sinaHistoryTradeRequestObject.setCode(INFOSOURCE_CODE);
                        sinaHistoryTradeRequestObject.setYear(year);
                        sinaHistoryTradeRequestObject.setMonth(month);
                        sinaHistoryTradeRequestObject.setDay(day);
                        sinaHistoryTradeRequestObject.setSymbol(stock.getSymbol());

                        todoRequestContainer.unshift(sinaHistoryTradeRequestObject);
                    }
                }
            }
        }

        return todoRequestContainer.size();
    }

    @Override
    protected LogContainer getLogContainer() {
        return logContainer;
    }

    @Override
    protected InfosourceRequestFactory<SinaHistoryTradeResponseObject> getInfosourceRequestFactory() {
        return infosourceRequestFactory;
    }

    @Override
    protected Container<RequestObject> getTodoRequestContainer() {
        return todoRequestContainer;
    }

    @Override
    protected Container<String> getVisitedRequestContainer() {
        return visitedRequestContainer;
    }

    @Override
    protected Logger getLOGGER() {
        return LOGGER;
    }

    @Override
    protected MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlDriver#logContainer
     * @param logContainer
     *            the logContainer to set
     */
    public void setLogContainer(LogContainer logContainer) {
        this.logContainer = logContainer;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlDriver#mongoTemplate
     * @param mongoTemplate
     *            the mongoTemplate to set
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlDriver#todoRequestContainer
     * @param todoRequestContainer
     *            the todoRequestContainer to set
     */
    public void setTodoRequestContainer(Container<RequestObject> todoRequestContainer) {
        this.todoRequestContainer = todoRequestContainer;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlDriver#visitedRequestContainer
     * @param visitedRequestContainer
     *            the visitedRequestContainer to set
     */
    public void setVisitedRequestContainer(Container<String> visitedRequestContainer) {
        this.visitedRequestContainer = visitedRequestContainer;
    }

    /**
     * setter method
     * 
     * @see SinaHistoryTradeCrawlDriver#infosourceRequestFactory
     * @param infosourceRequestFactory
     *            the infosourceRequestFactory to set
     */
    public void setInfosourceRequestFactory(InfosourceRequestFactory<SinaHistoryTradeResponseObject> infosourceRequestFactory) {
        this.infosourceRequestFactory = infosourceRequestFactory;
    }

}
