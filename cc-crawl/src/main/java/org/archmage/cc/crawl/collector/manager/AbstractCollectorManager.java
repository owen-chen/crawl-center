/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-26
 * File Name      : AbstractCollectorManager.java
 */

package org.archmage.cc.crawl.collector.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.crawl.bean.BackupHistoryDataBean;
import org.archmage.cc.crawl.bean.EntireObject;
import org.archmage.cc.crawl.bean.ErrorCode;
import org.archmage.cc.crawl.bean.log.CollectorInnerLog;
import org.archmage.cc.crawl.bean.log.CrawlJobLogBean;
import org.archmage.cc.crawl.daosupport.ExtendedDaoSupport;
import org.archmage.cc.crawl.exception.CrawlErrorException;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.response.ResponseObject;
import org.slf4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.CommandResult;

/**
 * abstract data collector manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-26
 */
public abstract class AbstractCollectorManager implements CollectorManager {
    @Override
    public void collect() throws CrawlErrorException {
        long startTime = System.currentTimeMillis();
        CollectorInnerLog collectorInnerLog = new CollectorInnerLog();

        try {
            long countOfData = retrieveCountOfData();
            int pageSize = 100;
            for (int pageNo = 1; pageNo <= Math.ceil((double) countOfData / pageSize); pageNo++) {
                // 1.read data
                long retrieveStartTime = System.currentTimeMillis();
                List<EntireObject> crawledDataList = retrieveData(pageNo, pageSize);
                collectorInnerLog.setRetrieveElapsedTime(collectorInnerLog.getRetrieveElapsedTime() + (System.currentTimeMillis() - retrieveStartTime));
                collectorInnerLog.setRetrievedDataSize(collectorInnerLog.getRetrievedDataSize() + crawledDataList.size());
                if (CollectionUtils.isEmpty(crawledDataList)) {
                    throw new CrawlErrorException(ErrorCode.TEMP_DATA_NOT_EXIST_IN_MONGODB);
                }

                // 2.validate data
                validateTotally(crawledDataList);

                // 3.persist
                long persistStartTime = System.currentTimeMillis();
                int succeedToPersist = 0;
                for (final EntireObject entireObject : crawledDataList) {
                    try {
                        putIntoDB(entireObject);
                    }
                    catch (Exception e) {
                        // XXX failed to insert
                        continue;
                    }

                    succeedToPersist++;
                }
                collectorInnerLog.setPersistElapsedTime(collectorInnerLog.getPersistElapsedTime() + (System.currentTimeMillis() - persistStartTime));
                collectorInnerLog.setSucceedToPersistCount(collectorInnerLog.getSucceedToPersistCount() + succeedToPersist);

                getLOGGER().info("{} is collecting: {} / {}", getResponseClassSimpleName(), Math.min(pageNo * pageSize, countOfData), countOfData);
            }

            if (collectorInnerLog.getSucceedToPersistCount() != countOfData) {
                getLOGGER().warn("succeedToInsertCount {} != allTempDataCount {}", collectorInnerLog.getSucceedToPersistCount(), countOfData);
            }

            // 4. backup history data
            BackupHistoryDataBean backupHistoryDataBean = backupHistoryData();
            collectorInnerLog.setBackupHistoryDataBean(backupHistoryDataBean);
        }
        finally {
            collectorInnerLog.setTotalElapsedTime(System.currentTimeMillis() - startTime);

            long threadId = Thread.currentThread().getId();
            CrawlJobLogBean crawlJobLogBean = (CrawlJobLogBean) getLogContainer().retrieveLogBean(threadId);
            if (crawlJobLogBean != null) {
                crawlJobLogBean.setCollectorInnerLog(collectorInnerLog);
            }
            else {
                getLOGGER().error("CrawlJobLogBean is unexpectedlly null in thread: {}.", threadId);
            }
        }
    }

    /**
     * validate data totally
     * <p>
     * 
     * @author chen.chen.9, 2013-10-24
     * @param crawledDataList
     *            {@link EntireObject} list
     * @throws CrawlErrorException
     */
    private void validateTotally(List<EntireObject> crawledDataList) throws CrawlErrorException {
        int succeedCount = 0;
        for (EntireObject entireObject : crawledDataList) {
            if (entireObject == null) {
                continue;
            }

            ResponseObject responseObject = entireObject.getResponseObject();
            if (responseObject == null || !responseObject.isSuccess()) {
                continue;
            }

            try {
                customValidateTotally(responseObject);
            }
            catch (Exception e) {
                continue;
            }

            succeedCount++;
        }

        double successRate = (double) succeedCount / crawledDataList.size();
        boolean available = successRate >= retrieveThreshold();
        if (!available) {
            throw new CrawlErrorException(ErrorCode.SUCCESS_RATE_NOT_EXCEED_THRESHOLD);
        }
    }

