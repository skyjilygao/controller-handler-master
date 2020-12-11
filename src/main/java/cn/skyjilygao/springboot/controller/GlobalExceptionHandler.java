package cn.skyjilygao.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认异常处理信息
 *
 * @author skyjilygao
 * @date 20200305
 * @since 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 默认异常处理页面
	 */
	public static final String DEFAUL_ERROR_VIEW = "error";

	/**
	 * PwException异常处理方法,返回异常请求路径和异常信息
	 */
	@ResponseBody
	@ExceptionHandler(value = {PwExceptionBase.class})
	public ReturnT handlerPwException(HttpServletRequest request, HttpServletResponse response, PwExceptionBase e) throws PwExceptionBase {
		String uri = request.getRequestURI();
		String queryStr = request.getQueryString();
		if (!ObjectUtils.isEmpty(queryStr)) {
			uri += "?" + queryStr;
		}
		log.error("handler Exception: request uri={}. with error message={}", uri, e.getMessage(), e);
		return new ReturnT(e.getHttpCode(), e.getHttpMessage(), uri);
	}
	/**
	 * 默认异常处理方法,返回异常请求路径和异常信息
	 */
	@ResponseBody
	@ExceptionHandler(value = {Throwable.class})
	public ReturnT defaulErrorHandler(HttpServletRequest request, HttpServletResponse response, Throwable e) throws Throwable {
		String uri = request.getRequestURI();
		String queryStr = request.getQueryString();
		if (!ObjectUtils.isEmpty(queryStr)) {
			uri += "?" + queryStr;
		}
		log.error("defaulErrorHandler catch exception, request uri=" + uri, e);
		String msg = e.getMessage();

		ReturnT r = new ReturnT(ReturnT.FAIL_CODE, "服务器内部异常", uri);
		return r;
	}


}
