/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : SinaStockCollectorManagerTest.java
 */

package org.archmage.cc.crawl.collector.manager.stock;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.AbstractTestng;
import org.archmage.cc.crawl.bean.EntireObject;
import org.archmage.cc.crawl.driver.stock.SinaStockCrawlDriver;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.infosource.dto.request.stock.SinaStockRequestObject;
import org.archmage.cc.infosource.dto.response.stock.Result;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.archmage.cc.model.stock.Stock;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * {@link SinaStockCollectorManager} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 21, 2015
 */
public class SinaStockCollectorManagerTest extends AbstractTestng {
    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private SinaStockCollectorManager sinaStockCollectorManager;

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Resource
    private XmlConfiguration sysconfig;

    /** {@link SinaStockResponseObject} */
    private static final SinaStockResponseObject sinaStockResponseObject = new SinaStockResponseObject();
    {
        sinaStockResponseObject.getResultList().add(new Result("sh600000", "600000", "浦发银行"));
        sinaStockResponseObject.getResultList().add(new Result("sh600004", "600004", "白云机场"));
        sinaStockResponseObject.setSuccess(true);

    }

    @Test
    public void collectTest() throws CrawlErrorException {
        List<EntireObject> list = mongoTemplate.findAll(EntireObject.class, SinaStockResponseObject.class.getSimpleName());
        Assert.assertEquals(list.size(), 1);

        sinaStockCollectorManager.collect();

        List<Stock> stockList = hibernateTemplate.loadAll(Stock.class);
        Assert.assertEquals(stockList.size(), 2);
        Result result1 = sinaStockResponseObject.getResultList().get(1);
        Assert.assertEquals(stockList.get(0).toString(), new Stock(stockList.get(0).getId(), result1.getName(), result1.getCode(), result1.getSymbol(),
                                                                   stockList.get(0).getAddTime()).toString());
        Result result2 = sinaStockResponseObject.getResultList().get(0);
        Assert.assertEquals(stockList.get(1).toString(), new Stock(stockList.get(1).getId(), result2.getName(), result2.getCode(), result2.getSymbol(),
                                                                   stockList.get(1).getAddTime()).toString());

        Assert.assertFalse(mongoTemplate.collectionExists(SinaStockResponseObject.class.getSimpleName()));

        Set<String> set = mongoTemplate.getCollectionNames();
        Assert.assertTrue(CollectionUtils.isNotEmpty(set));
        boolean assertCondition = false;
        for (String collectionName : set) {
            if (collectionName.startsWith(SinaStockResponseObject.class.getSimpleName() + sysconfig.getString("Collector.BackupEndName"))) {
                assertCondition = true;
            }
        }
        Assert.assertTrue(assertCondition);
    }

    @BeforeMethod
    public void beforeMethod() {
        SinaStockRequestObject stockRequestObject = new SinaStockRequestObject();
        stockRequestObject.setCode(SinaStockCrawlDriver.INFOSOURCE_CODE);

        mongoTemplate.save(new EntireObject(stockRequestObject, sinaStockResponseObject), SinaStockResponseObject.class.getSimpleName());

        List<EntireObject> list = mongoTemplate.findAll(EntireObject.class, SinaStockResponseObject.class.getSimpleName());
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).toString(), new EntireObject(stockRequestObject, sinaStockResponseObject).toString());
    }

    @BeforeClass
    @AfterMethod
    public void afterMethod() {
        mongoTemplate.dropCollection(SinaStockResponseObject.class.getSimpleName());

        Set<String> set = mongoTemplate.getCollectionNames();
        if (CollectionUtils.isNotEmpty(set)) {
            for (String collectionName : set) {
                if (collectionName.startsWith(SinaStockResponseObject.class.getSimpleName() + sysconfig.getString("Collector.BackupEndName"))) {
                    mongoTemplate.dropCollection(collectionName);

                    continue;
                }
            }
        }

        hibernateTemplate.deleteAll(hibernateTemplate.loadAll(Stock.class));
    }
}
