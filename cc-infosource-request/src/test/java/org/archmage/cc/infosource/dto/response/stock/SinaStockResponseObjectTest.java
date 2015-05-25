/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : SinaStockResponseObjectTest.java
 */

package org.archmage.cc.infosource.dto.response.stock;

import java.util.Arrays;

import org.archmage.cc.infosource.AbstractTestng;
import org.archmage.cc.infosource.bean.InfosourceErrorException;
import org.archmage.cc.infosource.parse.BaseParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link SinaStockResponseObject} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 21, 2015
 */
public class SinaStockResponseObjectTest extends AbstractTestng {
    @Test(dataProvider = "dataProvider")
    public void test(String filename, SinaStockResponseObject expected) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException,
            InfosourceErrorException {
        String result = readResponse("classpath:response/stock/" + filename);

        SinaStockResponseObject sinaStockResponseObject = (SinaStockResponseObject) new BaseParser<SinaStockResponseObject>().parse(SinaStockResponseObject.class, result, "json");

        Assert.assertEquals(sinaStockResponseObject.toString(), expected.toString());
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return new Object[][] { { "sina_stock.js", new SinaStockResponseObject(Arrays.asList(new Result("sh603998", "603998", "方盛制药"), new Result("sh603997", "603997", "继峰股份"))) } };
    }
}
