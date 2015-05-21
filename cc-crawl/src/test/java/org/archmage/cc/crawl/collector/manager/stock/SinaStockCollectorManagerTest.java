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
import org.archmage.cc.infosource.dto.request.stock.SinaStockRequestObject;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.springframework.data.mongodb.core.MongoTemplate;
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
    private XmlConfiguration sysconfig;

    /** {@link SinaStockRequestObject} */
    private SinaStockRequestObject stockRequestObject = new SinaStockRequestObject();
    {
        stockRequestObject.setCode(SinaStockCrawlDriver.INFOSOURCE_CODE);
    }

    @Test
    public void collectTest() {
        List<EntireObject> list = mongoTemplate.findAll(EntireObject.class, SinaStockResponseObject.class.getSimpleName());
        Assert.assertEquals(list.size(), 1);

        sinaStockCollectorManager.collect();

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
        SinaStockResponseObject sinaStockResponseObject = new SinaStockResponseObject();
        sinaStockResponseObject.setSuccess(true);

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
    }
}
