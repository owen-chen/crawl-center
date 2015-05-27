/*
 * Create Author  : chen.chen.9
 * Create Date    : May 26, 2015
 * File Name      : SinaHistoryTradeCrawlDriverTest.java
 */

package org.archmage.cc.crawl.driver.historyTrade;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.archmage.cc.crawl.AbstractTestng;
import org.archmage.cc.crawl.bean.EntireObject;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.driver.stock.SinaStockCrawlDriver;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;
import org.archmage.cc.model.stock.Stock;
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

    /** {@link SinaStockCrawlDriver} */
    private SinaHistoryTradeCrawlDriver sinaHistoryTradeCrawlDriver;

    @Test
    public void obtainTodoUrlTest() throws CrawlErrorException {
        sinaHistoryTradeCrawlDriver.obtainTodoRequest();

        Assert.assertFalse(sinaHistoryTradeCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertTrue(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().empty());
    }

    @Test
    public void captureTest() throws CrawlErrorException {
        sinaHistoryTradeCrawlDriver.obtainTodoRequest();

        // 第一次爬取
        Assert.assertTrue(sinaHistoryTradeCrawlDriver.capture());
        Assert.assertFalse(sinaHistoryTradeCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertFalse(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().empty());
        Assert.assertEquals(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().size(), 1);

        List<EntireObject> entireObjectList = daoSupport.getMongoTemplate().findAll(EntireObject.class, SinaHistoryTradeResponseObject.class.getSimpleName());
        Assert.assertTrue(CollectionUtils.isNotEmpty(entireObjectList));
        Assert.assertEquals(entireObjectList.size(), 1);

        Assert.assertFalse(sinaHistoryTradeCrawlDriver.isCrawlFinish());

        // 第二次爬取
        Assert.assertTrue(sinaHistoryTradeCrawlDriver.capture());
        Assert.assertFalse(sinaHistoryTradeCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertFalse(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().empty());
        Assert.assertEquals(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().size(), 2);

        entireObjectList = daoSupport.getMongoTemplate().findAll(EntireObject.class, SinaHistoryTradeResponseObject.class.getSimpleName());
        Assert.assertTrue(CollectionUtils.isNotEmpty(entireObjectList));
        Assert.assertEquals(entireObjectList.size(), 2);

        Assert.assertFalse(sinaHistoryTradeCrawlDriver.isCrawlFinish());
    }

    @Test
    public void doesCrawlFinishTest() throws CrawlErrorException {
        captureTest();
    }

    @BeforeMethod
    public void beforeClass() {
        sinaHistoryTradeCrawlDriver = new SinaHistoryTradeCrawlDriver(logContainer, daoSupport, infosourceRequestFactory);

        Assert.assertTrue(sinaHistoryTradeCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertTrue(sinaHistoryTradeCrawlDriver.getVisitedRequestContainer().empty());

        Assert.assertTrue(CollectionUtils.isEmpty(daoSupport.getMongoTemplate().findAll(EntireObject.class, SinaHistoryTradeResponseObject.class.getSimpleName())));

        daoSupport.getHibernateTemplate().save(new Stock("sh600000", "600000", "浦发银行", new Date().getTime()));
        daoSupport.getHibernateTemplate().save(new Stock("sh600004", "600004", "白云机场", new Date().getTime()));
        Assert.assertEquals(daoSupport.getHibernateTemplate().loadAll(Stock.class).size(), 2);
    }

    @BeforeClass
    @AfterMethod
    public void afterMethod() {
        daoSupport.getMongoTemplate().dropCollection(SinaHistoryTradeResponseObject.class.getSimpleName());

        Assert.assertTrue(CollectionUtils.isEmpty(daoSupport.getMongoTemplate().findAll(EntireObject.class, SinaHistoryTradeResponseObject.class.getSimpleName())));

        daoSupport.getHibernateTemplate().deleteAll(daoSupport.getHibernateTemplate().loadAll(Stock.class));
    }
}
