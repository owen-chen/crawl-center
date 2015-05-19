/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-26
 * File Name      : ConcurrentTaskExecutor.java
 */

package org.archmage.cc.common.thread;

import org.archmage.cc.common.AbstractTestng;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * {@link ConcurrentTaskExecutor} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-26
 */
public class ConcurrentTaskExecutorTest extends AbstractTestng {
    /** number for test */
    private Integer testNumber = 0;

    @Test
    public void test() throws InterruptedException {
        ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor(10, 100);

        int cycleCount = 5;
        for (int i = 0; i < cycleCount; i++) {
            concurrentTaskExecutor.execute(new TestThread());
        }

        Thread.sleep(100L);

        Assert.assertTrue(testNumber == cycleCount);

        concurrentTaskExecutor.stop();
    }

    /**
     * thread for test
     * <p>
     * 
     * @author : chen.chen.9
     * @date : 2013-8-26
     */
    class TestThread implements Runnable {
        @Override
        public void run() {
            synchronized (testNumber) {
                testNumber++;
            }
        }
    }
}
