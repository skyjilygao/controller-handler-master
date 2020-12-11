package cn.skyjilygao.springboot.controller;

/**
 * @version 1.0.0
 * code为消息的编码8位：编码的方式:	第一二位为：10-90 服务  10=系统服务 (20=用户服务)  30=订单服务 ...等
 * 第三四位为：10-90 代表服务或者web站点的自身编码,先取整数数字例如(10)、20、30
 * 10 = system
 * 第五六位为：10-40 代表应用层的错误代码
 * 10 = dao
 * 20 = service
 * 30 = (controller)
 * 40 = component
 * 第七八位为：消息具体代码形式
 * 05 = 运行时错误
 * 01 = 空或者null
 * 其它为根据需要进行自定义
 * @Description: 订单消息枚举
 * @date 2018年6月30日 下午7:57:56
 * @since JDK1.8
 */

public enum ReturnTEnum {
    SUCCESS(200, "SUCCESS"),
    ERROR(-1, "服务异常"),
    UNKNOWN_ERROR(-100, "未知错误"),
    THERE_IS_NOT_DATA(-200, "暂无数据"),
    UNRECEIVED_PARAMETER(300,"参数为空"),
    ADD_DATA_ERROR(301,"数据添加失败"),
    DATE_FORMAT_ERROR(302,"日期格式错误"),
    START_LARGE_END_DATE_ERROR(303,"开始时间大于结束时间"),
    PARAMETER_ERROR(304,"传入参数异常"),
    IP_SOURCE_ERROR(305, "此IP地址不在白名单,无法访问"),
    PAGING_PARAM_NOT_LESS_THAN_OR_EQUAL_TO_ZERO(306,"分页参数不能小于等于零"),
    REQUEST_PARAMS_ERROR(-999, "接收到的请求参数异常"),
    ACTIVITY_IS_END(10101001, "xxxx"),
    
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
