package cn.skyjilygao.springboot.core.exception;


import cn.skyjilygao.springboot.core.enums.HttpStatus;

/**
 * 自定义异常基础类
 * @author skyjilygao
 * @since 1.0
 */
public class SkyExceptionBase extends RuntimeException {

    /**
     * 状态码：用于接口返回状态code
     */
    protected int httpCode;
    /**
     * 状态信息：用户接口返回code说明
     */
    protected String httpMessage;

    public SkyExceptionBase(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        setHttpInfo(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public SkyExceptionBase(HttpStatus httpStatus, Exception e) {
        super(e);
        setHttpInfo(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public SkyExceptionBase(int httpCode, String httpMessage) {
        super(httpMessage);
        setHttpInfo(httpCode, httpMessage);
    }
    public SkyExceptionBase(int httpCode, String httpMessage, Exception e) {
        super(e);
        setHttpInfo(httpCode, httpMessage);
    }


    public int getHttpCode() {
        return httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    protected void setHttpInfo(int httpCode, String httpMessage) {
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
    }
}
