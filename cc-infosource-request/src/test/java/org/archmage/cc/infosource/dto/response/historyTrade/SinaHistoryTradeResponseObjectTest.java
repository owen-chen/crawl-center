/*
 * Create Author  : chen.chen.9
 * Create Date    : May 25, 2015
 * File Name      : SinaHistoryTradeResponseObjectTest.java
 */

package org.archmage.cc.infosource.dto.response.historyTrade;

import java.util.ArrayList;
import java.util.Arrays;

import org.archmage.cc.infosource.AbstractTestng;
import org.archmage.cc.infosource.bean.InfosourceErrorException;
import org.archmage.cc.infosource.parse.historyTrade.SinaHistoryTradeParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link SinaHistoryTradeResponseObject} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 25, 2015
 */
public class SinaHistoryTradeResponseObjectTest extends AbstractTestng {
    @Test(dataProvider = "dataProvider")
    public void test(String filename, SinaHistoryTradeResponseObject expected) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException,
            InfosourceErrorException {
        String result = readResponse("classpath:response/historyTrade/" + filename);

        SinaHistoryTradeResponseObject sinaStockResponseObject = (SinaHistoryTradeResponseObject) new SinaHistoryTradeParser().parse(SinaHistoryTradeResponseObject.class, result,
                                                                                                                                     "html");

        Assert.assertEquals(sinaStockResponseObject.toString(), expected.toString());
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return new Object[][] {
                               {
                                "sina_history_trade_1.txt",
                                new SinaHistoryTradeResponseObject(Arrays.asList(new Result("14", "59", "56", "77.88", "-0.12", "54", "420552", "卖盘"), new Result("14", "12", "51",
                                                                                                                                                                  "78.77", "0.05",
                                                                                                                                                                  "7", "55139",
                                                                                                                                                                  "卖盘"),
                                                                                 new Result("09", "31", "58", "72.82", "0.01", "6", "43691", "中性盘"), new Result("09", "31", "43",
                                                                                                                                                                "72.81", "--", "7",
                                                                                                                                                                "50967", "卖盘")),
                                                                   false) }, { "sina_history_trade_2.txt", new SinaHistoryTradeResponseObject(new ArrayList<Result>(), true) } };
    }
}
