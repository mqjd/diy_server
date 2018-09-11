package com.base.framework.exception;

import com.base.framework.exception.base.BaseException;
import com.base.framework.exception.base.BaseExceptionDesc;

public class BusinessException extends BaseException{
	
	public BusinessException(BaseExceptionDesc desc) {
		super(desc);
	}

	private static final long serialVersionUID = -8727784825143778443L;

	@Override
	public String getType() {
		return "business";
	}

}
