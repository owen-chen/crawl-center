/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-26
 * File Name      : ConcurrentTaskExecutor.java
 */

package org.archmage.cc.common.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * concurrent thread pool
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-26
 */
public class ConcurrentTaskExecutor {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** ThreadPool */
    private ExecutorService threadPool;

    /**
     * constructor
     * 
     * @param numThreads
     *            size of threads
     * @param maxQueueSize
     *            the maximum queue size
     */
    public ConcurrentTaskExecutor(int numThreads, int maxQueueSize) {
        threadPool = new ThreadPoolExecutor(numThreads, numThreads, Long.MAX_VALUE, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<Runnable>(maxQueueSize, false),
                                            new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    /**
     * execute task
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     * @param task
     *            {@link Runnable}
     */
    public void execute(Runnable task) {
        threadPool.execute(task);
    }

    /**
     * shutdown thread pool
     * <p>
     * 
     * @author chen.chen.9, 2013-8-26
     */
    public void stop() {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        }
        catch (InterruptedException e) {
            LOGGER.error("InterruptedException occurred on ConcurrentTaskExecutor shutdown! Current Thread will be interrupted!", e);

            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
