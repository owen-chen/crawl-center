/*
 * Create Author  : chen.chen.9
 * Create Date    : May 27, 2015
 * File Name      : SinaHistoryTradeCollectorManagerTest.java
 */

package org.archmage.cc.crawl.collector.manager.historyTrade;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.AbstractTestng;
import org.archmage.cc.crawl.bean.EntireObject;
import org.archmage.cc.crawl.driver.historyTrade.SinaHistoryTradeCrawlDriver;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.infosource.dto.request.historyTrade.SinaHistoryTradeRequestObject;
import org.archmage.cc.infosource.dto.response.historyTrade.Result;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.model.stock.HistoryTrade;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@Link SinaHistoryTradeCollectorManager} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 27, 2015
 */
public class SinaHistoryTradeCollectorManagerTest extends AbstractTestng {
    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private SinaHistoryTradeCollectorManager sinaHistoryTradeCollectorManager;

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Resource
    private XmlConfiguration sysconfig;

    @Test(dataProvider = "dataProvider")
    public void collectTest(boolean isClosed, int year, int month, int day, int hour, int minute, int second, String symbol, String current, String quoteTrend, String dealAmount,
                            String dealFigure, String feature, int featureAlias, float quoteTrendAlias) throws CrawlErrorException {
        SinaHistoryTradeResponseObject sinaHistoryTradeResponseObject = new SinaHistoryTradeResponseObject(Arrays.asList(new Result(Integer.toString(hour),
                                                                                                                                    Integer.toString(minute),
                                                                                                                                    Integer.toString(second), current, quoteTrend,
                                                                                                                                    dealAmount, dealFigure, feature)), isClosed);
        sinaHistoryTradeResponseObject.setSuccess(true);
        SinaHistoryTradeRequestObject stockHistoryTradeRequestObject = new SinaHistoryTradeRequestObject(SinaHistoryTradeCrawlDriver.INFOSOURCE_CODE, symbol, year, month, day);

        mongoTemplate.save(new EntireObject(stockHistoryTradeRequestObject, sinaHistoryTradeResponseObject), SinaHistoryTradeResponseObject.class.getSimpleName());

        List<EntireObject> list = mongoTemplate.findAll(EntireObject.class, SinaHistoryTradeResponseObject.class.getSimpleName());
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).toString(), new EntireObject(stockHistoryTradeRequestObject, sinaHistoryTradeResponseObject).toString());

        sinaHistoryTradeCollectorManager.collect();

        List<HistoryTrade> historyTradeList = hibernateTemplate.loadAll(HistoryTrade.class);
        Assert.assertEquals(historyTradeList.size(), 1);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(year, month - 1, day, hour, minute, second);
        calendar1.set(Calendar.MILLISECOND, 0);
        Assert.assertEquals(historyTradeList.get(0).toString(),
                            new HistoryTrade(historyTradeList.get(0).getId(), symbol, calendar1.getTimeInMillis(), Float.parseFloat(current), quoteTrendAlias,
                                             Integer.parseInt(dealAmount), Long.parseLong(dealFigure), featureAlias).toString());

        Assert.assertFalse(mongoTemplate.collectionExists(SinaHistoryTradeResponseObject.class.getSimpleName()));

        Set<String> set = mongoTemplate.getCollectionNames();
        Assert.assertTrue(CollectionUtils.isNotEmpty(set));
        boolean assertCondition = false;
        for (String collectionName : set) {
            if (collectionName.startsWith(SinaHistoryTradeResponseObject.class.getSimpleName() + sysconfig.getString("Collector.BackupEndName"))) {
                assertCondition = true;
            }
        }
        Assert.assertTrue(assertCondition);
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return new Object[][] { { false, 2014, 3, 20, 9, 53, 30, "sh600000", "5.78", "-0.58", "305", "14839523", "买盘", 1, -0.58f },
                               { false, 2014, 3, 20, 11, 5, 49, "sh600000", "13.78", "0.68", "3115", "148439523", "中性盘", 0, 0.68f },
                               { false, 2014, 3, 20, 14, 39, 5, "sh600000", "51.3", "--", "0", "5834", "卖盘", -1, 0f } };
    }

    @BeforeMethod
    public void beforeMethod() {
    }

    @BeforeClass
    @AfterMethod
    public void afterMethod() {
        mongoTemplate.dropCollection(SinaHistoryTradeResponseObject.class.getSimpleName());

        Set<String> set = mongoTemplate.getCollectionNames();
        if (CollectionUtils.isNotEmpty(set)) {
            for (String collectionName : set) {
                if (collectionName.startsWith(SinaHistoryTradeResponseObject.class.getSimpleName() + sysconfig.getString("Collector.BackupEndName"))) {
                    mongoTemplate.dropCollection(collectionName);

                    continue;
                }
            }
        }

        hibernateTemplate.deleteAll(hibernateTemplate.loadAll(HistoryTrade.class));
    }
}
