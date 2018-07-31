package com.api.user_group.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.framework.controller.BaseController;

@RestController
@RequestMapping("UserGroupController")
public class UserGroupController extends BaseController{

	@RequestMapping("xxx")
	public String xxx() {
		logger.info("xxxxxxxxxxxxxxxx");
		return "xxx";
	}
	
}
