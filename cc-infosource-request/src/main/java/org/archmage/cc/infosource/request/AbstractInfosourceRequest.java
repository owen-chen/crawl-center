/*
 * Create Author  : chen.chen.9
 * Create Date    : 2013-8-19
 * File Name      : AbstractInfosourceRequest.java
 */

package org.archmage.cc.infosource.request;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.archmage.cc.common.util.constants.ConstantsInterface;
import org.archmage.cc.common.util.http.GetMethodGenerator;
import org.archmage.cc.common.util.http.HttpclientGenerator;
import org.archmage.cc.framework.bean.ErrorCode;
import org.archmage.cc.framework.log.LogBean;
import org.archmage.cc.framework.log.LogContainer;
import org.archmage.cc.infosource.bean.InfosourceErrorCode;
import org.archmage.cc.infosource.bean.InfosourceErrorException;
import org.archmage.cc.infosource.bean.InfosourceRequestInnerLog;
import org.archmage.cc.infosource.bean.SubInfosourceRequestInnerLog;
import org.archmage.cc.infosource.dto.request.RequestObject;
import org.archmage.cc.infosource.dto.response.ResponseObject;
import org.archmage.cc.infosource.parse.Parser;
import org.archmage.cc.infosource.reader.bean.Infosource;
import org.archmage.cc.infosource.reader.bean.SubInfosource;
import org.slf4j.Logger;

/**
 * abstract infosource request class
 * <p>
 * 
 * @author : chen.chen.9
 * @date : 2013-8-19
 */
