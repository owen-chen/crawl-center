/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-9
 * File Name      : MetaDataCacheManager.java
 */

package org.archmage.cc.infosource.metadata;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.framework.cache.RefreshableCacheManager;
import org.archmage.cc.infosource.metadata.loader.LoaderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * metadata cache manager
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-9
 */
public class MetadataCacheManager extends RefreshableCacheManager<String, Object> {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** {@link XmlConfiguration} */
    private XmlConfiguration sysconfig;

    /** {@link LoaderManager} list */
    private List<LoaderManager> loaderManagerList;

    /** metadata map */
    private Map<String, Object> map = new HashMap<String, Object>();

    @Override
    public Map<String, Object> readData() {
        if (CollectionUtils.isEmpty(loaderManagerList)) {
            LOGGER.error("no loader manager found");

            return null;
        }

        Map<String, Object> localMetadataMap = new HashMap<String, Object>();

        for (LoaderManager loaderManager : loaderManagerList) {
            Map<String, Object> map = loaderManager.load();

            if (MapUtils.isEmpty(map)) {
                continue;
            }

            Set<Entry<String, Object>> set = map.entrySet();
            Iterator<Entry<String, Object>> iterator = set.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> entry = iterator.next();

                localMetadataMap.put(entry.getKey(), entry.getValue());
            }
        }

        return localMetadataMap;
    }

    @Override
    protected XmlConfiguration getSysconfig() {
        return sysconfig;
    }

    @Override
    protected long getRefreshIntervalInSecond() {
        return sysconfig.getInt("SerializationAndDataCache.CacheInterval");
    }

    @Override
    protected void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    protected Map<String, Object> getMap() {
        return map;
    }

    /**
     * setter method
     * 
     * @see MetadataCacheManager#sysconfig
     * @param sysconfig
     *            the sysconfig to set
     */
    public void setSysconfig(XmlConfiguration sysconfig) {
        this.sysconfig = sysconfig;
    }

    /**
     * setter method
     * 
     * @see MetadataCacheManager#loaderManagerList
     * @param loaderManagerList
     *            the loaderManagerList to set
     */
    public void setLoaderManagerList(List<LoaderManager> loaderManagerList) {
        this.loaderManagerList = loaderManagerList;
    }
}
