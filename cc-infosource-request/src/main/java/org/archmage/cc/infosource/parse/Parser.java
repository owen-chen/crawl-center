/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-3
 * File Name      : Parser.java
 */

package org.archmage.cc.infosource.parse;

import org.archmage.cc.infosource.bean.InfosourceErrorException;

/**
 * parse response data
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-3
 */
public interface Parser<T> {
    /**
     * parse response data
     * <p>
     * 
     * @author chen.chen.9, 2013-8-21
     * @param clazz
     *            the class
     * @param original
     *            original response
     * @param type
     *            parse type: json/html/xml
     * @return the class
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws InfosourceErrorException
     */
    T parse(Class<T> clazz, String original, String type) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException,
            InfosourceErrorException;
}
