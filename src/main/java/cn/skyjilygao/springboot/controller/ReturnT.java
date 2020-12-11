package cn.skyjilygao.springboot.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "响应消息基础类")
public class ReturnT<T> implements Serializable {
    public static final long serialVersionUID = 42L;
    public static final int SUCCESS_CODE = 200;
    public static final int REDIRECT_CODE = 302;
    public static final int FAIL_CODE = 500;
    public static final String SUCCESS_MSG = "success";
    public static final String FAIL_MSG = "fail";
    public static final ReturnT<String> SUCCESS = new ReturnT((Object)null);
    public static final ReturnT<String> FAIL = new ReturnT(500, "System Error", "unknow error");

    @ApiModelProperty(value = "状态码,0表示成功 其他表示失败", required = true)
    private int code;
    @ApiModelProperty(value = "SUCCESS 响应消息", required = true)
    private String msg;
    @ApiModelProperty(value = "返回数据", required = true)
    private T content;

    public ReturnT() {
        this.code = 200;
        this.msg = "success";
    }

    public ReturnT(T content) {
        this.code = 200;
        this.content = content;
    }

    public ReturnT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnT(int code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }
}
