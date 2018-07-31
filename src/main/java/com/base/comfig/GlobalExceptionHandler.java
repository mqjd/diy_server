package com.base.comfig;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
    public Map<String, String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		result.put("data", "");
		result.put("code", "001");
		result.put("message", "系统错误，请联系管理员！");
		return result;
    }
}
