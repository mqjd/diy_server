package com.api.sys_dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.api.model.SysDept;
import com.base.framework.service.BaseService;

@Service("SysDeptService")
public class SysDeptService extends BaseService{
	
	@Autowired
	@Qualifier("SysDeptDao")
	private SysDeptDao sysDeptDao;
	
	public List<SysDept> queryAll(SysDept sysDept) {
		return sysDeptDao.queryAll(sysDept);
	}

}
