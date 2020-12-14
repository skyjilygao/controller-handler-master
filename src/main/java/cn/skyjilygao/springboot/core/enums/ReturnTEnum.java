package cn.skyjilygao.springboot.core.enums;

/**
 * 返回消息枚举类: 此枚举类主要是对 Http Status 的补充，尽量出现重复。主要是相关业务状态码
 *
 * <br> 遵守公共<a href="https://tools.ietf.org/html/rfc7231">Http Status rfc7231</a>状态使用规范。
 * @author skyjilygao
 * @date 20201210
 * @since 1.8
 */
public enum ReturnTEnum {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务异常"),
    UNKNOWN_ERROR(5000, "未知错误"),

    PARAMETER_EMPTY(210,"参数为空"),
    PARAMETER_INVALID(211,"参数错误"),

    DATE_ERROR_FORMAT_ERROR(212,"日期格式错误"),
    DATE_ERROR_START_LARGE_END(213,"开始时间大于结束时间"),

    FORBIDDEN(403, "拒绝访问"),

    // todo: 更多自定义返回错误信息，均在此重新定义

    ;


    private int code;
    private String msg;


    @Override
    public String toString() {
        return super.toString();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ReturnTEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
