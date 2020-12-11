package cn.skyjilygao.springboot.controller;

public class PwException extends PwExceptionBase {

    public PwException(ReturnTEnum httpStatus) {
        super(httpStatus.getCode(), httpStatus.getMsg());
    }

    public PwException(ReturnTEnum httpStatus, Exception e) {
        super(httpStatus.getCode(), httpStatus.getMsg(), e);
    }

    public PwException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public PwException(HttpStatus httpStatus, Exception e) {
        super(httpStatus, e);
    }
}
