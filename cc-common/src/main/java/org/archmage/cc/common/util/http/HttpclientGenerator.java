/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-19
 * File Name      : HttpclientGenerator.java
 */

package org.archmage.cc.common.util.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/**
 * {@link Httpclient} generator
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-19
 */
public class HttpclientGenerator {
    /** default time out is 10 seconds */
    private static final int DEFAULT_TIME_OUT = 10000;

    /**
     * generator
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @return {@link CloseableHttpClient}
     */
    public static CloseableHttpClient generate() {
        return generate(DEFAULT_TIME_OUT);
    }

    /**
     * generator
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param timeout
     *            time out
     * @return {@link CloseableHttpClient}
     */
    public static CloseableHttpClient generate(int timeout) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).setConnectTimeout(timeout).build();
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setDefaultRequestConfig(requestConfig);
        return httpClientBuilder.build();
    }
}
