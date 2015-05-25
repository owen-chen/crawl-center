/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-19
 * File Name      : MethodGenerator.java
 */

package org.archmage.cc.common.util.http;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHeader;

/**
 * {@link HttpGet} generator
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-19
 */
public class GetMethodGenerator {
    /**
     * generator
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param url
     *            url
     * @return {@link HttpGet}
     */
    public static HttpGet generate(String url) {
        HttpGet getMethod = new HttpGet(url);

        getMethod.addHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
        getMethod.addHeader(new BasicHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7"));
        getMethod.addHeader(new BasicHeader("Accept-Language", "zh-cn,zh;q=0.5"));
        getMethod.addHeader(new BasicHeader("Connection", "keep-alive"));
        getMethod.addHeader(new BasicHeader("DNT", "1"));

        return getMethod;
    }
}
