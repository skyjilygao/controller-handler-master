package cn.skyjilygao.springboot.core;

import cn.skyjilygao.springboot.core.enums.HttpStatus;
import cn.skyjilygao.springboot.core.enums.ReturnTEnum;
import lombok.Getter;

import java.io.Serializable;

/**
 * 请求api响应消息类
 * @author skyjilygao
 * @date 20201210
 * @since 1.8
 *
 */
@Getter
public class ReturnT<T> implements Serializable {
    public static final long serialVersionUID = 42L;
    public static final int SUCCESS_CODE = HttpStatus.OK.value();
    public static final String SUCCESS_MSG = "success";
    public static final int REDIRECT_CODE = HttpStatus.FOUND.value();
    public static final int FAIL_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();
    public static final String FAIL_MSG = "fail";
    public static final ReturnT<String> SUCCESS = new ReturnT((Object)null);
    public static final ReturnT<String> FAIL = new ReturnT(FAIL_CODE, "System Error", "unknow error");

    /**
     * 状态码
     */
    private int code;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T content;

    public ReturnT() {
        this.code = SUCCESS_CODE;
        this.msg = SUCCESS_MSG;
    }

    public ReturnT(T content) {
        this();
        this.content = content;
    }

    public ReturnT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnT(ReturnTEnum tEnum) {
        this.code = tEnum.getCode();
        this.msg = tEnum.getMsg();
    }

    public ReturnT(ReturnTEnum tEnum, T content) {
        this(tEnum);
        setContent(content);
    }

    public ReturnT(HttpStatus status) {
        this.code = status.value();
        this.msg = status.getReasonPhrase();
    }

    public ReturnT(HttpStatus status, T content) {
        this(status);
        setContent(content);
    }

    private void setContent(T content) {
        this.content = content;
    }

    public ReturnT(int code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }
}
