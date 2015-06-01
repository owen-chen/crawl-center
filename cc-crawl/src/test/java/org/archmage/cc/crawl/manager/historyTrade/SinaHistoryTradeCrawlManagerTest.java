/*
 * Create Author  : chen.chen.9
 * Create Date    : May 27, 2015
 * File Name      : SinaHistoryTradeCrawlManagerTest.java
 */

package org.archmage.cc.crawl.manager.historyTrade;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.AbstractTestng;
import org.archmage.cc.crawl.driver.historyTrade.SinaHistoryTradeCrawlDriver;
import org.archmage.cc.crawl.manager.historyTrade.mock.MockSinaHistoryTradeCrawlManager;
import org.archmage.cc.crawl.model.CrawlStatus;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * {@link SinaHistoryTradeCrawlManager} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 27, 2015
 */
public class SinaHistoryTradeCrawlManagerTest extends AbstractTestng {
    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private XmlConfiguration sysconfig;

    @Resource
    private MockSinaHistoryTradeCrawlManager mockSinaHistoryTradeCrawlManager;

    @Test
    public void crawlTest() {
        mockSinaHistoryTradeCrawlManager.crawl();

        List<CrawlStatus> crawlStatusList = mongoTemplate.findAll(CrawlStatus.class);
        Assert.assertTrue(CollectionUtils.isNotEmpty(crawlStatusList));
        Assert.assertEquals(crawlStatusList.size(), 1);

        CrawlStatus actualCrawlStatus = crawlStatusList.get(0);
        CrawlStatus expectedCrawlStatus = new CrawlStatus(SinaHistoryTradeCrawlDriver.INFOSOURCE_CODE, true, CrawlStatus.Status.FINISHED, 50, Integer.MAX_VALUE, false);
        expectedCrawlStatus.set_id(actualCrawlStatus.get_id());
        Assert.assertEquals(actualCrawlStatus.toString(), expectedCrawlStatus.toString());

        Set<String> set = mongoTemplate.getCollectionNames();
        Assert.assertTrue(CollectionUtils.isNotEmpty(set));
        boolean assertCondition = false;
        for (String collectionName : set) {
            if (collectionName.startsWith("HistoryTrade.")) {
                Assert.assertTrue(mongoTemplate.collectionExists(collectionName));
                mongoTemplate.dropCollection(collectionName);
                assertCondition = true;
            }
        }
        Assert.assertTrue(assertCondition);
    }

    @BeforeMethod
    public void beforeMethod() {
    }

    @AfterMethod
    @BeforeClass
    public void afterMethod() {
        Set<String> set = mongoTemplate.getCollectionNames();
        if (CollectionUtils.isNotEmpty(set)) {
            for (String collectionName : set) {
                if (collectionName.startsWith("HistoryTrade.")) {
                    mongoTemplate.dropCollection(collectionName);

                    continue;
                }
            }
        }

        mongoTemplate.dropCollection(CrawlStatus.class);
    }
}
