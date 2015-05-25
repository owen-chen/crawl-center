/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-28
 * File Name      : InfosourceRequestFactory.java
 */

package org.archmage.cc.infosource.factory;

import java.lang.reflect.InvocationTargetException;

import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.response.ResponseObject;

/**
 * factory interface
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-28
 */
public interface InfosourceRequestFactory<T> {
    /**
     * request infosource.<br />
     * no repeated
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param requestObject
     *            {@lin RequestObject}
     * @return {@link ResponseObject}
     */
    T request(RequestObject requestObject) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException,
            InvocationTargetException;

    /**
     * request infosource.<br />
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param requestObject
     *            {@lin RequestObject}
     * @param repeated
     *            whether or not repeated if failed
     * @return {@link ResponseObject}
     */
    T request(RequestObject requestObject, boolean repeated) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException,
            IllegalAccessException, InvocationTargetException;
}
