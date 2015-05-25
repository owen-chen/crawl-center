/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-19
 * File Name      : InfosourceRequest.java
 */

package org.archmage.cc.infosource.request;

import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.response.ResponseObject;
import org.archmage.cc.infosource.reader.bean.Infosource;

/**
 * request infosource
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-19
 */
public interface InfosourceRequest<T extends ResponseObject> {
    /**
     * request infosource
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param requestObject
     *            {@lin RequestObject}
     * @param infosource
     *            {@link Infosource}
     * @param repeated
     *            whether or not repeated if failed
     * @return {@link ResponseObject}
     */
    T request(Infosource infosource, RequestObject requestObject, boolean repeated);
}
