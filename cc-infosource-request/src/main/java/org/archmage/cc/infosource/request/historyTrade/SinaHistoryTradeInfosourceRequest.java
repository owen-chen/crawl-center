/*
 * Create Author  : chen.chen.9
 * Create Date    : May 25, 2015
 * File Name      : SinaHistoryTradeInfosourceRequest.java
 */

package org.archmage.cc.infosource.request.historyTrade;

import org.apache.commons.lang.StringUtils;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.request.historyTrade.SinaHistoryTradeRequestObject;
import org.archmage.cc.infosource.dto.response.historyTrade.SinaHistoryTradeResponseObject;
import org.archmage.cc.infosource.metadata.MetadataCacheManager;
import org.archmage.cc.infosource.request.AbstractInfosourceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sina history trade infosource request
 * <p>
 * 
 * @author : chen.chen.9
 * @date : May 25, 2015
 */
public class SinaHistoryTradeInfosourceRequest extends AbstractInfosourceRequest<SinaHistoryTradeResponseObject> {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** {@link LogContainer} */
    private LogContainer logContainer;

    /**
     * constructor
     * 
     * @param logContainer
     *            {@link LogContainer}
     * @param metadataCacheManager
     *            {@link MetadataCacheManager}
     */
    public SinaHistoryTradeInfosourceRequest(LogContainer logContainer, MetadataCacheManager metadataCacheManager) {
        super();
        this.logContainer = logContainer;
    }

    @Override
    protected SinaHistoryTradeResponseObject getResponseInstance() {
        return new SinaHistoryTradeResponseObject();
    }

    @Override
    protected String generateUrl(String url, RequestObject requestObject) {
        SinaHistoryTradeRequestObject sinaHistoryTradeRequestObject = (SinaHistoryTradeRequestObject) requestObject;

        return StringUtils.replaceEach(url,
                                       new String[] { "${SYMBOL}", "${YEAR}", "${MONTH}", "${DAY}" },
                                       new String[] { sinaHistoryTradeRequestObject.getSymbol(), Integer.toString(sinaHistoryTradeRequestObject.getYear()),
                                                     StringUtils.leftPad(Integer.toString(sinaHistoryTradeRequestObject.getMonth()), 2, '0'),
                                                     StringUtils.leftPad(Integer.toString(sinaHistoryTradeRequestObject.getDay()), 2, '0') });
    }

    @Override
    protected Logger getLOGGER() {
        return LOGGER;
    }

    @Override
    protected LogContainer getLogContainer() {
        return logContainer;
    }

}
