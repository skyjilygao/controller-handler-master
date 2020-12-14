package cn.skyjilygao.springboot.controller;

import cn.skyjilygao.springboot.core.ReturnT;
import cn.skyjilygao.springboot.core.enums.HttpStatus;
import lombok.extern.slf4j.Slf4j;


/**
 * 接口controller基础类
 * @author skyjilygao
 * @date 20201210
 * @since 1.8
 * @version 1.0
 */
@Slf4j
public abstract class BaseController {

	protected static final ReturnT SUCCESS = ReturnT.SUCCESS;
	protected static final String SUCCESS_MSG = ReturnT.SUCCESS_MSG;
	protected static final int SUCCESS_CODE = ReturnT.SUCCESS_CODE;
	protected static final int FAIL_CODE = ReturnT.FAIL_CODE;

	protected <T> ReturnT<T> success(T content) {
		return response(ReturnTEnum.SUCCESS, content);
	}

	protected <T> ReturnT<T> success(ReturnTEnum resultEnum) {
		return response(resultEnum);
	}

	protected <T> ReturnT<T> success(ReturnTEnum resultEnum, T content) {
		return response(resultEnum, content);
	}


	protected <T> ReturnT<T> success(HttpStatus status) {
		return response(status);
	}

	protected <T> ReturnT<T> success(HttpStatus status, T content) {
		return response(status, content);
	}

	protected <T> ReturnT<T> error(ReturnTEnum resultEnum) {
		return response(resultEnum);
	}

	protected <T> ReturnT<T> error(ReturnTEnum resultEnum, Exception e) {
		throw new SkyException(resultEnum, e);
	}

	protected <T> ReturnT<T> error(HttpStatus status) {
		return response(status);
	}
	protected <T> ReturnT<T> error(HttpStatus status, Exception e) {
		throw new SkyException(status, e);
	}

	protected <T> ReturnT<T> response(ReturnTEnum resultEnum) {
		return response(resultEnum.getCode(), resultEnum.getMsg(), null);
	}

	protected <T> ReturnT<T> response(ReturnTEnum resultEnum, T content) {
		return response(resultEnum.getCode(), resultEnum.getMsg(), content);
	}

	protected <T> ReturnT<T> response(HttpStatus status) {
		return response(status.value(), status.getReasonPhrase(), null);
	}

	protected <T> ReturnT<T> response(HttpStatus status, T content) {
		return response(status.value(), status.getReasonPhrase(), content);
	}


	private <T> ReturnT<T> response(int code, String msg, T obj) {
		return new ReturnT<T>(code, msg, obj);
	}
}
