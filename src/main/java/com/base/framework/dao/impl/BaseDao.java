package com.base.framework.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.base.framework.BaseComponent.BaseComponent;
import com.base.framework.dao.IBaseDao;
import com.base.framework.model.BaseModel;

public class BaseDao extends BaseComponent implements IBaseDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public <T extends BaseModel> T findById(T entity) {
		SqlParameterSource parameterSource= new SqlParameter(entity);
		@SuppressWarnings("unchecked")
		BeanPropertyRowMapper<T> beanPropertyRowMapper = (BeanPropertyRowMapper<T>) new BeanPropertyRowMapper<>(entity.getClass());
		String sql = entity.sqlBuilder().getQueryByIdSql();
		T result = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, beanPropertyRowMapper);
		return result;
	}

	@Override
	public <T extends BaseModel> void update(T entity) {
		SqlParameterSource parameterSource= new SqlParameter(entity);
		String sql = entity.sqlBuilder().getUpdateSql();
		namedParameterJdbcTemplate.update(sql, parameterSource);
	}

	@Override
	public <T extends BaseModel> Serializable insert(T entity) {
		SqlParameterSource parameterSource= new SqlParameter(entity);
		String sql = entity.sqlBuilder().getInsertSql();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder, new String[]{entity.metadata().getPKColumnName()});
		return getKey(keyHolder);
	}

	@Override
	public <T extends BaseModel> void deleteById(T entity) {
		SqlParameterSource parameterSource= new SqlParameter(entity);
		String sql = entity.sqlBuilder().getDeleteById();
		namedParameterJdbcTemplate.update(sql, parameterSource);
	}
	
	private Serializable getKey(KeyHolder keyHolder) {
		Map<String, Object> keyMap = keyHolder.getKeys();
		Serializable key = null;
		if(keyMap != null && keyMap.size() == 1){
			Iterator<Object> it = keyMap.values().iterator();
			if(it.hasNext()){
				Object keyValue = it.next();
				if(keyValue instanceof Serializable){
					key = (Serializable)keyValue;
				}
			}
		}
		return key;
	}

	@Override
	public List<Map<String, Object>> query(String sql) {
		List<Map<String, Object>> result = namedParameterJdbcTemplate.queryForList(sql, new HashMap<>());
		return result;
	}

	@Override
	public List<Map<String, Object>> query(String sql, SqlParameter parameter) {
		List<Map<String, Object>> result = namedParameterJdbcTemplate.queryForList(sql, parameter);
		return result;
	}

	@Override
	public <T> List<T> query(String sql, SqlParameter parameter, Class<T> clz) {
		BeanPropertyRowMapper<T> beanPropertyRowMapper = (BeanPropertyRowMapper<T>) new BeanPropertyRowMapper<>(clz);
		List<T> result = namedParameterJdbcTemplate.query(sql, parameter, beanPropertyRowMapper);
		return result;
	}

	@Override
	public <T> T queryForObject(String sql, SqlParameter parameter, Class<T> clz) {
		BeanPropertyRowMapper<T> beanPropertyRowMapper = (BeanPropertyRowMapper<T>) new BeanPropertyRowMapper<>(clz);
		T result = namedParameterJdbcTemplate.queryForObject(sql, parameter, beanPropertyRowMapper);
		return result;
	}
	
	@Override
	public String queryForString(String sql, SqlParameter parameter) {
		String result = "";
		List<Map<String, Object>> data = query(sql,parameter);
		if (data != null && data.size() > 0) {
			result = data.get(0).entrySet().iterator().next().getValue()+"";
		}
		return result;
	}

	@Override
	public Integer insert(String sql, SqlParameter parameter) {
		int result = namedParameterJdbcTemplate.update(sql, parameter);
		return result;
	}

	@Override
	public Integer update(String sql, SqlParameter parameter) {
		int result = namedParameterJdbcTemplate.update(sql, parameter);
		return result;
	}

	@Override
	public Integer delete(String sql, SqlParameter parameter) {
		int result = namedParameterJdbcTemplate.update(sql, parameter);
		return result;
	}

	@Override
	public <T extends BaseModel> List<T> query(T entity) {
		SqlParameterSource sqlParameter= new SqlParameter(entity);
		@SuppressWarnings("unchecked")
		BeanPropertyRowMapper<T> beanPropertyRowMapper = (BeanPropertyRowMapper<T>) new BeanPropertyRowMapper<>(entity.getClass());
		String sql = entity.sqlBuilder().getQuerySql();
		List<T> result = namedParameterJdbcTemplate.query(sql, sqlParameter, beanPropertyRowMapper);
		return result;
	}

	@Override
	public <T extends BaseModel> void update(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			update(entitys.get(i));
		}
	}

	@Override
	public <T extends BaseModel> void insert(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			insert(entitys.get(i));
		}
	}

	@Override
	public <T extends BaseModel> void deleteById(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			deleteById(entitys.get(i));
		}
	}

	@Override
	public int[] batchUpdate(String sql, SqlParameter[] parameters) {
		int[] result = namedParameterJdbcTemplate.batchUpdate(sql, parameters);
		return result;
	}
	@Override
	public int[] batchInsert(String sql, SqlParameter[] parameters) {
		int[] result = namedParameterJdbcTemplate.batchUpdate(sql, parameters);
		return result;
	}
	@Override
	public int[] batchDelete(String sql, SqlParameter[] parameters) {
		int[] result = namedParameterJdbcTemplate.batchUpdate(sql, parameters);
		return result;
	}

	@Override
	public <T> List<T> query(String sql, Class<T> clz) {
		BeanPropertyRowMapper<T> beanPropertyRowMapper = (BeanPropertyRowMapper<T>) new BeanPropertyRowMapper<>(clz);
		List<T> result = namedParameterJdbcTemplate.query(sql, new HashMap<>(), beanPropertyRowMapper);
		return result;
	}
	
}
