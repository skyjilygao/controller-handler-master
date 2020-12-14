package cn.skyjilygao.springboot.core.interceptor;

import cn.skyjilygao.springboot.core.ReturnT;
import cn.skyjilygao.springboot.core.enums.ReturnTEnum;
import cn.skyjilygao.springboot.core.exception.SkyExceptionBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 *
 * @author skyjilygao
 * @date 20200305
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


	/**
	 * 自定义异常处理方法,返回异常请求路径和异常信息
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @param e {@link SkyExceptionBase} 自定义异常基础类
	 * @return {code: 状态吗, msg: 状态信息, content: 请求的url}
	 * @throws SkyExceptionBase
	 */
	@ResponseBody
	@ExceptionHandler(value = {SkyExceptionBase.class})
	public ReturnT handlerCustomerException(HttpServletRequest request, HttpServletResponse response, SkyExceptionBase e) throws SkyExceptionBase {
		String uri = getUri(request);
		log.error("handler Customer Exception: request uri={}. with error message={}", uri, e.getMessage(), e);
		return new ReturnT(e.getHttpCode(), e.getHttpMessage(), uri);
	}

	/**
	 * 默认异常处理方法,返回异常请求路径和异常信息
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @param e
	 * @return
	 * @throws Throwable
	 */
	@ResponseBody
	@ExceptionHandler(value = {Exception.class})
	public ReturnT defaulExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		String uri = getUri(request);
		log.error("handler Exception: request uri={}. with error message={}", uri, e.getMessage(), e);
		return new ReturnT(ReturnTEnum.ERROR.getCode(), "服务器内部异常", uri);
	}


	/**
	 * 默认异常处理方法,返回异常请求路径和异常信息
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @param e
	 * @return
	 * @throws Error
	 */
	@ResponseBody
	@ExceptionHandler(value = {Error.class})
	public ReturnT defaulErrorHandler(HttpServletRequest request, HttpServletResponse response, Error e) throws Error {
		String uri = getUri(request);
		log.error("handler Error: request uri={}. with error message={}", uri, e.getMessage(), e);
		return new ReturnT(ReturnT.FAIL_CODE, "服务器内部错误", uri);
	}

	/**
	 * 只针对get请求或post请求url携带参数。至于post时的post-data中参数暂时不支持
	 * @param request
	 * @return
	 */
	private String getUri(HttpServletRequest request){
		String uri = request.getRequestURI();
		// 请求参数
		String queryStr = request.getQueryString();
		if (!ObjectUtils.isEmpty(queryStr)) {
			uri += "?" + queryStr;
		}
		return uri;
	}
}
