package com.base.framework.exception.base;

import com.base.util.TextUtil;

public class BaseExceptionDesc {
	private String code;
	private String message;
	private String _message;
	private Throwable throwable;
	
	public BaseExceptionDesc(String code, String message){
		this.code = code;
		this.message = message;
		if (TextUtil.isEmpty(message)) {
			this.message = _message;
		}
	}
	
	public BaseExceptionDesc(String code, Throwable throwable){
		this.code = code;
		this.throwable = throwable;
	}
	
	public BaseExceptionDesc(String code, String message, Throwable throwable){
		this.code = code;
		this.message = message;
		this.throwable = throwable;
		if (TextUtil.isEmpty(message)) {
			this.message = _message;
		}
	}
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
}
