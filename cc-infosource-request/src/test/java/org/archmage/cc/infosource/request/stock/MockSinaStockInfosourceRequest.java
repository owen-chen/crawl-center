/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : MockSinaStockInfosourceRequest.java
 */

package org.archmage.cc.infosource.request.stock;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.archmage.cc.common.resourceLoader.ResourceLoader;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.metadata.MetadataCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.testng.Assert;

/**
 * mock {@link SinaStockInfosourceRequest}
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 21, 2015
 */
public class MockSinaStockInfosourceRequest extends SinaStockInfosourceRequest {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** expected url for test */
    private String expectedUrl;

    /** {@link ResourceLoader} */
    private ResourceLoader resourceLoader;

    /**
     * constructor
     * 
     * @param resourceLoader
     * @param logContainer
     * @param metadataCacheManager
     */
    public MockSinaStockInfosourceRequest(ResourceLoader resourceLoader, LogContainer logContainer, MetadataCacheManager metadataCacheManager) {
        super(logContainer, metadataCacheManager);

        this.resourceLoader = resourceLoader;
    }

    @Override
    protected String obtainClassName() {
        return super.obtainClassName().replace("Mock", "");
    }

    @Override
    protected String doRequest(String url, int timeout, boolean repeatable) {
        Assert.assertEquals(url, expectedUrl);

        Resource resource = resourceLoader.getResource("classpath:response/stock/sina_stock.js");

        try {
            InputStream inputStream = resource.getInputStream();
            StringWriter stringWriter = new StringWriter();
            IOUtils.copy(inputStream, stringWriter, UTF8);

            return stringWriter.toString();
        }
        catch (IOException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }

        return null;
    }

    /**
     * setter method
     * 
     * @see MockSinaStockInfosourceRequest#expectedUrl
     * @param expectedUrl
     *            the expectedUrl to set
     */
    public void setExpectedUrl(String expectedUrl) {
        this.expectedUrl = expectedUrl;
    }
}
