/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-19
 * File Name      : HttpclientGeneratorTest.java
 */

package org.archmage.cc.common.util.http;

import org.archmage.cc.common.AbstractTestng;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * {@link HttpclientGenerator} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-19
 */
public class HttpclientGeneratorTest extends AbstractTestng {
    @Test
    public void generateTest() {
        Assert.assertNotNull(HttpclientGenerator.generate());
    }
}
