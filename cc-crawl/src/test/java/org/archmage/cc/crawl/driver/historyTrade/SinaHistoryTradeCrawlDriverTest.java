/*
 * Create Author  : chen.chen.9
 * Create Date    : May 26, 2015
 * File Name      : SinaHistoryTradeCrawlDriverTest.java
 */

package org.archmage.cc.crawl.driver.historyTrade;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.archmage.cc.crawl.AbstractTestng;
import org.archmage.cc.crawl.bean.EntireObject;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.driver.historyTrade.mock.MockSinaHistoryTradeCrawlDriver;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.request.historyTrade.SinaHistoryTradeRequestObject;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * {@link SinaHistoryTradeCrawlDriver} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 26, 2015
 */
public class SinaHistoryTradeCrawlDriverTest extends AbstractTestng {
    @Resource
    private InfosourceRequestFactory<SinaHistoryTradeResponseObject> infosourceRequestFactory;

    @Resource
    private ExtendedDaoSupport daoSupport;

    @Resource
    private LogContainer logContainer;

    /** {@link MockSinaHistoryTradeCrawlDriver} */
    private MockSinaHistoryTradeCrawlDriver sinaHistoryTradeCrawlDriver;

    @Test
    public void obtainTodoUrlTest() throws CrawlErrorException {
        sinaHistoryTradeCrawlDriver.obtainTodoRequest();

        Assert.assertFalse(sinaHistoryTradeCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertTrue(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().empty());
    }

    @Test
    public void generateCollectionNameTest() {
        SinaHistoryTradeRequestObject sinaHistoryTradeRequestObject = new SinaHistoryTradeRequestObject();
        sinaHistoryTradeRequestObject.setSymbol("sh600100");
        sinaHistoryTradeRequestObject.setYear(2014);

        Assert.assertEquals(sinaHistoryTradeCrawlDriver.generateCollectionName(sinaHistoryTradeRequestObject), "HistoryTrade.sh.10.2014");
    }

    @Test
    public void captureTest() throws CrawlErrorException {
        sinaHistoryTradeCrawlDriver.obtainTodoRequest();

        // 第一次爬取
        Assert.assertTrue(sinaHistoryTradeCrawlDriver.capture());
        Assert.assertTrue(sinaHistoryTradeCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertFalse(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().empty());
        Assert.assertEquals(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().size(), 1);

        List<EntireObject> entireObjectList = daoSupport.getMongoTemplate().findAll(EntireObject.class, "HistoryTrade.sh.0.2015");
        Assert.assertTrue(CollectionUtils.isNotEmpty(entireObjectList));
        Assert.assertTrue(entireObjectList.size() > 2000);

        Assert.assertTrue(sinaHistoryTradeCrawlDriver.isCrawlFinish());

        // 第二次爬取
        Assert.assertFalse(sinaHistoryTradeCrawlDriver.capture());
    }

    @Test
    public void doesCrawlFinishTest() throws CrawlErrorException {
        captureTest();
    }

    @BeforeMethod
    public void beforeClass() {
        sinaHistoryTradeCrawlDriver = new MockSinaHistoryTradeCrawlDriver(logContainer, daoSupport, infosourceRequestFactory);

        Assert.assertTrue(sinaHistoryTradeCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertTrue(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().empty());

        Assert.assertTrue(CollectionUtils.isEmpty(daoSupport.getMongoTemplate().findAll(EntireObject.class, "HistoryTrade.sh.0.2015")));
    }

    @BeforeClass
    @AfterMethod
    public void afterMethod() {
        daoSupport.getMongoTemplate().dropCollection("HistoryTrade.sh.0.2015");

        Assert.assertTrue(CollectionUtils.isEmpty(daoSupport.getMongoTemplate().findAll(EntireObject.class, "HistoryTrade.sh.0.2015")));
    }
}
