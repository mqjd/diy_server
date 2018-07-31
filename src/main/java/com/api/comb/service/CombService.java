package com.api.comb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.comb.dao.CombDao;
import com.api.domain.CombParam;

@Service
public class CombService {

	@Autowired
	private CombDao combDao;
	
	public List<Map<String, Object>> queryComb(CombParam param) {
		return combDao.queryComb(param);

	}
	
}
