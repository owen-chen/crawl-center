/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : CrawlConfigReader.java
 */

package org.archmage.cc.crawl.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.model.CrawlStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * crawl config reader
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public class CrawlConfigReader {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** crawlConfig of {@link XmlConfiguration} */
    private XmlConfiguration crawlConfig;

    /** key：manager class name，value：{@link CrawlStatus} */
    private Map<String, CrawlStatus> crawlStatusMap = new HashMap<String, CrawlStatus>();

    /**
     * initialize crawl config
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     */
    public void init() {
        List<?> targetList = crawlConfig.getConfig().configurationsAt("Crawl.Targets.Target");

        if (CollectionUtils.isEmpty(targetList)) {
            LOGGER.error("None of targets can be found in {}", crawlConfig.getConfigFilePath());

            return;
        }

        for (int i = 0; i < targetList.size(); i++) {
            String clazz = crawlConfig.getString("Crawl.Targets.Target(" + i + ").Class");

            CrawlStatus crawlStatus = new CrawlStatus();
            crawlStatus.setRunnable(crawlConfig.getBoolean("Crawl.Targets.Target(" + i + ").Runnable"));
            crawlStatus.setCode(crawlConfig.getString("Crawl.Targets.Target(" + i + ").Code"));
            crawlStatus.setThreadPoolSize(crawlConfig.getInt("Crawl.Targets.Target(" + i + ").ThreadPoolSize"));
            long timeout = crawlConfig.getInt("Crawl.Targets.Target(" + i + ").Timeout");
            crawlStatus.setTimeout((timeout > 0 ? timeout : 1 * 60 * 60) * 1000);

            crawlStatusMap.put(clazz, crawlStatus);
        }
    }

    /**
     * retrieve {@link CrawlStatus}
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     * @param className
     *            manager class name
     * @return {@link CrawlStatus}
     */
    public CrawlStatus retrieveCertainCrawlConfig(String className) {
        return crawlStatusMap.get(className);
    }

    /**
     * setter method
     * 
     * @see CrawlConfigReader#crawlConfig
     * @param crawlConfig
     *            the crawlConfig to set
     */
    public void setCrawlConfig(XmlConfiguration crawlConfig) {
        this.crawlConfig = crawlConfig;
    }
}
