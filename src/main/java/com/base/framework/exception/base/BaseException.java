package com.base.framework.exception.base;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException{

	private BaseExceptionDesc desc;
	
	public BaseException(BaseExceptionDesc desc) {
		super(desc.getMessage());
		this.desc = desc;
	}
	
	public Map<String, String> getSerializedData(){
		Map<String, String> data = new HashMap<>();
		data.put("type", getType());
		data.put("code", desc.getCode());
		data.put("message", desc.getMessage());
		return data;
	}
	public abstract String getType();
}
