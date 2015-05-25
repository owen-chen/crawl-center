/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-20
 * File Name      : InfosourceConfigReader.java
 */

package org.archmage.cc.infosource.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.archmage.cc.configuration.XmlConfiguration;
import org.archmage.cc.infosource.parse.BaseParser;
import org.archmage.cc.infosource.parse.Parser;
import org.archmage.cc.infosource.reader.bean.Infosource;
import org.archmage.cc.infosource.reader.bean.SubInfosource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * infosource config reader
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-20
 */
public class InfosourceConfigReader {
    /** LOGGER */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** isconfig */
    private XmlConfiguration isconfig;

    /** key：infosource code，value：{@link Infosource} */
    private Map<String, Infosource> infosourceMap = new HashMap<String, Infosource>();

    /**
     * retrieve {@link Infosource}
     * <p>
     * 
     * @author chen.chen.9, 2013-8-20
     * @param code
     *            infosource code
     * @return {@link Infosource}
     */
    public Infosource retrieveInfosource(String code) {
        return infosourceMap.get(code);
    }

    /**
     * initialize infosource config reader
     * <p>
     * 
     * @author chen.chen.9, 2013-8-20
     */
    public void init() {
        long startTime = System.currentTimeMillis();

        List<?> infosourceList = getIsconfig().getConfig().configurationsAt("Infosources.Infosource");

        if (CollectionUtils.isEmpty(infosourceList)) {
            LOGGER.error("None of infosources can be found in {}", getIsconfig().getConfigFilePath());

            return;
        }

        for (int i = 0; i < infosourceList.size(); i++) {
            Infosource infosource = new Infosource();

            infosource.setCode(getIsconfig().getString("Infosources.Infosource(" + i + ").Code"));
            infosource.setName(getIsconfig().getString("Infosources.Infosource(" + i + ").Name"));

            String requestHandlerClass = getIsconfig().getString("Infosources.Infosource(" + i + ").RequestHandlerClass");
            String responseClass = getIsconfig().getString("Infosources.Infosource(" + i + ").ResponseClass");

            try {
                infosource.setRequestHandlerClass(Class.forName(requestHandlerClass));
                infosource.setResponseClass(Class.forName(responseClass));
            }
            catch (ClassNotFoundException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            }

            if (infosource.getResponseClass() == null || infosource.getRequestHandlerClass() == null) {
                continue;
            }

            String xpath = "Infosources.Infosource(" + i + ").SubInfosources.SubInfosource";
            List<?> subInfosourceList = getIsconfig().getConfig().configurationsAt(xpath);
            if (CollectionUtils.isEmpty(subInfosourceList)) {
                LOGGER.error("None of subInfosources can be found in {}, {}", getIsconfig().getConfigFilePath(), xpath);

                continue;
            }

            for (int j = 0; j < subInfosourceList.size(); j++) {
                SubInfosource subInfosource = new SubInfosource();

                subInfosource.setName(getIsconfig().getString(xpath + "(" + j + ").Name"));
                subInfosource.setUrl(getIsconfig().getString(xpath + "(" + j + ").Url"));
                subInfosource.setResponseType(getIsconfig().getString(xpath + "(" + j + ").ResponseType"));
                subInfosource.setResponseCharset(getIsconfig().getString(xpath + "(" + j + ").ResponseCharset"));
                subInfosource.setTimeout(getIsconfig().getInt(xpath + "(" + j + ").Timeout") * 1000);

                String parserClass = getIsconfig().getString(xpath + "(" + j + ").ParserClass");
                if (StringUtils.isNotEmpty(parserClass)) {
                    try {
                        subInfosource.setParser((Parser<?>) Class.forName(parserClass).newInstance());
                    }
                    catch (InstantiationException e) {
                        LOGGER.error(ExceptionUtils.getStackTrace(e));
                    }
                    catch (IllegalAccessException e) {
                        LOGGER.error(ExceptionUtils.getStackTrace(e));
                    }
                    catch (ClassNotFoundException e) {
                        LOGGER.error(ExceptionUtils.getStackTrace(e));
                    }
                }
                else {
                    subInfosource.setParser(new BaseParser<Object>());
                }

                String responseClassName = getIsconfig().getString(xpath + "(" + j + ").ResponseClass");
                if (StringUtils.isEmpty(responseClassName)) {
                    responseClassName = responseClass;
                }
                try {
                    subInfosource.setResponseClass(Class.forName(responseClassName));
                }
                catch (ClassNotFoundException e) {
                    LOGGER.error(ExceptionUtils.getStackTrace(e));
                }

                if (subInfosource.getResponseClass() == null) {
                    continue;
                }

                infosource.getSubInfosourceList().add(subInfosource);
            }

            infosourceMap.put(infosource.getCode(), infosource);
        }

        LOGGER.info("{} infosources config has been loaded. elapsed {} ms", infosourceMap.keySet().size(), System.currentTimeMillis() - startTime);
    }

    /**
     * setter method
     * 
     * @see InfosourceConfigReader#isconfig
     * @param isconfig
     *            the isconfig to set
     */
    public void setIsconfig(XmlConfiguration isconfig) {
        this.isconfig = isconfig;
    }

    /**
     * getter method
     * 
     * @see InfosourceConfigReader#isconfig
     * @return the isconfig
     */
    public XmlConfiguration getIsconfig() {
        return isconfig;
    }
}
