package cn.skyjilygao.springboot.controller;


import cn.skyjilygao.springboot.core.ReturnTResponse;
import cn.skyjilygao.springboot.core.exception.SkyExceptionBase;

/**
 * 自定义异常处理类。用于接口返回时可以指定异常枚举类。便于返回状态码管理
 * @author skyjilygao
 * @since 1.8
 */
public class SkyException extends SkyExceptionBase {

    public SkyException(ReturnTResponse httpStatus) {
        super(httpStatus.getCode(), httpStatus.getMsg());
    }

    public SkyException(ReturnTResponse httpStatus, Exception e) {
        super(httpStatus.getCode(), httpStatus.getMsg(), e);
    }
}
