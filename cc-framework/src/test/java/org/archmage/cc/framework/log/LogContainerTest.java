/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-17
 * File Name      : LogContainerTest.java
 */

package org.archmage.cc.framework.log;

import javax.annotation.Resource;

import org.archmage.cc.framework.AbstractTestng;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * {@link LogContainer} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-17
 */
public class LogContainerTest extends AbstractTestng {
    @Resource
    private LogContainer logContainer;

    @Test
    public void test() {
        Assert.assertFalse(logContainer.removeLogBean(Thread.currentThread().getId()));

        LogBean expected = logContainer.initializeLogBean(Thread.currentThread().getId(), new LogBean());
        LogBean logBean = logContainer.retrieveLogBean(Thread.currentThread().getId());

        Assert.assertEquals(logBean, expected);

        Assert.assertTrue(logContainer.removeLogBean(Thread.currentThread().getId()));
        Assert.assertFalse(logContainer.removeLogBean(Thread.currentThread().getId()));
    }
}
