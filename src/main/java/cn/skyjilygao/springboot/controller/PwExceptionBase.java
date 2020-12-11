package cn.skyjilygao.springboot.controller;


/**
 *
 */
public class PwExceptionBase extends RuntimeException {

    protected int httpCode;
    protected String httpMessage;

    public PwExceptionBase(HttpStatus httpStatus) {
        super(httpStatus.getReasonPhrase());
        setHttpInfo(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public PwExceptionBase(HttpStatus httpStatus, Exception e) {
        super(e);
        setHttpInfo(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public PwExceptionBase(int httpCode, String httpMessage) {
        super(httpMessage);
        setHttpInfo(httpCode, httpMessage);
    }
    public PwExceptionBase(int httpCode, String httpMessage, Exception e) {
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
