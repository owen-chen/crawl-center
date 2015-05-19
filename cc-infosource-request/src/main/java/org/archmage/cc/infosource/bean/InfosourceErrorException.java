/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-10-29
 * File Name      : InfosourceErrorException.java
 */

package org.archmage.cc.infosource.bean;

/**
 * infosource error exception
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-10-29
 */
public class InfosourceErrorException extends Exception {
    /** Serial version UID */
    private static final long serialVersionUID = -2242424810841518598L;

    /** {@link InfosourceErrorCode} */
    private InfosourceErrorCode infosourceErrorCode;

    /**
     * constructor
     * 
     * @param infosourceErrorCode
     *            {@link InfosourceErrorCode}
     */
    public InfosourceErrorException(InfosourceErrorCode infosourceErrorCode) {
        this.infosourceErrorCode = infosourceErrorCode;
    }

    /**
     * constructor
     * 
     * @param infosourceErrorCode
     *            {@link InfosourceErrorCode}
     * @param errorMessage
     *            error message
     */
    public InfosourceErrorException(InfosourceErrorCode infosourceErrorCode, String errorMessage) {
        super(errorMessage);
        this.infosourceErrorCode = infosourceErrorCode;
    }

    /**
     * getter method
     * 
     * @see InfosourceErrorException#infosourceErrorCode
     * @return the infosourceErrorCode
     */
    public InfosourceErrorCode getInfosourceErrorCode() {
        return infosourceErrorCode;
    }
}
