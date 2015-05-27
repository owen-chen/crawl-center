/*
 * Create Author  : chen.chen.9
 * Create Date    : May 27, 2015
 * File Name      : MockSinaHistoryTradeCrawlDriver.java
 */

package org.archmage.cc.crawl.manager.historyTrade.mock;

import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.driver.historyTrade.SinaHistoryTradeCrawlDriver;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.request.historyTrade.SinaHistoryTradeRequestObject;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;

/**
 * mock {@link SinaHistoryTradeCrawlDriver}
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 27, 2015
 */
public class MockSinaHistoryTradeCrawlDriver extends SinaHistoryTradeCrawlDriver {
    /**
     * constructor
     * 
     * @param logContainer
     * @param extendedDaoSupport
     * @param infosourceRequestFactory
     */
    public MockSinaHistoryTradeCrawlDriver(LogContainer logContainer, ExtendedDaoSupport extendedDaoSupport,
                                           InfosourceRequestFactory<SinaHistoryTradeResponseObject> infosourceRequestFactory) {
        super(logContainer, extendedDaoSupport, infosourceRequestFactory);
    }

    @Override
    public int obtainTodoRequest() throws CrawlErrorException {
        SinaHistoryTradeRequestObject sinaHistoryTradeRequestObject = new SinaHistoryTradeRequestObject();

        sinaHistoryTradeRequestObject.setCode(INFOSOURCE_CODE);
        sinaHistoryTradeRequestObject.setYear(2014);
        sinaHistoryTradeRequestObject.setMonth(4);
        sinaHistoryTradeRequestObject.setDay(30);
        sinaHistoryTradeRequestObject.setSymbol("sh600000");

        getTodoRequestContainer().unshift(sinaHistoryTradeRequestObject);

        return getTodoRequestContainer().size();
    }
}
