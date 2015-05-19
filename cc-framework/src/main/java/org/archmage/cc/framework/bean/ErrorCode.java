/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-9-4
 * File Name      : ErrorCode.java
 */

package org.archmage.cc.framework.bean;

/**
 * error code
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-9-4
 */
public enum ErrorCode {
    /** 信源编码为空 */
    EMPTY_ISCODE(100),
    /** 无默认信源编码 */
    EMPTY_DEFAULT_ISCODE(101),
    /** 业务为空 */
    EMPTY_COMMAND(102),
    /** 未知的调度规则，目前只支持3种规则 */
    UNKNOWN_POLICY(103),
    /** 权重调度策略中的信源权重不能都为0 */
    ALL_WEIGHTS_ARE_ZERO(104),
    /** 业务没有对应的规则列表 */
    EMPTY_RULELIST_FOR_COMMAND(105),
    /** 默认信源反射访问失败 */
    REQUEST_DEFAULT_IS_REFLECT_EXCEPTION(106),

    /** 没发现对应的信源配置 */
    NO_FIT_IS_CONFIG(200),

    /** 一般异常错误 */
    RUNTIME_ERROR(500),
    /** 网络故障：1002和1003 */
    NETWORK_ERROR(408),
    /** 外部信源故障，非可控因素 */
    OUTER_INFOSOURCE_ERROR(503),
    /** 输入参数有误，非可控因素 */
    INPUT_ERROR(400);

    /** value */
    private int value;

    /**
     * 私有构造函数
     * 
     * @param value
     *            value
     */
    private ErrorCode(int value) {
        this.value = value;
    }

    /**
     * getter method
     * 
     * @see ErrorCode#value
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
