/*
 * Create Author  : chen.chen.9
 * Create Date    : May 25, 2015
 * File Name      : SinaHistoryTradeInfosourceRequestTest.java
 */

package org.archmage.cc.infosource.request.historyTrade;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.AbstractTestng;
import org.archmage.cc.infosource.dto.request.historyTrade.SinaHistoryTradeRequestObject;
import org.archmage.cc.infosource.dto.response.historyTrade.Result;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.infosource.reader.InfosourceConfigReader;
import org.archmage.cc.infosource.reader.bean.Infosource;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link SinaHistoryTradeInfosourceRequest} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 25, 2015
 */
public class SinaHistoryTradeInfosourceRequestTest extends AbstractTestng {
    @Resource
    private InfosourceConfigReader isconfigReader;

    @Resource
    private LogContainer logContainer;

    @Test(dataProvider = "requestTest")
    public void requestTest(SinaHistoryTradeRequestObject sinaHistoryTradeRequestObject, String expectedUrl, SinaHistoryTradeResponseObject expected) throws InterruptedException {
        SinaHistoryTradeInfosourceRequest sinaHistoryTradeInfosourceRequest = new SinaHistoryTradeInfosourceRequest(logContainer, null);

        Infosource infosource = isconfigReader.retrieveInfosource(sinaHistoryTradeRequestObject.getCode());
        Assert.assertEquals(sinaHistoryTradeInfosourceRequest.generateUrl(infosource.getSubInfosourceList().get(0).getUrl(), sinaHistoryTradeRequestObject), expectedUrl);

        SinaHistoryTradeResponseObject sinaHistoryTradeResponseObject = sinaHistoryTradeInfosourceRequest.request(infosource, sinaHistoryTradeRequestObject, true);

        Assert.assertTrue(sinaHistoryTradeResponseObject != null);
        Assert.assertTrue(sinaHistoryTradeResponseObject.isSuccess());

        if (expected.isClosed()) {
            expected.setSuccess(true);
            Assert.assertEquals(sinaHistoryTradeResponseObject.toString(), expected.toString());
        }
        else {
            for (Result result : expected.getResultList()) {
                boolean isAimed = false;
                for (Result actual : sinaHistoryTradeResponseObject.getResultList()) {
                    if (StringUtils.equals(actual.toString(), result.toString())) {
                        isAimed = true;
                        break;
                    }
                }
                Assert.assertTrue(isAimed);
            }
        }
    }

    @DataProvider(name = "requestTest")
    public Object[][] dataProvider() {
        return new Object[][] {
                               {
                                new SinaHistoryTradeRequestObject("10002", "sh603898", 2015, 4, 30),
                                "http://market.finance.sina.com.cn/downxls.php?date=2015-04-30&symbol=sh603898",
                                new SinaHistoryTradeResponseObject(Arrays.asList(new Result("14", "59", "56", "77.88", "-0.12", "54", "420552", "卖盘"), new Result("14", "12", "51",
                                                                                                                                                                  "78.77", "0.05",
                                                                                                                                                                  "7", "55139",
                                                                                                                                                                  "卖盘"),
                                                                                 new Result("09", "31", "58", "72.82", "0.01", "6", "43691", "中性盘"), new Result("09", "31", "43",
                                                                                                                                                                "72.81", "--", "7",
                                                                                                                                                                "50967", "卖盘")),
                                                                   false) },
                               { new SinaHistoryTradeRequestObject("10002", "sh603898", 2014, 1, 1),
                                "http://market.finance.sina.com.cn/downxls.php?date=2014-01-01&symbol=sh603898", new SinaHistoryTradeResponseObject(new ArrayList<Result>(), true) } };
    }
}
