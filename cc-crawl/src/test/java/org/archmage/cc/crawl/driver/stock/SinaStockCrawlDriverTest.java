/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : SinaStockCrawlDriverTest.java
 */

package org.archmage.cc.crawl.driver.stock;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.archmage.cc.crawl.AbstractTestng;
import org.archmage.cc.crawl.bean.EntireObject;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * {@link SinaStockCrawlDriver} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 21, 2015
 */
public class SinaStockCrawlDriverTest extends AbstractTestng {
    @Resource
    private InfosourceRequestFactory<SinaStockResponseObject> infosourceRequestFactory;

    @Resource
    private ExtendedDaoSupport daoSupport;

    @Resource
    private LogContainer logContainer;

    /** {@link SinaStockCrawlDriver} */
    private SinaStockCrawlDriver sinaStockCrawlDriver;

    @Test
    public void obtainTodoUrlTest() {
        sinaStockCrawlDriver.obtainTodoRequest();

        Assert.assertFalse(sinaStockCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertTrue(sinaStockCrawlDriver.getVisitedRequestContainer().empty());
    }

    @Test
    public void captureTest() {
        sinaStockCrawlDriver.obtainTodoRequest();

        // 第一次爬取
        Assert.assertTrue(sinaStockCrawlDriver.capture());
        Assert.assertFalse(sinaStockCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertFalse(sinaStockCrawlDriver.getVisitedRequestContainer().empty());
        Assert.assertEquals(sinaStockCrawlDriver.getVisitedRequestContainer().size(), 1);

        List<EntireObject> entireObjectList = daoSupport.getMongoTemplate().findAll(EntireObject.class, SinaStockResponseObject.class.getSimpleName());
        Assert.assertTrue(CollectionUtils.isNotEmpty(entireObjectList));
        Assert.assertEquals(entireObjectList.size(), 1);

        Assert.assertFalse(sinaStockCrawlDriver.isCrawlFinish());

        // 第二次爬取
        Assert.assertTrue(sinaStockCrawlDriver.capture());
        Assert.assertFalse(sinaStockCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertFalse(sinaStockCrawlDriver.getVisitedRequestContainer().empty());
        Assert.assertEquals(sinaStockCrawlDriver.getVisitedRequestContainer().size(), 2);

        entireObjectList = daoSupport.getMongoTemplate().findAll(EntireObject.class, SinaStockResponseObject.class.getSimpleName());
        Assert.assertTrue(CollectionUtils.isNotEmpty(entireObjectList));
        Assert.assertEquals(entireObjectList.size(), 2);

        Assert.assertFalse(sinaStockCrawlDriver.isCrawlFinish());
    }

    @Test
    public void doesCrawlFinishTest() {
        captureTest();
    }

    @BeforeMethod
    public void beforeClass() {
        sinaStockCrawlDriver = new SinaStockCrawlDriver(logContainer, daoSupport, infosourceRequestFactory);

        Assert.assertTrue(sinaStockCrawlDriver.getTodoRequestContainer().empty());
        Assert.assertTrue(sinaStockCrawlDriver.getVisitedRequestContainer().empty());

        Assert.assertTrue(CollectionUtils.isEmpty(daoSupport.getMongoTemplate().findAll(EntireObject.class, SinaStockResponseObject.class.getSimpleName())));
    }

    @BeforeClass
    @AfterMethod
    public void afterMethod() {
        daoSupport.getMongoTemplate().dropCollection(SinaStockResponseObject.class.getSimpleName());

        Assert.assertTrue(CollectionUtils.isEmpty(daoSupport.getMongoTemplate().findAll(EntireObject.class, SinaStockResponseObject.class.getSimpleName())));
    }
}
