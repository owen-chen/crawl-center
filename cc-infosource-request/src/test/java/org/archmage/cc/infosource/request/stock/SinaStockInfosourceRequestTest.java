/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : SinaStockInfosourceRequestTest.java
 */

package org.archmage.cc.infosource.request.stock;

import java.util.Arrays;

import javax.annotation.Resource;

import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.AbstractTestng;
import org.archmage.cc.infosource.dto.request.stock.SinaStockRequestObject;
import org.archmage.cc.infosource.dto.response.stock.Result;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.archmage.cc.infosource.reader.InfosourceConfigReader;
import org.archmage.cc.infosource.reader.bean.Infosource;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * {@link SinaStockInfosourceRequest} test
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 21, 2015
 */
public class SinaStockInfosourceRequestTest extends AbstractTestng {
    @Resource
    private InfosourceConfigReader isconfigReader;

    @Resource
    private LogContainer logContainer;

    @Test(dataProvider = "requestTest")
    public void requestTest(SinaStockRequestObject sinaStockRequestObject, String expectedUrl, SinaStockResponseObject expected) throws InterruptedException {
        SinaStockInfosourceRequest sinaStockInfosourceRequest = new SinaStockInfosourceRequest(logContainer, null);

        Infosource infosource = isconfigReader.retrieveInfosource(sinaStockRequestObject.getCode());
        Assert.assertEquals(sinaStockInfosourceRequest.generateUrl(infosource.getSubInfosourceList().get(0).getUrl(), sinaStockRequestObject), expectedUrl);

        SinaStockResponseObject sinaStockResponseObject = sinaStockInfosourceRequest.request(infosource, sinaStockRequestObject, true);

        Assert.assertTrue(sinaStockResponseObject != null);
        Assert.assertTrue(sinaStockResponseObject.isSuccess());

        Assert.assertEquals(sinaStockResponseObject.getResultList().get(0).toString(), expected.getResultList().get(0).toString());
        Assert.assertEquals(sinaStockResponseObject.getResultList().get(1).toString(), expected.getResultList().get(1).toString());
    }

    @DataProvider(name = "requestTest")
    public Object[][] dataProvider() {
        return new Object[][] { { new SinaStockRequestObject("10001", 2),
                                 "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=2&num=1000&sort=symbol&asc=1&node=hs_a",
                                 new SinaStockResponseObject(Arrays.asList(new Result("sh603869", "603869", "北部湾旅"), new Result("sh603883", "603883", "老百姓"))) } };
    }
}
