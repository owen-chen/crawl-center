/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-21
 * File Name      : InfosourceConfigReaderTest.java
 */

package org.archmage.cc.infosource.reader;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Resource;

import org.archmage.cc.infosource.AbstractTestng;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.archmage.cc.infosource.parse.BaseParser;
import org.archmage.cc.infosource.reader.bean.Infosource;
import org.archmage.cc.infosource.reader.bean.SubInfosource;
import org.archmage.cc.infosource.reader.mock.MockInfosourceConfigReader;
import org.archmage.cc.infosource.request.stock.SinaStockInfosourceRequest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link InfosourceConfigReader} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-21
 */
public class InfosourceConfigReaderTest extends AbstractTestng {
    @Resource
    private MockInfosourceConfigReader mockIsconfigReader;

    @Test(dataProvider = "retrieveInfosourceTest")
    public void retrieveInfosourceTest(String isCode, Infosource expectedInfosource, SubInfosource[] subInfosourceArray) {
        Infosource infosource = mockIsconfigReader.retrieveInfosource(isCode);

        expectedInfosource.setSubInfosourceList(Arrays.asList(subInfosourceArray));

        for (int i = 0; i < infosource.getSubInfosourceList().size(); i++) {
            SubInfosource subInfosource = infosource.getSubInfosourceList().get(i);

            Assert.assertTrue(subInfosource.getParser().getClass() == subInfosourceArray[i].getParser().getClass());
            subInfosourceArray[i].setParser(subInfosource.getParser());
        }

        Assert.assertEquals(infosource.toString(), expectedInfosource.toString());
    }

    @DataProvider(name = "retrieveInfosourceTest")
    public Object[][] dataProvider() {
        return new Object[][] { {
                                 "10001",
                                 new Infosource("10001", "test sina stock info", SinaStockInfosourceRequest.class, SinaStockResponseObject.class, new ArrayList<SubInfosource>()),
                                 new SubInfosource[] { new SubInfosource(
                                                                         "test.stock.sina",
                                                                         "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=1&num=10000&sort=symbol&asc=1&node=hs_a",
                                                                         SinaStockResponseObject.class, "json", new BaseParser<Object>(), 30000, "GB2312") } } };
    }
}
