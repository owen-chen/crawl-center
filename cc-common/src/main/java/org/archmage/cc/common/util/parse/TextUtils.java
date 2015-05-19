/*
 * Create Author  : chen.chen.9
 * Create Date    : 2014-1-2
 * File Name      : HtmlUtils.java
 */

package org.archmage.cc.common.util.parse;

import org.apache.commons.lang.StringUtils;

/**
 * text utils
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2014-1-2
 */
public class TextUtils {
    /**
     * remove line break
     * <p>
     * 
     * @author chen.chen.9, 2014-1-2
     * @param input
     *            input string
     * @return string
     */
    public static String removeLineBreak(String input) {
        return StringUtils.remove(input, "\n");
    }
}
