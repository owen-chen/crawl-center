/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-15
 * File Name      : XmlPropertyPlaceholderConfigurer.java
 */

package org.archmage.cc.framework.placeholder;

import java.util.Properties;

import org.archmage.cc.configuration.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * PropertyPlaceholderConfigurer reads placeholder values from xml config file <br />
 * The xml config file format is the same as apache commons configurations <br>
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-15
 */
public class XmlPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements InitializingBean {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** {@link XmlConfiguration} */
    private XmlConfiguration config;

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
        String propVal = null;
        if (systemPropertiesMode == SYSTEM_PROPERTIES_MODE_OVERRIDE) {
            propVal = resolveSystemProperty(placeholder);
        }
        if (propVal == null) {
            propVal = config.getString(placeholder);
        }
        if (propVal == null) {
            propVal = resolvePlaceholder(placeholder, props);
        }

        if (propVal == null && systemPropertiesMode == SYSTEM_PROPERTIES_MODE_FALLBACK) {
            propVal = resolveSystemProperty(placeholder);
        }

        return propVal;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (config != null) {
            LOGGER.info("Sysconfig has been initializated and its name is {}", config.getConfigFilePath());
        }
        else {
            LOGGER.error("Sysconfig has not been initializated");
        }
    }

    /**
     * setter method
     * 
     * @see XmlPropertyPlaceholderConfigurer#config
     * @param config
     *            the config to set
     */
    public void setConfig(XmlConfiguration config) {
        this.config = config;
    }
}
