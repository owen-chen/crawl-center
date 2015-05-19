/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-14
 * File Name      : XmlConfigurationTest.java
 */

package org.archmage.cc.configuration;

import javax.annotation.Resource;

import org.archmage.cc.common.resourceLoader.ResourceLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * {@link XmlConfiguration} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-14
 */
public class XmlConfigurationTest extends AbstractTestng {
    @Resource
    private ResourceLoader resourceLoader;

    @Test
    public void test() {
        XmlConfiguration xmlConfiguration = new XmlConfiguration("classpath:conf/local/sys-configuration.xml;classpath*:conf/common/sys-configuration.xml");
        xmlConfiguration.setResourceLoader(resourceLoader);
        xmlConfiguration.init();

        Assert.assertEquals(xmlConfiguration.getString("Test.A"), "a");
        Assert.assertEquals(xmlConfiguration.getInt("Test.B"), 2);
        Assert.assertEquals(xmlConfiguration.getBoolean("Test.D"), false);
        Assert.assertEquals(xmlConfiguration.getConfig().configurationsAt("Test1").size(), 3);
    }
}