public abstract class AbstractInfosourceRequest<T extends ResponseObject> implements InfosourceRequest<T>, ConstantsInterface {
    @Override
    public T request(Infosource infosource, RequestObject requestObject, boolean repeatable) {
        long parentTotalTime = System.currentTimeMillis();

        long threadId = Thread.currentThread().getId();
        LogBean logBean = getLogContainer().retrieveLogBean(threadId);
        if (logBean == null) {
            getLOGGER().error("LogBean is unexpectedlly null in thread: {}.", threadId);
        }

        InfosourceRequestInnerLog infosourceRequestInnerLog = new InfosourceRequestInnerLog();
        infosourceRequestInnerLog.setCode(infosource.getCode());

        T t = null;
        try {
            List<SubInfosource> subInfosourceList = infosource.getSubInfosourceList();
            if (CollectionUtils.isEmpty(subInfosourceList)) {
                t = getResponseInstance();
                t.setSuccess(false);
                t.setErrorCode(ErrorCode.NO_FIT_IS_CONFIG.getValue());

                return t;
            }

            t = getResponseInstance();

            long requestTime = System.currentTimeMillis();
            boolean successful = false;
            try {
                for (SubInfosource subInfosource : subInfosourceList) {
                    long subTotalTime = System.currentTimeMillis();

                    boolean subSuccessful = true;
                    SubInfosourceRequestInnerLog subInfosourceRequestInnerLog = new SubInfosourceRequestInnerLog(subInfosource.getName());

                    try {
                        // 0. prehandler
                        preHandler(requestObject, subInfosource.getName());

                        // 1. generate url
                        long time1 = System.currentTimeMillis();
                        String url = null;
                        try {
                            url = subInfosource.getUrl();
                            url = generateUrl(url, requestObject);
                        }
                        catch (UnsupportedEncodingException e) {
                            throw new InfosourceErrorException(InfosourceErrorCode.UNSUPPORTED_ENCODING_EXCEPTION);
                        }
                        subInfosourceRequestInnerLog.setUrl(url);

                        if (StringUtils.isEmpty(url)) {
                            throw new InfosourceErrorException(InfosourceErrorCode.EMPTY_URL_EXCEPTION);
                        }
                        subInfosourceRequestInnerLog.setUrlElapsedTime(System.currentTimeMillis() - time1);

                        // 2. request and parse
                        Object parseObject = doRequestAndParse(url, subInfosource, repeatable, subInfosourceRequestInnerLog);

                        long time4 = System.currentTimeMillis();
                        if (parseObject == null) {
                            throw new InfosourceErrorException(InfosourceErrorCode.PARSER_EXCEPTION);
                        }

                        // 3. validate parsed data
                        validateAfterParser(parseObject);

                        // 4. assign data
                        // only one subinfosource
                        if (subInfosourceList.size() == 1) {
                            t = (T) parseObject;
                        }
                        // more than one subinfosource
                        else {
                            try {
                                Class<?> subResponseClass = subInfosource.getResponseClass();
                                Method setMethod = infosource.getResponseClass().getMethod("set" + subResponseClass.getSimpleName(), new Class[] { subResponseClass });
                                setMethod.setAccessible(true);
                                setMethod.invoke(t, parseObject);
                            }
                            catch (SecurityException e) {
                                throw new InfosourceErrorException(InfosourceErrorCode.SETTER_REFLECT_EXCEPTION);
                            }
                            catch (IllegalArgumentException e) {
                                throw new InfosourceErrorException(InfosourceErrorCode.SETTER_REFLECT_EXCEPTION);
                            }
                            catch (NoSuchMethodException e) {
                                throw new InfosourceErrorException(InfosourceErrorCode.SETTER_REFLECT_EXCEPTION);
                            }
                            catch (IllegalAccessException e) {
                                throw new InfosourceErrorException(InfosourceErrorCode.SETTER_REFLECT_EXCEPTION);
                            }
                            catch (InvocationTargetException e) {
                                throw new InfosourceErrorException(InfosourceErrorCode.SETTER_REFLECT_EXCEPTION);
                            }
                        }

                        // 5. finalHandler
                        postHandler(requestObject, t);

                        subSuccessful = true;

                        subInfosourceRequestInnerLog.setOtherElapsedTime(System.currentTimeMillis() - time4);
                    }
                    catch (InfosourceErrorException e) {
                        t.getErrorCodeInRequestList().add(e.getInfosourceErrorCode().getValue());
                        subInfosourceRequestInnerLog.setErrorCode(e.getInfosourceErrorCode().getValue());
                        subInfosourceRequestInnerLog.getErrorMessageInRequestList().add(e.getMessage());
                        subSuccessful = false;
                    }
                    catch (RuntimeException e) {
                        t.getErrorCodeInRequestList().add(InfosourceErrorCode.RUNTIME_EXCEPTION.getValue());
                        subInfosourceRequestInnerLog.setErrorCode(InfosourceErrorCode.RUNTIME_EXCEPTION.getValue());
                        subInfosourceRequestInnerLog.getErrorMessageInRequestList().add(ExceptionUtils.getStackTrace(e));
                        subSuccessful = false;
                    }
                    finally {
                        subInfosourceRequestInnerLog.setTotalElapsedTime(System.currentTimeMillis() - subTotalTime);
                        if (logBean != null) {
                            logBean.getInner().add(subInfosourceRequestInnerLog);
                        }

                        // if one of subinfosources succeed,
                        // the parent infosource succeed
                        if (subSuccessful) {
                            successful = true;
                        }
                    }
                }
            }
            finally {
                infosourceRequestInnerLog.setRequestElapsedTime(System.currentTimeMillis() - requestTime);

                t.setSuccess(successful);
                if (!t.isSuccess()) {
                    List<String> errorCodeInRequestList = t.getErrorCodeInRequestList();
                    if (CollectionUtils.isNotEmpty(errorCodeInRequestList)) {
                        String firstInfosourceErrorCode = errorCodeInRequestList.get(0);
                        if (StringUtils.startsWith(firstInfosourceErrorCode, "%IS@")) {
                            t.setErrorCode(ErrorCode.NETWORK_ERROR.getValue());
                        }
                        else if (StringUtils.startsWith(firstInfosourceErrorCode, "%IS#")) {
                            t.setErrorCode(ErrorCode.OUTER_INFOSOURCE_ERROR.getValue());
                        }
                        else if (StringUtils.startsWith(firstInfosourceErrorCode, "%IS~")) {
                            t.setErrorCode(ErrorCode.INPUT_ERROR.getValue());
                        }
                        else {
                            t.setErrorCode(ErrorCode.RUNTIME_ERROR.getValue());
                        }
                    }
                    else {
                        t.setErrorCode(ErrorCode.RUNTIME_ERROR.getValue());
                    }
                }
            }
        }
        finally {
            infosourceRequestInnerLog.setTotalElapsedTime(System.currentTimeMillis() - parentTotalTime);
            if (logBean != null) {
                logBean.getInner().add(infosourceRequestInnerLog);
            }
        }

        return t;
    }

