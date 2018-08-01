package com.api.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.framework.dao.BaseDao;

@Repository
public class UserDao extends BaseDao{

	public List<Map<String, Object>> getUser() {
		String sql = "select * from sys_user";
		List<Map<String, Object>> result = queryForList(sql);
		return result;
	}
}
