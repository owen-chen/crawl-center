/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-26
 * File Name      : CrawlTask.java
 */

package org.archmage.cc.crawl.bean;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.archmage.cc.crawl.bean.log.CrawlJobLogBean;
import org.archmage.cc.crawl.bean.log.CrawlTaskLogBean;
import org.archmage.cc.crawl.driver.CrawlDriver;
import org.archmage.cc.framework.log.LogContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the crawl task
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-26
 */
public class CrawlTask implements Runnable {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** 循环爬取间隔时间 */
    private final long CYCLE_CRAWL_INTERVAL = 1000;

    /** {@link CrawlDriver} */
    private CrawlDriver<?> driver;

    /** {@link CrawlFinished}, 如果爬取操作已经全部结束，需要关闭任务，停止爬取操作 */
    private CrawlFinished needFinished;

    /** {@link LogContainer} */
    private LogContainer logContainer;

    /** {@link CrawlJobLogBean} */
    private CrawlJobLogBean crawlJobLogBean;

    /**
     * constructor
     * 
     * @param driver
     *            {@link CrawlDriver}
     * @param needFinished
     *            {@link CrawlFinished}, 如果爬取操作已经全部结束，需要关闭任务，停止爬取操作
     */
    public CrawlTask(CrawlDriver<?> driver, CrawlFinished needFinished, LogContainer logContainer, CrawlJobLogBean crawlJobLogBean) {
        this.driver = driver;
        this.needFinished = needFinished;
        this.logContainer = logContainer;
        this.crawlJobLogBean = crawlJobLogBean;
    }

    @Override
    public void run() {
        CrawlTaskLogBean crawlTaskLogBean = (CrawlTaskLogBean) logContainer.initializeLogBean(Thread.currentThread().getId(), new CrawlTaskLogBean());
        crawlJobLogBean.getCrawlTaskLogList().add(crawlTaskLogBean);

        try {
            while (!needFinished.isFinished()) {
                try {
                    Thread.sleep(CYCLE_CRAWL_INTERVAL);
                }
                catch (InterruptedException e) {
                    LOGGER.error(ExceptionUtils.getStackTrace(e));
                }

                driver.capture();
            }
        }
        finally {
            logContainer.removeLogBean(Thread.currentThread().getId());
        }
    }
}
