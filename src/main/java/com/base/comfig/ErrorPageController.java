package com.base.comfig;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/error")
public class ErrorPageController implements ErrorController {

	@Override
	public String getErrorPath() {
		return null;
	}
	
	@ResponseBody
	@RequestMapping
    public Map<String, String> errorPage(HttpServletResponse response) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("code", response.getStatus()+"");
		result.put("message", "错误页面！");
		return result;
    }
	
}
