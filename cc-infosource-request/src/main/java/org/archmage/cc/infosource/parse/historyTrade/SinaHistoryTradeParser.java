/*
 * Create Author  : chen.chen.9
 * Create Date    : May 25, 2015
 * File Name      : SinaHistoryTradeParser.java
 */

package org.archmage.cc.infosource.parse.historyTrade;

import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.archmage.cc.infosource.bean.InfosourceErrorException;
import org.archmage.cc.infosource.dto.response.historyTrade.Result;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.infosource.parse.BaseParser;

/**
 * sina history trade parser
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 25, 2015
 */
public class SinaHistoryTradeParser extends BaseParser<SinaHistoryTradeResponseObject> {
    @Override
    protected SinaHistoryTradeResponseObject fromHtml(String original) throws InfosourceErrorException {
        SinaHistoryTradeResponseObject sinaHistoryTradeResponseObject = new SinaHistoryTradeResponseObject();

        if (StringUtils.contains(original, "当天没有数据")) {
            sinaHistoryTradeResponseObject.setClosed(true);
        }
        else {
            sinaHistoryTradeResponseObject.setClosed(false);

            Scanner scanner = null;
            try {
                scanner = new Scanner(original);
                boolean skipFirstLine = true;
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    if (skipFirstLine) {
                        skipFirstLine = false;

                        continue;
                    }

                    if (StringUtils.isBlank(nextLine)) {
                        continue;
                    }

                    Result result = new Result();

                    String[] stringArray = StringUtils.split(nextLine, "\t");
                    for (int i = 0; i < stringArray.length; i++) {
                        String string = stringArray[i];
                        if (i == 0) {
                            String[] timeArray = StringUtils.split(string, ":");
                            result.setHour(timeArray[0]);
                            result.setMinute(timeArray[1]);
                            result.setSecond(timeArray[2]);
                        }
                        else if (i == 1) {
                            result.setCurrent(string);
                        }
                        else if (i == 2) {
                            result.setQuoteTrend(string);
                        }
                        else if (i == 3) {
                            result.setDealAmount(string);
                        }
                        else if (i == 4) {
                            result.setDealFigure(string);
                        }
                        else if (i == 5) {
                            result.setFeature(string);
                        }
                    }

                    sinaHistoryTradeResponseObject.getResultList().add(result);
                }
            }
            finally {
                IOUtils.closeQuietly(scanner);
            }
        }

        return sinaHistoryTradeResponseObject;
    }
}
