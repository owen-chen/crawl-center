/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-13
 * File Name      : ResourceLoaderTest.java
 */

package org.archmage.cc.common.resourceLoader;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.archmage.cc.common.AbstractTestng;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * {@link ResourceLoader} Test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-13
 */
public class ResourceLoaderTest extends AbstractTestng {
    @Resource
    private ResourceLoader resourceLoader;

    @Test
    public void getResourcesTest() throws IOException {
        org.springframework.core.io.Resource[] resourceArray = resourceLoader.getResources("classpath:log/log4j.xml");

        Assert.assertTrue(ArrayUtils.isNotEmpty(resourceArray) && resourceArray.length == 1);
    }

    @Test
    public void getResourceTest() throws IOException {
        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:log/log4j.xml");

        Assert.assertNotNull(resource);
    }
}
