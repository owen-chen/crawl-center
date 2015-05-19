/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-14
 * File Name      : XmlConfiguration.java
 */

package org.archmage.cc.configuration;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.archmage.cc.common.resourceLoader.ResourceLoader;
import org.archmage.cc.common.util.constants.ConstantsInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

/**
 * load config from file
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-14
 */
public class XmlConfiguration implements ConstantsInterface {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** delimiter */
    private static final String DELIMITER = ";";

    /** {@link ResourceLoader} */
    private ResourceLoader resourceLoader;

    /** config file path */
    private String configFilePath;

    /** config files' path */
    private String[] configFilepathArray;

    /** {@link XMLConfiguration} */
    private XMLConfiguration config = new XMLConfiguration();

    /**
     * map is used to cache config, for increasing speed of retrieving.<br />
     * key：nodeName，value：nodeValue<br />
     **/
    private Map<String, Object> valueMap = new ConcurrentHashMap<String, Object>();

    /**
     * constructor
     * 
     * @param configFilepath
     *            config file path
     */
    public XmlConfiguration(String configFilepath) {
        this.configFilePath = configFilepath;
        this.configFilepathArray = StringUtils.split(configFilepath, DELIMITER);
    }

    /**
     * load config to ram
     */
    public void init() {
        if (ArrayUtils.isEmpty(configFilepathArray)) {
            LOGGER.error("The construct-arg: configFilepath must be filled in.");

            return;
        }

        for (String filepath : configFilepathArray) {
            Resource[] resources = null;
            try {
                resources = resourceLoader.getResources(filepath);
            }
            catch (IOException e) {
                LOGGER.error("Could not find any config file in {}", filepath, e);
            }

            if (ArrayUtils.isEmpty(resources)) {
                LOGGER.warn("Configuration file does not exist, please make sure the config file {} exist in your classpath", filepath);

                continue;
            }
            for (Resource resource : resources) {
                if (resource == null || !resource.exists() || !resource.isReadable()) {
                    LOGGER.warn("Configuration file does not exist, please make sure the config file {} exist in your classpath", filepath);
                    continue;
                }

                String resourcePath = filepath;
                try {
                    resourcePath = resource.getURL().getPath();
                }
                catch (IOException e) {
                    LOGGER.error("Could not find any config file in {}", resourcePath, e);
                }

                XMLConfiguration config = new XMLConfiguration();
                // invisible charactor
                config.setListDelimiter('　');

                config.setAttributeSplittingDisabled(true);
                config.setDelimiterParsingDisabled(true);

                try {
                    config.load(resource.getInputStream());

                    config.setReloadingStrategy(new FileChangedReloadingStrategy());

                    LOGGER.info("Succedd to loading configuration file {}", resourcePath);

                    if (!this.config.isEmpty()) {
                        // FIXME append() may make configurationsAt() always get
                        // only one record
                        this.config.append(config);
                    }
                    else {
                        this.config = config;
                    }
                }
                catch (ConfigurationException e) {
                    LOGGER.error("error loading configuration file {}", resourcePath, e);
                }
                catch (IOException e) {
                    LOGGER.error("Could not find any config file in {}", resourcePath, e);
                }
            }
        }
    }

    /**
     * get string of the key
     * 
     * @param key
     *            key
     * @return value
     */
    public String getString(String key) {
        if (valueMap.containsKey(key)) {
            return String.valueOf(valueMap.get(key));
        }

        String value = null;
        if (config.containsKey(key + "[@value]")) {
            value = config.getString(key + "[@value]");
        }
        else {
            value = config.getString(key);
        }

        value = value != null ? value : "";
        putKeyIntoMap(key, value);

        return value;
    }

    /**
     * remove "(0)" from key.<br />
     * if key has "(0)", put both removed key and original key into map<br />
     * <p>
     * 
     * @author owen.chen, Jan 11, 2011
     * @param key
     *            original key
     * @param value
     *            value
     */
    private void putKeyIntoMap(String key, Object value) {
        String anotherKey = StringUtils.remove(key, "(0)");
        if (!StringUtils.equals(anotherKey, key)) {
            valueMap.put(anotherKey, value);
        }
        valueMap.put(key, value);
    }

    /**
     * get double value of the key
     * 
     * @param key
     *            key
     * @return value
     */
    public Double getDouble(String key) {
        if (valueMap.containsKey(key)) {
            String value = String.valueOf(valueMap.get(key));
            if (StringUtils.isNotEmpty(value)) {
                try {
                    return Double.valueOf(value);
                }
                catch (NumberFormatException e) {
                    LOGGER.warn("can't parse value to double.", e);
                }
            }
        }

        double value;
        if (config.containsKey(key + "[@value]")) {
            value = config.getDouble(key + "[@value]");
        }
        else {
            value = config.getDouble(key, 0);
        }

        putKeyIntoMap(key, value);

        return value;
    }

    /**
     * get value of the key
     * 
     * @param key
     *            key
     * @return value
     */
    public Object getObject(String key) {
        if (valueMap.containsKey(key)) {
            return valueMap.get(key);
        }

        Object value = null;
        if (config.containsKey(key + "[@value]")) {
            value = config.getProperty(key + "[@value]");
        }
        else {
            value = config.getProperty(key);
        }

        value = (value != null) ? value : "";
        putKeyIntoMap(key, value);

        return value;
    }

    /**
     * get boolean value of the key
     * 
     * @param key
     *            key
     * @return value
     */
    public boolean getBoolean(String key) {
        if (valueMap.containsKey(key)) {
            return Boolean.valueOf(String.valueOf(valueMap.get(key)));
        }

        boolean value;
        if (config.containsKey(key + "[@value]")) {
            value = config.getBoolean(key + "[@value]");
        }
        else {
            value = config.getBoolean(key, false);
        }

        putKeyIntoMap(key, value);

        return value;
    }

    /**
     * get int value of the key
     * 
     * @param key
     *            key
     * @return value
     */
    public int getInt(String key) {
        if (valueMap.containsKey(key)) {
            String value = String.valueOf(valueMap.get(key));
            if (StringUtils.isNotEmpty(value)) {
                try {
                    return Integer.valueOf(value);
                }
                catch (NumberFormatException e) {
                    LOGGER.warn("can't parse value to int.", e);
                }
            }
        }

        int value;
        if (config.containsKey(key + "[@value]")) {
            value = config.getInt(key + "[@value]");
        }
        else {
            value = config.getInt(key, 0);
        }

        putKeyIntoMap(key, value);

        return value;
    }

    /**
     * get description of the node
     * 
     * @param nodeName
     *            node name
     * @return node description
     */
    public String getPropertyDescription(String nodeName) {
        String key = nodeName + "[@description]";

        return config.getString(key);
    }

    /**
     * getter method
     * 
     * @see XmlConfiguration#configFilePath
     * @return the configFilePath
     */
    public String getConfigFilePath() {
        return configFilePath;
    }

    /**
     * setter method
     * 
     * @see XmlConfiguration#resourceLoader
     * @param resourceLoader
     *            the resourceLoader to set
     */
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * getter method
     * 
     * @see XmlConfiguration#config
     * @return the config
     */
    public XMLConfiguration getConfig() {
        return config;
    }
}
