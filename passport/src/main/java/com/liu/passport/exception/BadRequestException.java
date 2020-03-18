package com.liu.passport.exception;

import java.util.Map;

public class BadRequestException extends RuntimeException {

	/**
	 * 错误消息中的变量参数
	 */
	private Map<String, String> params;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String code) {
		super(code);
	}

	public BadRequestException(String code, Map<String, String> params) {
		super(code);
		this.params = params;
	}

	public BadRequestException(String code, Throwable cause) {
		super(code, cause);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
