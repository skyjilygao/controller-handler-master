## Controller响应数据统一管理

较`1.0`版，此版本增加枚举接口`ReturnTResponse`。2个状态（`ReturnTEnum`,`HttpStatus`）枚举类实现枚举接口`ReturnTResponse`,使用时统一调用`getCode()`,`getMsg()`方法即可。无需单独处理各自的get方法。如果增加其他返回枚举类，实现该接口即可

### 1. 返回数据统一：

所有controller都继承`BaseController`类

- 返回正常数据：`return success("hello world");`
- 返回错误数据：`return error(ReturnTEnum.ARITHMETIC_ERROR, e);` 或 `throw new SkyException(ReturnTEnum.ARITHMETIC_ERROR, e);`即可

```json
{
    "code": 200,
    "msg": "SUCCESS",
    "content": "sdf"
}
```

或

```json
{
    "code": 500,
    "msg": "服务器内部异常",
    "content": "/test/err"
}
```

其中

| 字段    | 类型                | 说明     |
| :------ | :------------------ | :------- |
| code    | int                 | 状态码   |
| msg     | String              | 状态信息 |
| content | 泛型T。允许序列化类 | 业务数据 |

### 2. 全局异常处理

- 使用`@ControllerAdvice`和`@ExceptionHandler` 注解处理全局异常
- 参考`cn.skyjilygao.springboot.core.interceptor.GlobalExceptionHandler`类

为了能够更好统一返回状态信息，捕获异常需要同时状态码和状态信息，怎么办？使用自定义异常类`SkyException`

-1. 增加了枚举类`cn.skyjilygao.springboot.controller.ReturnTEnum`也可以使用`HttpStatus`.

-2. 异常类`SkyException`

```java
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
```

-3. 对于可控异常，可以抛出`SkyException`同时指定相应枚举类即可
例如：对于计算，可能抛出被除数不能为0，则可以捕获以下。枚举类就是`ReturnTEnum.ARITHMETIC_ERROR`

```java
@GetMapping("err")
public ReturnT err(){
    try {
        int a = 2;
        int b = 0;
        return success(a/b);
    }catch (Exception e){
        throw new SkyException(ReturnTEnum.ARITHMETIC_ERROR, e);
        // return error(ReturnTEnum.ARITHMETIC_ERROR, e);
        // throw new SkyException(HttpStatus.BAD_GATEWAY, e);
    }
}
```

-4. 对于不可控异常，可以在全局异常处理类中指定默认状态码。

```java
   @ResponseBody
   @ExceptionHandler(value = {Exception.class})
   public ReturnT defaulExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
      String uri = getUri(request);
      log.error("handler Exception: request uri={}. with error message={}", uri, e.getMessage(), e);
      return new ReturnT(ReturnTEnum.ERROR.getCode(), ReturnTEnum.ERROR.getMsg(), uri);
   }
```

### 3. 记录调用者IP以及请求参数信息

利用拦截器记录IP和请求相关数据。这样跟其他配合时，就能清楚看到参数等数据不至于相互扯皮

```java
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
    ServletRequestPojo pojo = new ServletRequestPojo(request);
    // 记录请求信息，可以最终数据。注意：这里只能针对get请求，post请求无效。
    log.info("request info=" + pojo.toString());
    String ip = RequestUtil.getIPAddress(request);
    // ip控制访问
    if(!RequestUtil.isAllow(ip)){
        log.info("error_code: 2550, ip=" + ip + ",不再白名单，无法访问");
        throw new SkyException(HttpStatus.FORBIDDEN, new Exception("ip=" + ip + ",不再白名单，无法访问"));
    }
    return true;
}
```



### 4. 日志记录

使用`logback`记录，保留最近3天的日志

`<property name="MAX_HISTORY" value="3"/>`

## 如何移至到自己的项目中

1. 将`cn.skyjilygao.springboot.core`下所有文件复制到自己项目公共模块或第三方库中，注意相互应用包。重新编译安装
2. 将`ReturnTEnum`,`BaseController`,`SkyException`复制到项目的公共文件夹中（注意导入包路径）。然后在`ReturnTEnum`增加自定义枚举即可

## 如何使用

1. 使用本项目测试时，只需在`ReturnTEnum`增加自定义枚举即可





###### 参考

1. [Spring Boot 无侵入式 实现API接口统一JSON格式返回](https://mp.weixin.qq.com/s?__biz=MzI4OTA3NDQ0Nw==&mid=2455549015&idx=2&sn=44fa3621a8df3bd88e8c7064f1832105&chksm=fb9ca837cceb2121b9c15064f0a5f6c87f610c4b19c030cf385e0b297abe91cd177895c9c0fa&scene=126&sessionid=1600250050&key=cf1b11cd59da7ce7d9f9d510eb01a11d336a0136e7a8daea4a88ce757d5d899300b20616201de2a8a81284c4de25ea9a694a218fd06da8b4a0f3be80f7e34e06c63adefb7ffb7124ff8cc0e4833f8bbfbde4da7120c547c133d899108caf4876dd6c5d6d379884c956fcf7d5a503af6a97513175a8d5fb4a3421f55d51d41a0e&ascene=14&uin=OTMyNDYxMzQw&devicetype=Windows+10+x64&version=6300002f&lang=zh_CN&exportkey=AUOK0L%2F5IlqEYWRwGtxG%2FwI%3D&pass_ticket=fEs1HHbuT5JrkDds5Mn8cg1NzokPvax7ngMP5blOa%2FDonl2n0B0odFiegTD8NGcg&wx_header=0)
2. [你怎么天天就会 try...catch](https://mp.weixin.qq.com/s?__biz=MzIwNTc4NTEwOQ==&mid=2247489220&idx=1&sn=0a319bb3e8068faed40acfb44acf5c1a&chksm=972ac7bea05d4ea81eb70d99a833a0c4e7e5f472e6c207b806cd5f54241cdb22391ac96f72cc&mpshare=1&scene=24&srcid=0906fBfKSyqI31XKxOUn70sf&sharer_sharetime=1599395214798&sharer_shareid=9757876af3972f7c5c9a5047c8015bf9&key=a7cd04bfef4edfa33f8a9b340ea5f3f1833ac28825eddfc7da8404e31d4b7f7320cbea1decea01437f15d0eb79026c4be9152f558989f6cbfee072fe9af2d1148202a81fa93b2144e3d45b717365e95a102fdf841aa2ebf39ae1fafa77f279eed72ef517b941eda8cdcda9e54dd816f097153f9fe73074089d380efcbbe17de3&ascene=14&uin=OTMyNDYxMzQw&devicetype=Windows+10+x64&version=6300002f&lang=zh_CN&exportkey=AWjTx9TPD6QMRvx1G1QW%2BXA%3D&pass_ticket=fEs1HHbuT5JrkDds5Mn8cg1NzokPvax7ngMP5blOa%2FDonl2n0B0odFiegTD8NGcg&wx_header=0)