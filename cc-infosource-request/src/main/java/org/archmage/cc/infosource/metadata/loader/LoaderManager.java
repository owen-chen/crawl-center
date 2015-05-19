/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-9
 * File Name      : LoaderManager.java
 */

package org.archmage.cc.infosource.metadata.loader;

import java.util.Map;

/**
 * metadata loader manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-9
 */
public interface LoaderManager {
    /**
     * load metadata
     * <p>
     * 
     * @author chen.chen.9, 2013-9-9
     * @return metadata
     */
    Map<String, Object> load();
}
