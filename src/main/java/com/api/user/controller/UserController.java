package com.api.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.SysUser;
import com.api.user.service.UserService;
import com.base.framework.controller.BaseController;

@RestController
@RequestMapping("UserController")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("getUser")
	public List<Map<String, Object>> getUser(String xxx) {
		List<Map<String, Object>> users = userService.getUser();
		return users;
	}
	
	@RequestMapping("findUser")
	public SysUser findUser(Long userId) {
		SysUser sysUser = userService.findUser(userId);
		return sysUser;
	}
	

}
