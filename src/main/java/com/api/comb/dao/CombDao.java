package com.api.comb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.api.model.CombParam;

@Mapper
public interface CombDao {

	List<Map<String, Object>> queryComb(@Param("param") CombParam param);
	
}
