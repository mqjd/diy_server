package com.base.framework.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.base.framework.dao.impl.SqlParameter;
import com.base.framework.model.BaseModel;

public interface IBaseDao {
	
	<T extends BaseModel> T findById(T entity);
	<T extends BaseModel> void update(T entity);
	<T extends BaseModel> void update(List<T> entitys);
	<T extends BaseModel> void insert(List<T> entitys);
	<T extends BaseModel> Serializable insert(T entity);
	<T extends BaseModel> void deleteById(List<T> entitys);
	<T extends BaseModel> void deleteById(T entity);
	<T extends BaseModel> List<T> query(T entity);
	List<Map<String, Object>> query(String sql);
	List<Map<String, Object>> query(String sql,SqlParameter parameter);
	<T> List<T> query(String sql,Class<T> clz);
	<T> List<T> query(String sql,SqlParameter parameter,Class<T> clz);
	<T> T queryForObject(String sql,SqlParameter parameter,Class<T> clz);
	Integer insert(String sql,SqlParameter parameter);
	Integer update(String sql,SqlParameter parameter);
	Integer delete(String sql,SqlParameter parameter);
	int[] batchUpdate(String sql,SqlParameter[] parameters);
	int[] batchInsert(String sql, SqlParameter[] parameters);
	int[] batchDelete(String sql, SqlParameter[] parameters);
	String queryForString(String sql, SqlParameter parameter);
	
}
