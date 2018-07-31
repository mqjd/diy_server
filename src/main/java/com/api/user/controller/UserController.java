package com.api.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("UserController")
public class UserController {
	
	@RequestMapping("getUser")
	public String getUser(String xxx) {
		String substring = xxx.substring(2);
		return substring;
	}

}
