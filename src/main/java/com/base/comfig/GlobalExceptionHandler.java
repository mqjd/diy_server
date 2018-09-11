package com.base.comfig;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.framework.exception.base.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
    public Map<String, String> jsonErrorHandler(HttpServletResponse response, Exception e) throws Exception {
		Map<String, String> result = null;
		if (e instanceof BaseException) {
			BaseException exception = (BaseException) e;
			result = exception.getSerializedData();
		}else {
			result = new HashMap<>();
			result.put("code", "001");
			result.put("message", "系统错误，请联系管理员！");
		}
		response.setStatus(500);
		return result;
    }
}
