/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-19
 * File Name      : HttpclientGenerator.java
 */

package org.archmage.cc.common.util.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * {@link Httpclient} generator
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-19
 */
public class HttpclientGenerator {
    /** default charset */
    private static final String DEFAULT_CHARSET = "GB2312";

    /**
     * generator
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @return {@link Httpclient}
     */
    public static HttpClient generate() {
        HttpClient httpClient = new HttpClient();

        HttpClientParams params = httpClient.getParams();
        params.setContentCharset(DEFAULT_CHARSET);
        httpClient.setParams(params);

        return httpClient;
    }
}