    /**
     * request and parse process<br />
     * <p>
     * 
     * @author chen.chen.9, 2013-11-27
     * @param url
     *            url
     * @param subInfosource
     *            {@link SubInfosource}
     * @param repeated
     *            whether or not repeated if failed
     * @param subInfosourceRequestInnerLog
     *            {@link SubInfosourceRequestInnerLog}
     * @return response object
     * @throws InfosourceErrorException
     */
    protected Object doRequestAndParse(String url, SubInfosource subInfosource, boolean repeated, SubInfosourceRequestInnerLog subInfosourceRequestInnerLog)
            throws InfosourceErrorException {
        long time2 = System.currentTimeMillis();
        String response = null;
        try {
            response = doRequest(url, subInfosource.getTimeout(), repeated);
        }
        catch (IOException e) {
            throw new InfosourceErrorException(InfosourceErrorCode.REQUEST_IS_EXCEPTION);
        }
        // subInfosourceRequestInnerLog.setResponse(response);
        subInfosourceRequestInnerLog.setRequestElapsedTime(System.currentTimeMillis() - time2);

        long time3 = System.currentTimeMillis();
        validateBeforeParser(response);

        Class<?> subResponseClass = subInfosource.getResponseClass();
        Parser<Object> parser = (Parser<Object>) subInfosource.getParser();

        Object parseObject = null;
        try {
            parseObject = parser.parse((Class<Object>) subResponseClass, response, subInfosource.getResponseType());
        }
        catch (SecurityException e) {
            throw new InfosourceErrorException(InfosourceErrorCode.PARSER_EXCEPTION);
        }
        catch (IllegalArgumentException e) {
            throw new InfosourceErrorException(InfosourceErrorCode.PARSER_EXCEPTION);
        }
        catch (NoSuchFieldException e) {
            throw new InfosourceErrorException(InfosourceErrorCode.PARSER_EXCEPTION);
        }
        catch (IllegalAccessException e) {
            throw new InfosourceErrorException(InfosourceErrorCode.PARSER_EXCEPTION);
        }
        subInfosourceRequestInnerLog.setParsedResult(parseObject);
        subInfosourceRequestInnerLog.setParseElapsedTime(System.currentTimeMillis() - time3);

        return parseObject;
    }

    /**
     * request infosource
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param url
     *            url
     * @param timeout
     *            timeout
     * @param repeated
     *            whether or not repeated if failed
     * @return data
     * @throws IOException
     * @throws HttpException
     * @throws InfosourceErrorException
     */
    protected String doRequest(String url, int timeout, boolean repeated) throws HttpException, IOException, InfosourceErrorException {
        // load data from local file
        if (StringUtils.startsWith(url, "file://")) {
            int prefixLength = "file://".length();
            if (url.length() <= prefixLength) {
                throw new InfosourceErrorException(InfosourceErrorCode.RESPONSE_FILE_NULL_EXCEPTION);
            }

            InputStream inputStream = null;
            StringWriter writer = null;
            try {
                inputStream = new FileInputStream(StringUtils.substring(url, prefixLength));

                writer = new StringWriter();
                IOUtils.copy(inputStream, writer, obtainReadStreamCharset());
                String result = writer.toString();

                validateResponseBody(url, result);

                return result;
            }
            catch (FileNotFoundException e) {
                throw new InfosourceErrorException(InfosourceErrorCode.RESPONSE_FILE_NULL_EXCEPTION);
            }
            catch (IOException e) {
                throw new InfosourceErrorException(InfosourceErrorCode.RESPONSE_FILE_NULL_EXCEPTION);
            }
            finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (writer != null) {
                    writer.close();
                }
            }
        }

        // read data from remote
        HttpClient httpClient = HttpclientGenerator.generate();

        HttpMethodBase method = null;
        try {
            method = generateHttpMethod(url, timeout);

            int repeatCount = 0;
            // repeat twice
            int maxRepeatCount = repeated ? 2 : 0;
            while (repeatCount <= maxRepeatCount) {
                try {
                    int statusCode = httpClient.executeMethod(method);

                    if (statusCode >= 200 && statusCode < 300) {
                        InputStream inputStream = method.getResponseBodyAsStream();

                        StringWriter writer = new StringWriter();
                        IOUtils.copy(inputStream, writer, obtainReadStreamCharset());
                        String result = writer.toString();

                        try {
                            validateResponseBody(url, result);
                        }
                        catch (InfosourceErrorException e) {
                            if (repeatCount >= maxRepeatCount) {
                                throw e;
                            }
                            else {
                                continue;
                            }
                        }

                        return result;
                    }
                }
                catch (SocketTimeoutException e) {
                    if (repeatCount >= maxRepeatCount) {
                        throw new InfosourceErrorException(InfosourceErrorCode.REQUEST_IS_TIMEOUT_EXCEPTION);
                    }
                }
                catch (SocketException e) {
                    if (repeatCount >= maxRepeatCount) {
                        throw new InfosourceErrorException(InfosourceErrorCode.REQUEST_IS_TIMEOUT_EXCEPTION);
                    }
                }

                repeatCount++;
            }
        }
        finally {
            if (method != null) {
                method.releaseConnection();
            }
        }

