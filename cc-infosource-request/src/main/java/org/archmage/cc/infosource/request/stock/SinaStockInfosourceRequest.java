/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-16
 * File Name      : SinaStockInfosourceRequest.java
 */

package org.archmage.cc.infosource.request.stock;

import org.apache.commons.lang.StringUtils;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.request.stock.SinaStockRequestObject;
import org.archmage.cc.infosource.dto.response.stock.SinaStockResponseObject;
import org.archmage.cc.infosource.metadata.MetadataCacheManager;
import org.archmage.cc.infosource.request.AbstractInfosourceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * stock info from sina
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-16
 */
public class SinaStockInfosourceRequest extends AbstractInfosourceRequest<SinaStockResponseObject> {
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
    public SinaStockInfosourceRequest(LogContainer logContainer, MetadataCacheManager metadataCacheManager) {
        super();
        this.logContainer = logContainer;
    }

    @Override
    protected SinaStockResponseObject getResponseInstance() {
        return new SinaStockResponseObject();
    }

    @Override
    protected String generateUrl(String url, RequestObject requestObject) {
        SinaStockRequestObject sinaStockRequestObject = (SinaStockRequestObject) requestObject;
        return StringUtils.replace(url, "${PAGE_NO}", Integer.toString(sinaStockRequestObject.getPageNo()));
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