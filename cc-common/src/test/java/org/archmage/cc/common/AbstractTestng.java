/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-14
 * File Name      : AbstractTestng.java
 */

package org.archmage.cc.common;

import java.io.FileNotFoundException;

import org.apache.commons.lang.exception.ExceptionUtils;
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
@ContextConfiguration(locations = { "classpath:spring/common/applicationContext-*.xml" })
public class AbstractTestng extends AbstractTestNGSpringContextTests {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * constructor
     */
    public AbstractTestng() {
        try {
            Log4jConfigurer.initLogging("classpath:log/log4j.xml", 60000);
        }
        catch (FileNotFoundException e) {
            LOGGER.info(ExceptionUtils.getStackTrace(e));
        }
    }
}
