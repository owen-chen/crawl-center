/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-10-29
 * File Name      : InfosourceErrorCode.java
 */

package org.archmage.cc.infosource.bean;

/**
 * error code of infosource
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-10-29
 */
public enum InfosourceErrorCode {
    /** common error code */
    /** 拼接url时，不支持的编码异常%IS%_1000 */
    UNSUPPORTED_ENCODING_EXCEPTION("%IS%_1000"),
    /** 信源访问的url为空%IS%_1001 */
    EMPTY_URL_EXCEPTION("%IS%_1001"),
    /** 请求信源网络异常%IS@_1002 */
    REQUEST_IS_EXCEPTION("%IS@_1002"),
    /** 请求信源超时异常%IS@_1003 */
    REQUEST_IS_TIMEOUT_EXCEPTION("%IS@_1003"),
    /** 请求信源结果异常%IS%_1004 */
    UNVALID_RESPONSE_RESULT_EXCEPTION("%IS%_1004"),
    /** 信源结果反序列化前校验失败%IS%_1005 */
    UNVALID_RESPONSE_RESULT_BEFORE_PARSER_EXCEPTION("%IS%_1005"),
    /** 不支持的反序列化类型，目前只支持html，xml和json %IS%_1006 */
    UNSUPPORTED_PARSER_TYPE_EXCEPTION("%IS%_1006"),
    /** 不支持的方法调用操作%IS%_1007 */
    UNSUPPORTED_OPERATION_EXCEPTION("%IS%_1007"),
    /** 反序列化异常%IS%_1008 */
    PARSER_EXCEPTION("%IS%_1008"),
    /** 信源结果反序列化后校验失败%IS%_1009 */
    UNVALID_RESPONSE_RESULT_AFTER_PARSER_EXCEPTION("%IS%_1009"),
    /** 反射调用赋值方法异常%IS%_1010 */
    SETTER_REFLECT_EXCEPTION("%IS%_1010"),
    /** 未能获取到响应文件%IS%_1011 */
    RESPONSE_FILE_NULL_EXCEPTION("%IS%_1011"),

    /** 特别信源的异常错误 */
    /** 股票 */
    /** 新浪财经股票数据不合法，start，top，bottom为空%IS#_2051 */
    STOCK_SINA_FINANCE_INVALID_DATA("%IS#_2051"),
    /** 指南针股票：访问信源返回错误信息%IS%_2052 */
    STOCK_COMPASS_ERROR_DATA_RETURN("%IS%_2052"),
    /** 指南针股票：返回空的数据体%IS#_2053 */
    STOCK_COMPASS_NULL_DATA_RETURN("%IS#_2053"),

    /** 股票 */
    /** 股票：股票代码不能为空%IS~_3070 */
    STOCK_NULL_CODE("%IS~_3070"),
    /** 股票：股票种类不能为空%IS~_3071 */
    STOCK_NULL_CATEGORY("%IS~_3071"),

    /** 其他普通错误异常%IS%_500 */
    RUNTIME_EXCEPTION("%IS%_500");

    /** value */
    private String value;

    /**
     * constructor
     * 
     * @param value
     *            value
     */
    private InfosourceErrorCode(String value) {
        this.value = value;
    }

    /**
     * getter method
     * 
     * @see InfosourceErrorCode#value
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
