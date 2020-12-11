package cn.skyjilygao.springboot.controller;

import lombok.extern.slf4j.Slf4j;


/**
 * @Description: 基础BaseController
 * @date 2018/6/12/ 下午6:24:40
 * @since JDK1.8
 * @version 1.0.0
 */
@Slf4j
public abstract class BaseController {

	protected static final ReturnT SUCCESS = ReturnT.SUCCESS;
	protected static final String SUCCESS_MSG = ReturnT.SUCCESS_MSG;
	protected static final int SUCCESS_CODE = ReturnT.SUCCESS_CODE;
	protected static final int FAIL_CODE = ReturnT.FAIL_CODE;

	protected static final String requesterInfo = "Request From IP Address: ";


	protected <T> ReturnT<T> success(ReturnTEnum resultEnum) {
		return response(resultEnum);
	}

	protected <T> ReturnT<T> success(T content) {
		return response(SUCCESS_CODE, SUCCESS_MSG, content);
	}

	protected <T> ReturnT<T> success(ReturnTEnum resultEnum, T content) {
		return response(resultEnum, content);
	}

	protected <T> ReturnT<T> error(ReturnTEnum resultEnum) {
		return response(resultEnum.getCode(), resultEnum.getMsg(), null);
	}
	protected <T> ReturnT<T> error(ReturnTEnum resultEnum, Exception e) {
		throw new PwException(resultEnum, e);
	}

	protected <T> ReturnT<T> response(ReturnTEnum resultEnum) {
		return response(resultEnum.getCode(), resultEnum.getMsg(), null);
	}
	protected <T> ReturnT<T> response(ReturnTEnum resultEnum, T content) {
		return response(resultEnum.getCode(), resultEnum.getMsg(), content);
	}


	private <T> ReturnT<T> response(int code, String msg, T obj) {
		return new ReturnT<T>(code, msg, obj);
	}
}
