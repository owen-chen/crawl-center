/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-19
 * File Name      : Parser.java
 */

package org.archmage.cc.infosource.parse;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;
import org.archmage.cc.common.util.constants.ConstantsInterface;
import org.archmage.cc.infosource.bean.InfosourceErrorCode;
import org.archmage.cc.infosource.bean.InfosourceErrorException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * 解析信源返回的数据成javabean
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-19
 */
public class BaseParser<T> implements Parser<T>, ConstantsInterface {
    /** Serial version UID */
    protected final String ROOT = "object";

    @Override
    public T parse(Class<T> clazz, String original, String type) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException,
            InfosourceErrorException {
        if (StringUtils.equals(type, "json")) {
            return fromJson(clazz, original);
        }
        else if (StringUtils.equals(type, "xml")) {
            return fromXml(clazz, original);
        }
        else if (StringUtils.equals(type, "html")) {
            return fromHtml(clazz, original);
        }
        else {
            throw new InfosourceErrorException(InfosourceErrorCode.UNSUPPORTED_PARSER_TYPE_EXCEPTION);
        }
    }

    /**
     * parse xml
     * <p>
     * 
     * @author shun.lv, 2014-1-10
     * @param <T>
     * @param clazz
     *            待转换成的class
     * @param original
     *            原始文本
     * @return 转换后的文本
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    protected T fromXml(Class<T> clazz, String original) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        HierarchicalStreamDriver hierarchicalStreamDriver = new DomDriver(UTF8);

        XStream xStream = generateXStream(hierarchicalStreamDriver);

        Field aliasField = clazz.getField("XSTREAM_ALIAS");
        xStream.alias(String.valueOf(aliasField.get(null)), clazz);

        return (T) xStream.fromXML(original);
    }

    /**
     * parse json
     * <p>
     * 
     * @param clazz
     *            the class
     * @param original
     *            original text
     * @return the class
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    protected T fromJson(Class<T> clazz, String original) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        HierarchicalStreamDriver hierarchicalStreamDriver = new JettisonMappedXmlDriver();

        XStream xStream = generateXStream(hierarchicalStreamDriver);

        Field rootField = clazz.getField("XSTREAM_ROOT");
        boolean root = (Boolean) rootField.get(null);
        if (root) {
            original = "{\"" + ROOT + "\":" + original + "}";
            xStream.alias(ROOT, clazz);
        }
        else {
            Field aliasField = clazz.getField("XSTREAM_ALIAS");
            xStream.alias(String.valueOf(aliasField.get(null)), clazz);
        }

        return (T) xStream.fromXML(original);
    }

    /**
     * parse html
     * <p>
     * 
     * @author chen.chen.9, 2013-9-3
     * @param clazz
     *            the class
     * @param original
     *            original response html
     * @return the class
     * @throws InfosourceErrorException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    protected T fromHtml(Class<T> clazz, String original) throws InfosourceErrorException, SecurityException, IllegalArgumentException, NoSuchFieldException,
            IllegalAccessException {
        throw new InfosourceErrorException(InfosourceErrorCode.UNSUPPORTED_OPERATION_EXCEPTION);
    }

    /**
     * {@link XStream} generator
     * <p>
     * 
     * @param hierarchicalStreamDriver
     *            {@link HierarchicalStreamDriver}
     * @return {@link XStream}
     */
    private XStream generateXStream(HierarchicalStreamDriver hierarchicalStreamDriver) {
        XStream xStream = new XStream(hierarchicalStreamDriver) {
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    // avoid {@link XStream} crash when field misses
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        try {
                            return definedIn != Object.class || realClass(fieldName) != null;
                        }
                        catch (CannotResolveClassException cnrce) {
                            return false;
                        }
                    }
                };
            }
        };
        xStream.autodetectAnnotations(true);

        return xStream;
    }
}