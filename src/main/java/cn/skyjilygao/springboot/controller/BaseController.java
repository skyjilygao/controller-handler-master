package cn.skyjilygao.springboot.controller;

import cn.skyjilygao.springboot.core.ReturnT;
import cn.skyjilygao.springboot.core.ReturnTResponse;
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

	protected <T> ReturnT<T> success(ReturnTResponse resultEnum) {
		return response(resultEnum);
	}

	protected <T> ReturnT<T> success(ReturnTResponse resultEnum, T content) {
		return response(resultEnum, content);
	}

	protected <T> ReturnT<T> error(ReturnTResponse resultEnum) {
		return response(resultEnum);
	}

	protected <T> ReturnT<T> error(ReturnTResponse resultEnum, Exception e) {
		throw new SkyException(resultEnum, e);
	}

	protected <T> ReturnT<T> response(ReturnTResponse resultEnum) {
		return response(resultEnum.getCode(), resultEnum.getMsg(), null);
	}

	protected <T> ReturnT<T> response(ReturnTResponse resultEnum, T content) {
		return response(resultEnum.getCode(), resultEnum.getMsg(), content);
	}

	private <T> ReturnT<T> response(int code, String msg, T obj) {
		return new ReturnT<T>(code, msg, obj);
	}
}
