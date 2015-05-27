/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-23
 * File Name      : AbstractCrawlDriver.java
 */

package org.archmage.cc.crawl.driver;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.archmage.cc.crawl.bean.EntireObject;
import org.archmage.cc.crawl.bean.ErrorCode;
import org.archmage.cc.crawl.bean.log.CrawlInnerLog;
import org.archmage.cc.crawl.bean.log.CrawlTaskLogBean;
import org.archmage.cc.crawl.container.Container;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.response.ResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;
import org.slf4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * abstract crawl driver
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-23
 */
public abstract class AbstractCrawlDriver<T extends ResponseObject> implements CrawlDriver<T> {
    /** the count of processing or parsing */
    private int processCount = 0;

    /** use for syncronization */
    private final byte[] bytes = new byte[0];

    @Override
    public boolean capture() {
        // 1. obtain a todo request
        RequestObject requestObject = getTodoRequestContainer().shift();

        if (requestObject == null) {
            return false;
        }

        try {
            synchronized (bytes) {
                processCount++;
            }

            CrawlInnerLog crawlInnerLog = new CrawlInnerLog();
            T t = null;
            try {
                crawlInnerLog.setStartTime(new Date(System.currentTimeMillis()));
                crawlInnerLog.setRequestObject(requestObject);

                // if todo request has been visited, do not crawl it again
                if (getVisitedRequestContainer().contains(requestObject.toString())) {
                    crawlInnerLog.setErrorCode(ErrorCode.VISITED.getValue());

                    return false;
                }

                // 2. capture by this todo request
                t = doCapture(requestObject);
                
                // 3. insert t to MongoDB
                if (t != null && t.isSuccess()) {
                    getMongoTemplate().insert(new EntireObject(requestObject, t), t.getClass().getSimpleName());
                }

                // 4. mark this todoUrl visited
                getVisitedRequestContainer().unshift(requestObject.toString());

                return t != null && t.isSuccess();
            }
            catch (RuntimeException e) {
                crawlInnerLog.setErrorMsg(ExceptionUtils.getStackTrace(e));
            }
            catch (NoSuchMethodException e) {
                crawlInnerLog.setErrorMsg(ExceptionUtils.getStackTrace(e));
            }
            catch (InstantiationException e) {
                crawlInnerLog.setErrorMsg(ExceptionUtils.getStackTrace(e));
            }
            catch (IllegalAccessException e) {
                crawlInnerLog.setErrorMsg(ExceptionUtils.getStackTrace(e));
            }
            catch (InvocationTargetException e) {
                crawlInnerLog.setErrorMsg(ExceptionUtils.getStackTrace(e));
            }
            finally {
                // crawlInnerLog.setResponseObject(t);
                crawlInnerLog.setEndTime(new Date(System.currentTimeMillis()));
                crawlInnerLog.setElapsedTime(crawlInnerLog.getEndTime().getTime() - crawlInnerLog.getStartTime().getTime());

                synchronized (bytes) {
                    long threadId = Thread.currentThread().getId();
                    CrawlTaskLogBean crawlLogBean = (CrawlTaskLogBean) getLogContainer().retrieveLogBean(threadId);
                    if (crawlLogBean != null) {
                        crawlLogBean.getInner().add(crawlInnerLog);
                    }
                    else {
                        getLOGGER().error("CrawlLogBean is unexpectedlly null in thread: {}.", threadId);
                    }
                }
            }

            return false;
        }
        finally {
            synchronized (bytes) {
                processCount--;
            }
        }
    }

    @Override
    public boolean isCrawlFinish() {
        getLOGGER().info("{} todo: {} precessCount: {}", new Object[] { getClass().getSimpleName(), getTodoRequestContainer().size(), getProcessCount() });

        return getTodoRequestContainer().empty() && getProcessCount() == 0;
    }

    @Override
    public abstract int obtainTodoRequest() throws CrawlErrorException;

    /**
     * capture
     * <p>
     * 
     * @author chen.chen.9, 2013-8-23
     * @param requestObject
     *            {@link RequestObject}
     * @return javabean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    protected T doCapture(RequestObject requestObject) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException,
            InvocationTargetException {
        return (T) getInfosourceRequestFactory().request(requestObject, true);
    }

    /**
     * getter method
     * 
     * @see AbstractCrawlDriver#logContainer
     * @return the logContainer
     */
    protected abstract LogContainer getLogContainer();

    /**
     * getter method
     * 
     * @see AbstractCrawlDriver#infosourceRequestFactory
     * @return the infosourceRequestFactory
     */
    protected abstract InfosourceRequestFactory<T> getInfosourceRequestFactory();

    /**
     * getter method
     * 
     * @see AbstractCrawlDriver#todoRequestContainer
     * @return the todoRequestContainer
     */
    protected abstract Container<RequestObject> getTodoRequestContainer();

    /**
     * getter method
     * 
     * @see AbstractCrawlDriver#visitedRequestContainer
     * @return the visitedRequestContainer
     */
    protected abstract Container<String> getVisitedRequestContainer();

    /**
     * getter method
     * 
     * @see AbstractCrawlDriver#LOGGER
     * @return the lOGGER
     */
    protected abstract Logger getLOGGER();

    /**
     * getter method
     * 
     * @see AbstractCrawlDriver#mongoTemplate
     * @return the mongoTemplate
     */
    protected abstract MongoTemplate getMongoTemplate();

    /**
     * getter method
     * 
     * @see AbstractCrawlDriver#processCount
     * @return the processCount
     */
    protected int getProcessCount() {
        return processCount;
    }
}
