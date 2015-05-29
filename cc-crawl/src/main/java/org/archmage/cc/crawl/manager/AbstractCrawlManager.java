/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : AbstractCrawlManager.java
 */

package org.archmage.cc.crawl.manager;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.archmage.cc.common.thread.ConcurrentTaskExecutor;
import org.archmage.cc.common.util.parse.TextUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.bean.CrawlFinished;
import org.archmage.cc.crawl.bean.CrawlTask;
import org.archmage.cc.crawl.bean.ErrorCode;
import org.archmage.cc.crawl.bean.log.CrawlJobLogBean;
import org.archmage.cc.crawl.bean.log.CrawlTaskLogBean;
import org.archmage.cc.crawl.collector.manager.CollectorManager;
import org.archmage.cc.crawl.driver.CrawlDriver;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.crawl.model.CrawlStatus;
import org.archmage.cc.crawl.reader.CrawlConfigReader;
import org.archmage.cc.framework.log.InnerLog;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.bean.SubInfosourceRequestInnerLog;
import org.archmage.cc.infosource.dto.response.ResponseObject;
import org.slf4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;

/**
 * abstract crawl manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public abstract class AbstractCrawlManager implements CrawlManager {
    @Override
    public void crawl() {
        CrawlJobLogBean crawlJobLogBean = (CrawlJobLogBean) getLogContainer().initializeLogBean(Thread.currentThread().getId(), new CrawlJobLogBean());
        crawlJobLogBean.setMachine(getSysconfig().getString("Log.Machine"));
        crawlJobLogBean.setStartTime(new Date(System.currentTimeMillis()));
        crawlJobLogBean.setCreatedTime(new Date(System.currentTimeMillis()));

        try {
            // 1.retrieveCrawlConfig
            String className = getClassName();
            crawlJobLogBean.setCrawlManagerClassName(className);
            CrawlStatus crawlStatus = getCrawlConfigReader().retrieveCertainCrawlConfig(className);
            crawlJobLogBean.setCrawlStatus(crawlStatus);
            if (crawlStatus == null) {
                throw new CrawlErrorException(ErrorCode.NO_CRAWL_STATUS);
            }
            else if (!crawlStatus.getRunnable()) {
                throw new CrawlErrorException(ErrorCode.UNRUNNABLE_CRAWL);
            }

            // 2.save started status of this job to db
            crawlStatus.setStatus(CrawlStatus.Status.STARTED);
            saveCrawlStatus(crawlStatus);

            // 3.obtain todo requests
            CrawlDriver<?> crawlDriver = getCrawlDriver();
            long startTime = System.currentTimeMillis();
            int todoSize = crawlDriver.obtainTodoRequest();
            crawlJobLogBean.setObtainTodoRequestElapsedTime(System.currentTimeMillis() - startTime);
            crawlJobLogBean.setTodoSize(todoSize);

            String crawlCode = crawlStatus.getCode();
            crawlStatus.setStatus(CrawlStatus.Status.CRAWLING);
            saveCrawlStatus(crawlStatus);

            // 4.crawl by todo request concurrently
            Integer threadPoolSize = crawlStatus.getThreadPoolSize();
            CrawlFinished needToFinish = new CrawlFinished(false);

            // drop unused collection
            long dropCollectionElapsedTime = System.currentTimeMillis();
            getMongoTemplate().dropCollection(getResponseClassSimpleName());
            crawlJobLogBean.setDropCollectionElapsedTime(System.currentTimeMillis() - dropCollectionElapsedTime);

            for (int i = 0; i < threadPoolSize; i++) {
                getConcurrentTaskExecutor().execute(new CrawlTask(crawlDriver, needToFinish, getLogContainer(), crawlJobLogBean));
            }

            getLOGGER().info("{} : {} threads have been proceeding to capture {}, todo: {}", new Object[] { getClassName(), threadPoolSize, crawlCode, todoSize });

            // 5.estimate whether or not finished
            startTime = System.currentTimeMillis();
            while (true) {
                long crawlStartTime = System.currentTimeMillis();
                try {
                    Thread.sleep(getSysconfig().getInt("Crawl.SleepingTime"));
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    getLOGGER().error(ExceptionUtils.getStackTrace(e));
                }

                needToFinish.setFinished(crawlDriver.isCrawlFinish());
                if (needToFinish.isFinished() || isTimeout(startTime, crawlStatus.getTimeout())) {
                    needToFinish.setFinished(true);
                    crawlJobLogBean.setCrawlElapsedTime(System.currentTimeMillis() - crawlStartTime);

                    crawlStatus.setStatus(CrawlStatus.Status.DATA_COLLECTOR);
                    saveCrawlStatus(crawlStatus);

                    getCollectorManager().collect();

                    crawlStatus.setStatus(CrawlStatus.Status.FINISHED);
                    saveCrawlStatus(crawlStatus);

                    break;
                }
            }
        }
        catch (CrawlErrorException e) {
            crawlJobLogBean.setErrorCode(e.getErrorCode().getValue());

            CrawlStatus crawlStatus = crawlJobLogBean.getCrawlStatus();
            crawlStatus.setStatus(CrawlStatus.Status.CRAWLING_FAILURE);
            saveCrawlStatus(crawlStatus);
        }
        finally {
            crawlJobLogBean.setEndTime(new Date(System.currentTimeMillis()));
            crawlJobLogBean.setElapsedTime(crawlJobLogBean.getEndTime().getTime() - crawlJobLogBean.getStartTime().getTime());

            try {
                // 去除parseResult，防止爬虫日志输出过多
                List<CrawlTaskLogBean> crawlTaskLogList = crawlJobLogBean.getCrawlTaskLogList();
                if (CollectionUtils.isNotEmpty(crawlTaskLogList)) {
                    for (CrawlTaskLogBean crawlTaskLogBean : crawlTaskLogList) {
                        if (crawlTaskLogBean == null) {
                            continue;
                        }

                        List<InnerLog> innerList = crawlTaskLogBean.getInner();
                        if (CollectionUtils.isEmpty(innerList)) {
                            continue;
                        }

                        for (InnerLog innerLog : innerList) {
                            if (innerLog instanceof SubInfosourceRequestInnerLog) {
                                SubInfosourceRequestInnerLog subInfosourceRequestInnerLog = (SubInfosourceRequestInnerLog) innerLog;
                                subInfosourceRequestInnerLog.setParsedResult(null);
                            }
                        }
                    }
                }
            }
            catch (RuntimeException e) {
                getLOGGER().error(ExceptionUtils.getStackTrace(e));
            }

            if (getSysconfig().getBoolean("Log.ShowResponseObject.Enabled")) {
                getLOGGER().info(TextUtils.removeLineBreak(crawlJobLogBean.toString()));
            }

            getLogContainer().removeLogBean(Thread.currentThread().getId());
        }
    }

    /**
     * save crawl status to db
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     * @param crawlStatus
     *            {@link CrawlStatus}
     */
    protected void saveCrawlStatus(CrawlStatus crawlStatus) {
        String code = crawlStatus.getCode();
        Query query = new BasicQuery(new BasicDBObject("code", code));
        CrawlStatus crawlStatusInDb = getMongoTemplate().findOne(query, CrawlStatus.class);

        // if exists, update it
        if (crawlStatusInDb != null) {
            crawlStatus.set_id(crawlStatusInDb.get_id());

            getMongoTemplate().save(crawlStatus);
        }
        // if not exists, insert it
        else {
            getMongoTemplate().insert(crawlStatus);
        }

        // XXX {@link WriteResult}#getLastError() to return isSuccess
        getLOGGER().info("The status of the crawl {} job has gone step to {}", code, crawlStatus.getStatus());
    }

    /**
     * is timeout
     * <p>
     * 
     * @author shun.lv, 2013-12-20
     * @param startTime
     *            start time
     * @param timeout
     *            time out
     * @return is timeout
     */
    private boolean isTimeout(long startTime, long timeout) {
        return (System.currentTimeMillis() - startTime) > timeout;
    }

    /**
     * get class name for test
     * <p>
     * 
     * @author chen.chen.9, 2013-8-20
     * @return class name
     */
    protected String getClassName() {
        return getClass().getName();
    }

    /**
     * getter method
     * 
     * @see AbstractCrawlManager#concurrentTaskExecutor
     * @return the concurrentTaskExecutor
     */
    protected abstract ConcurrentTaskExecutor getConcurrentTaskExecutor();

    /**
     * getter method
     * 
     * @see AbstractCrawlManager#logContainer
     * @return the logContainer
     */
    protected abstract LogContainer getLogContainer();

    /**
     * 返回responseClass simpleName
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     * @return responseClass simpleName
     */
    protected abstract String getResponseClassSimpleName();

    /**
     * getter method
     * 
     * @see AbstractCrawlManager#collectorManager
     * @return the collectorManager
     */
    protected abstract CollectorManager getCollectorManager();

    /**
     * 返回对应的爬虫驱动
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     * @return {@link CrawlDriver}
     */
    protected abstract CrawlDriver<? extends ResponseObject> getCrawlDriver();

    /**
     * getter method
     * 
     * @see AbstractCrawlManager#sysconfig
     * @return the sysconfig
     */
    protected abstract XmlConfiguration getSysconfig();

    /**
     * getter method
     * 
     * @see AbstractCrawlManager#LOGGER
     * @return the lOGGER
     */
    protected abstract Logger getLOGGER();

    /**
     * getter method
     * 
     * @see AbstractCrawlManager#crawlConfigReader
     * @return the crawlConfigReader
     */
    protected abstract CrawlConfigReader getCrawlConfigReader();

    /**
     * getter method
     * 
     * @see AbstractCrawlManager#mongoTemplate
     * @return the mongoTemplate
     */
    protected abstract MongoTemplate getMongoTemplate();
}
