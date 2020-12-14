package cn.skyjilygao.springboot.controller;

import cn.skyjilygao.springboot.core.enums.HttpStatus;
import cn.skyjilygao.springboot.core.enums.ReturnTEnum;
import cn.skyjilygao.springboot.core.exception.SkyExceptionBase;

/**
 * 自定义异常处理类。用于接口返回时可以指定异常枚举类。便于返回状态码管理
 * @author skyjilygao
 * @since 1.8
 */
public class SkyException extends SkyExceptionBase {

    public SkyException(ReturnTEnum httpStatus) {
        super(httpStatus.getCode(), httpStatus.getMsg());
    }

    public SkyException(ReturnTEnum httpStatus, Exception e) {
        super(httpStatus.getCode(), httpStatus.getMsg(), e);
    }

    public SkyException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public SkyException(HttpStatus httpStatus, Exception e) {
        super(httpStatus, e);
    }
}
