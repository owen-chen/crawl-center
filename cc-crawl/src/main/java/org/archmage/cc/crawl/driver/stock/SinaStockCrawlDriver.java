/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : SinaStockCrawlDriver.java
 */

package org.archmage.cc.crawl.driver.stock;

import org.archmage.cc.crawl.container.Container;
import org.archmage.cc.crawl.container.request.TodoRequestContainer;
import org.archmage.cc.crawl.container.request.VisitedRequestContainer;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.driver.AbstractCrawlDriver;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.request.stock.SinaStockRequestObject;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * sina stock crawl driver
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 21, 2015
 */
public class SinaStockCrawlDriver extends AbstractCrawlDriver<SinaStockResponseObject> {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** infosource code */
    public static final String INFOSOURCE_CODE = "10001";

    /** {@link LogContainer} */
    private LogContainer logContainer;

    /** {@link MongoTemplate} */
    private MongoTemplate mongoTemplate;

    /** {@link InfosourceRequestFactory} */
    private InfosourceRequestFactory<SinaStockResponseObject> infosourceRequestFactory;

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
    public SinaStockCrawlDriver(LogContainer logContainer, ExtendedDaoSupport extendedDaoSupport, InfosourceRequestFactory<SinaStockResponseObject> infosourceRequestFactory) {
        this.logContainer = logContainer;
        this.mongoTemplate = extendedDaoSupport.getMongoTemplate();
        this.infosourceRequestFactory = infosourceRequestFactory;
    }

    @Override
    public int obtainTodoRequest() {
        for (int i = 1; i <= 4; i++) {
            SinaStockRequestObject sinaStockRequestObject = new SinaStockRequestObject();
            sinaStockRequestObject.setCode(INFOSOURCE_CODE);
            sinaStockRequestObject.setPageNo(i);

            todoRequestContainer.unshift(sinaStockRequestObject);
        }

        return todoRequestContainer.size();
    }

    @Override
    protected LogContainer getLogContainer() {
        return logContainer;
    }

    @Override
    protected InfosourceRequestFactory<SinaStockResponseObject> getInfosourceRequestFactory() {
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
}
