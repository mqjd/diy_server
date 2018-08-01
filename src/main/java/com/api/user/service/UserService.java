package com.api.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.user.dao.UserDao;
import com.base.framework.service.BaseService;

@Service
public class UserService extends BaseService{
	
	@Autowired
	private UserDao userDao;

	public List<Map<String, Object>> getUser() {
		List<Map<String, Object>> users = userDao.getUser();
		return users;
	}
	
}
