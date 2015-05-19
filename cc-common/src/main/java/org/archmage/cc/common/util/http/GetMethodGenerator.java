/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-19
 * File Name      : MethodGenerator.java
 */

package org.archmage.cc.common.util.http;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * {@link GetMethod} generator
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-19
 */
public class GetMethodGenerator {
    /** time out: second */
    private static final int TIMEOUT = 5000;

    /**
     * generator
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param url
     *            url
     * @return {@link GetMethod}
     */
    public static GetMethod generate(String url) {
        return generate(url, TIMEOUT);
    }

    /**
     * generator
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param url
     *            url
     * @param timeout
     *            time out, timeunit is ms
     * @return {@link GetMethod}
     */
    public static GetMethod generate(String url, int timeout) {
        GetMethod getMethod = new GetMethod(url);

        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        getMethod.getParams().setParameter("http.connection.timeout", timeout);

        getMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        getMethod.setRequestHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        getMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.5");
        getMethod.setRequestHeader("Connection", "keep-alive");
        getMethod.setRequestHeader("DNT", "1");

        return getMethod;
    }
}
