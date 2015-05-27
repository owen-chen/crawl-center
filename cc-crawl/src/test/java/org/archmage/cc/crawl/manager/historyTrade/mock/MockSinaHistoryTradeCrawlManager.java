/*
 * Create Author  : chen.chen.9
 * Create Date    : May 27, 2015
 * File Name      : MockSinaHistoryTradeCrawlManager.java
 */

package org.archmage.cc.crawl.manager.historyTrade.mock;

import org.archmage.cc.crawl.driver.CrawlDriver;
import org.archmage.cc.crawl.manager.historyTrade.SinaHistoryTradeCrawlManager;
import org.archmage.cc.infosource.dto.response.ResponseObject;

/**
 * mock {@link SinaHistoryTradeCrawlManager}
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 27, 2015
 */
public class MockSinaHistoryTradeCrawlManager extends SinaHistoryTradeCrawlManager {
    @Override
    protected CrawlDriver<? extends ResponseObject> getCrawlDriver() {
        return new MockSinaHistoryTradeCrawlDriver(getLogContainer(), getDaoSupport(), getInfosourceRequestFactory());
    }

    @Override
    protected String getClassName() {
        return SinaHistoryTradeCrawlManager.class.getName();
    }
}
