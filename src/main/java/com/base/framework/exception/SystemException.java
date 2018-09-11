package com.base.framework.exception;

import com.base.framework.exception.base.BaseException;
import com.base.framework.exception.base.BaseExceptionDesc;

public class SystemException extends BaseException{

	public SystemException(BaseExceptionDesc desc) {
		super(desc);
	}

	private static final long serialVersionUID = -6549416181649878716L;

	@Override
	public String getType() {
		return "system";
	}

}
