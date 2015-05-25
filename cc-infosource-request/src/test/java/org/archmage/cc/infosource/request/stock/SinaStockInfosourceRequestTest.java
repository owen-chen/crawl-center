/*
 * Create Author  : chen.chen.9
 * Create Date    : May 21, 2015
 * File Name      : SinaStockInfosourceRequestTest.java
 */

package org.archmage.cc.infosource.request.stock;

import java.util.Arrays;

import javax.annotation.Resource;

import org.archmage.cc.common.resourceLoader.ResourceLoader;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.AbstractTestng;
import org.archmage.cc.infosource.dto.request.stock.SinaStockRequestObject;
import org.archmage.cc.infosource.dto.response.stock.Result;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.archmage.cc.infosource.reader.InfosourceConfigReader;
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

    @Resource
    private ResourceLoader resourceLoader;

    @Test(dataProvider = "requestTest")
    public void requestTest(String isCode, SinaStockRequestObject sinaStockRequestObject, String expectedUrl, SinaStockResponseObject expected) throws InterruptedException {
        sinaStockRequestObject.setCode(isCode);

        MockSinaStockInfosourceRequest sinaStockInfosourceRequest = new MockSinaStockInfosourceRequest(resourceLoader, logContainer, null);
        sinaStockInfosourceRequest.setExpectedUrl(expectedUrl);

        SinaStockResponseObject sinaStockResponseObject = sinaStockInfosourceRequest.request(isconfigReader.retrieveInfosource(isCode), sinaStockRequestObject, true);

        Assert.assertTrue(sinaStockResponseObject != null);
        Assert.assertTrue(sinaStockResponseObject.isSuccess());
        expected.setSuccess(sinaStockResponseObject.isSuccess());
        Assert.assertEquals(sinaStockResponseObject.toString(), expected.toString());
    }

    @DataProvider(name = "requestTest")
    public Object[][] dataProvider() {
        return new Object[][] { { "10001", new SinaStockRequestObject("10001", 2),
                                 "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=2&num=1000&sort=symbol&asc=1&node=hs_a",
                                 new SinaStockResponseObject(Arrays.asList(new Result("sh603998", "603998", "方盛制药"), new Result("sh603997", "603997", "继峰股份"))) } };
    }
}
