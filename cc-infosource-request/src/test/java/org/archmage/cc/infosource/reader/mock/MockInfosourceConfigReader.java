/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-21
 * File Name      : MockInfosourceConfigReader.java
 */

package org.archmage.cc.infosource.reader.mock;

import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.infosource.reader.InfosourceConfigReader;

/**
 * mock InfosourceConfigReader
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-21
 */
public class MockInfosourceConfigReader extends InfosourceConfigReader {
    /** isconfig */
    private XmlConfiguration mockIsconfig;

    @Override
    public XmlConfiguration getIsconfig() {
        return mockIsconfig;
    }

    /**
     * setter method
     * 
     * @see MockInfosourceConfigReader#mockIsconfig
     * @param mockIsconfig
     *            the mockIsconfig to set
     */
    public void setMockIsconfig(XmlConfiguration mockIsconfig) {
        this.mockIsconfig = mockIsconfig;
    }
}