    /**
     * validate
     * <p>
     * 
     * @author chen.chen.9, 2013-10-23
     * @param responseObject
     *            {@link ResponseObject}
     */
    protected void customValidateTotally(ResponseObject responseObject) throws CrawlErrorException {
    }

    /**
     * retrieve threshold
     * <p>
     * 
     * @author chen.chen.9, 2013-10-23
     * @return 总体校验阈值
     */
    protected double retrieveThreshold() {
        return getSysconfig().getDouble("Collector.Threshold");
    }

    /**
     * insert into db
     * <p>
     * 
     * @author chen.chen.9, 2013-9-27
     * @param entireObject
     *            {@link EntireObject}
     */
    protected abstract void putIntoDB(final EntireObject entireObject) throws CrawlErrorException;

    /**
     * retrieve data
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     * @param pageNo
     *            page no
     * @param pageSize
     *            page size
     * @return data list
     */
    protected List<EntireObject> retrieveData(int pageNo, int pageSize) {
        Query query = new Query().skip((pageNo - 1) * pageSize).limit(pageSize);
        return getDaoSupport().getMongoTemplate().find(query, EntireObject.class, getResponseClassSimpleName());
    }

    /**
     * retrieve count of all data
     * <p>
     *
     * @author chen.chen.9, May 29, 2015
     * @return the count of all data
     */
    protected long retrieveCountOfData() {
        return getDaoSupport().getMongoTemplate().count(new Query(), getResponseClassSimpleName());
    }

    /**
     * backup the last collection and put '.bak' to the end of name<br />
     * be aware that the sharded mongodb does not support renameCollection()
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     * @return {@link BackupHistoryDataBean}
     */
    protected BackupHistoryDataBean backupHistoryData() {
        long startTime = System.currentTimeMillis();

        String originalName = getResponseClassSimpleName();
        String backupEndName = getSysconfig().getString("Collector.BackupEndName");
        DateFormat dateFormat = new SimpleDateFormat(".yyyy-MM-dd_HH:mm");

        dropPreviousCollection(originalName, backupEndName, dateFormat, getDaoSupport().getMongoTemplate());

        String toName = StringUtils.join(new String[] { originalName, backupEndName, dateFormat.format(new Date()) });
        String dbCommand = StringUtils.replaceEach("{ renameCollection: \"{0}.{1}\", to: \"{0}.{2}\", dropTarget: true }", new String[] { "{0}", "{1}", "{2}" },
                                                   new String[] { getSysconfig().getString("Database.MongoDB.DatabaseName"), originalName, toName });

        CommandResult commandResult = getAdminMongoTemplate().executeCommand(dbCommand);

        return new BackupHistoryDataBean(originalName, toName, System.currentTimeMillis() - startTime, commandResult.ok(), dbCommand);
    }

    /**
     * remove the collection before today
     * <p>
     * 
     * @author chen.chen.9, 2013-10-29
     * @param originalName
     *            orignal name
     * @param backupEndName
     *            the end name, default is .bak
     * @param dateFormat
     *            {@link DateFormat}
     * @param mongoTemplate
     *            {@link MongoTemplate}
     */
    private void dropPreviousCollection(String originalName, String backupEndName, DateFormat dateFormat, MongoTemplate mongoTemplate) {
        Set<String> set = mongoTemplate.getCollectionNames();
        if (CollectionUtils.isNotEmpty(set)) {
            for (String collectionName : set) {
                if (!collectionName.startsWith(originalName + backupEndName)) {
                    continue;
                }

                String dateString = StringUtils.remove(collectionName, originalName + backupEndName);
                try {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateFormat.parse(dateString));

                    Calendar yesterday = Calendar.getInstance();
                    yesterday.roll(Calendar.DATE, false);

                    if (calendar.before(yesterday)) {
                        mongoTemplate.dropCollection(collectionName);
                    }
                }
                catch (ParseException e) {
                    getLOGGER().warn(ExceptionUtils.getStackTrace(e));
                }
            }
        }
    }

    /**
     * getter method
     * 
     * @see AbstractCollectorManager#infosourceCode
     * @return the infosourceCode
     */
    protected abstract String getInfosourceCode();

    /**
     * getter method
     * 
     * @see AbstractCollectorManager#daoSupport
     * @return the daoSupport
     */
    protected abstract ExtendedDaoSupport getDaoSupport();

    /**
     * getter method
     * 
     * @see AbstractCollectorManager#logContainer
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
     * @see AbstractCollectorManager#LOGGER
     * @return the lOGGER
     */
    protected abstract Logger getLOGGER();

    /**
     * getter method
     * 
     * @see AbstractCollectorManager#adminMongoTemplate
     * @return the adminMongoTemplate
     */
    protected abstract MongoTemplate getAdminMongoTemplate();

    /**
     * getter method
     * 
     * @see AbstractCollectorManager#sysconfig
     * @return the sysconfig
     */
    protected abstract XmlConfiguration getSysconfig();
}
