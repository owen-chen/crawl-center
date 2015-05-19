/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-28
 * File Name      : InfosourceRequestFactoryImpl.java
 */

package org.archmage.cc.infosource.factory.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang.StringUtils;
import org.archmage.cc.framework.bean.ErrorCode;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.bean.RequestObject;
import org.archmage.cc.infosource.bean.ResponseObject;
import org.archmage.cc.infosource.factory.InfosourceRequestFactory;
import org.archmage.cc.infosource.metadata.MetadataCacheManager;
import org.archmage.cc.infosource.reader.InfosourceConfigReader;
import org.archmage.cc.infosource.reader.bean.Infosource;
import org.archmage.cc.infosource.request.InfosourceRequest;

/**
 * infosource factory class
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-28
 */
public class InfosourceRequestFactoryImpl<T> implements InfosourceRequestFactory<T> {
    /** {@link InfosourceConfigReader} */
    private InfosourceConfigReader isconfigReader;

    /** {@link MetadataCacheManager} */
    private MetadataCacheManager metadataCacheManager;

    /** {@link LogContainer} */
    private LogContainer logContainer;

    @Override
    public T request(RequestObject requestObject, boolean repeated) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        String isCode = requestObject.getCode();
        if (StringUtils.isEmpty(isCode)) {
            ResponseObject responseObject = new ResponseObject();
            responseObject.setSuccess(false);
            responseObject.setErrorCode(ErrorCode.EMPTY_ISCODE.getValue());

            return (T) responseObject;
        }

        Infosource infosource = isconfigReader.retrieveInfosource(isCode);
        if (infosource == null) {
            ResponseObject responseObject = new ResponseObject();
            responseObject.setSuccess(false);
            responseObject.setErrorCode(ErrorCode.NO_FIT_IS_CONFIG.getValue());

            return (T) responseObject;
        }

        Class<InfosourceRequest<ResponseObject>> requestHandlerClass = (Class<InfosourceRequest<ResponseObject>>) infosource.getRequestHandlerClass();
        Constructor<InfosourceRequest<ResponseObject>> constructor = requestHandlerClass.getDeclaredConstructor(LogContainer.class, MetadataCacheManager.class);
        InfosourceRequest<ResponseObject> newInstance = constructor.newInstance(logContainer, metadataCacheManager);

        return (T) newInstance.request(infosource, requestObject, repeated);
    }

    @Override
    public T request(RequestObject requestObject) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException,
            InvocationTargetException {
        return request(requestObject, false);
    }

    /**
     * setter method
     * 
     * @see InfosourceRequestFactoryImpl#isconfigReader
     * @param isconfigReader
     *            the isconfigReader to set
     */
    public void setIsconfigReader(InfosourceConfigReader isconfigReader) {
        this.isconfigReader = isconfigReader;
    }

    /**
     * setter method
     * 
     * @see InfosourceRequestFactoryImpl#metadataCacheManager
     * @param metadataCacheManager
     *            the metadataCacheManager to set
     */
    public void setMetadataCacheManager(MetadataCacheManager metadataCacheManager) {
        this.metadataCacheManager = metadataCacheManager;
    }

    /**
     * setter method
     * 
     * @see InfosourceRequestFactoryImpl#logContainer
     * @param logContainer
     *            the logContainer to set
     */
    public void setLogContainer(LogContainer logContainer) {
        this.logContainer = logContainer;
    }
}