        throw new InfosourceErrorException(InfosourceErrorCode.REQUEST_IS_EXCEPTION);
    }

    /**
     * {@link GetMethod} generator
     * <p>
     * 
     * @param url
     *            url
     * @param timeout
     *            timeout
     * @return {@link HttpMethodBase}
     * @throws InfosourceErrorException
     */
    protected HttpMethodBase generateHttpMethod(String url, int timeout) throws InfosourceErrorException {
        GetMethod getMethod = null;
        if (timeout <= 0) {
            getMethod = GetMethodGenerator.generate(url);
        }
        else {
            getMethod = GetMethodGenerator.generate(url, timeout);
        }

        httpHeadProcess(getMethod);

        return getMethod;
    }

    /**
     * default charset is UTF-8
     * <p>
     * 
     * @author chen.chen.9, 2013-10-25
     * @return charset
     */
    protected String obtainReadStreamCharset() {
        return UTF8;
    }

    /**
     * obtain class name to test
     * <p>
     * 
     * @author chen.chen.9, 2013-8-20
     * @return class name
     */
    protected String obtainClassName() {
        return getClass().getName();
    }

    /**
     * add header
     * <p>
     * 
     * @param getMethod
     *            {@link GetMethod}
     */
    protected void httpHeadProcess(GetMethod getMethod) {
        // empty
    }

    /**
     * post handler
     * <p>
     * 
     * @author chen.chen.9, 2013-10-22
     * @param requestObject
     *            {@link RequestObject}
     * @param responseObject
     *            {@link ResponseObject}
     */
    protected void postHandler(RequestObject requestObject, ResponseObject responseObject) {
    }

    /**
     * pre handler
     * <p>
     * 
     * @author chen.chen.9, 2013-10-30
     * @param requestObject
     *            {@link RequestObject}
     * @param subInfosourceName
     *            subinfosource name
     */
    protected void preHandler(RequestObject requestObject, String subInfosourceName) throws InfosourceErrorException {
    }

    /**
     * validate response body
     * <p>
     * 
     * @author chen.chen.9, 2013-9-24
     * @param url
     *            url
     * @param result
     *            data result
     */
    protected void validateResponseBody(String url, String result) throws InfosourceErrorException {
    }

    /**
     * validate after parsing
     * <p>
     * 
     * @author chen.chen.9, 2013-9-4
     * @param result
     *            data result
     */
    protected void validateAfterParser(Object result) throws InfosourceErrorException {
    }

    /**
     * validate before parsing
     * <p>
     * 
     * @param response
     *            data
     * @throws InfosourceErrorException
     */
    protected void validateBeforeParser(String response) throws InfosourceErrorException {
        if (StringUtils.isEmpty(response)) {
            throw new InfosourceErrorException(InfosourceErrorCode.UNVALID_RESPONSE_RESULT_BEFORE_PARSER_EXCEPTION);
        }
    }

    /**
     * get response instance
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @return T
     */
    protected abstract T getResponseInstance();

    /**
     * generate url
     * <p>
     * 
     * @author chen.chen.9, 2013-8-19
     * @param url
     *            original url
     * @param requestObject
     *            {@link RequestObject}
     * @return generated url
     */
    protected abstract String generateUrl(String url, RequestObject requestObject) throws UnsupportedEncodingException, InfosourceErrorException;

    /**
     * getter method
     * 
     * @see AbstractInfosourceRequest#LOGGER
     * @return the lOGGER
     */
    protected abstract Logger getLOGGER();

    /**
     * getter method
     * 
     * @see AbstractInfosourceRequest#logContainer
     * @return the logContainer
     */
    protected abstract LogContainer getLogContainer();
}
