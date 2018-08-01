package com.api.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.SysUser;
import com.api.user.dao.UserDao;
import com.api.user.dao.UserMapper;
import com.base.framework.service.BaseService;

@Service
public class UserService extends BaseService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private UserMapper userMapper;

	public List<Map<String, Object>> getUser() {
		List<Map<String, Object>> users = userDao.getUser();
		return users;
	}
	
	public SysUser findUser(Long userId) {
		SysUser sysUser = userMapper.selectByPrimaryKey(userId);
		return sysUser; 
	}
	
}
