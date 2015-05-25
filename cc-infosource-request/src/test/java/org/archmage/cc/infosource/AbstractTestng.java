/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-14
 * File Name      : AbstractTestng.java
 */

package org.archmage.cc.infosource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.archmage.cc.common.resourceLoader.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Log4jConfigurer;

/**
 * abstract test class
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-14
 */
@ContextConfiguration(locations = { "classpath:spring/local/applicationContext-*.xml", "classpath*:spring/common/applicationContext-*.xml" })
public class AbstractTestng extends AbstractTestNGSpringContextTests {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private ResourceLoader resourceLoader;

    /**
     * construct
     */
    public AbstractTestng() {
        try {
            Log4jConfigurer.initLogging("classpath:log/log4j.xml", 60000);
        }
        catch (FileNotFoundException e) {
            LOGGER.info(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * read data from json file
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param location
     *            data file location
     * @return content
     */
    protected String readResponse(String location) {
        org.springframework.core.io.Resource resource = resourceLoader.getResource(location);

        StringWriter stringWriter = new StringWriter();
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            IOUtils.copy(inputStream, stringWriter);
        }
        catch (IOException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }

        return stringWriter.toString();
    }
}
