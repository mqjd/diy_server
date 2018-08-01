package com.api.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.user.service.UserService;
import com.base.framework.controller.BaseController;

@RestController
@RequestMapping("UserController")
public class UserController extends BaseController{
	
	@Autowired
	private UserService UserService;
	
	@RequestMapping("getUser")
	public List<Map<String, Object>> getUser(String xxx) {
		List<Map<String, Object>> users = UserService.getUser();
		return users;
	}

}
