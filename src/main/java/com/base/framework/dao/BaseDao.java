package com.base.framework.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class BaseDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    public <T> List<T> queryForList(String sql, Map<String, Object> param, Class<T> clz) {
    	List<T> result = jdbcTemplate.queryForList(sql, param, clz);
    	return result;
	}
    
    public List<Map<String, Object>> queryForList(String sql) {
    	List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, new HashMap<>());
    	return result;
	}
    
    public List<Map<String, Object>> queryForList(String sql, Map<String, Object> param) {
    	List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, param);
    	return result;
	}
    
    public List<Map<String, Object>> queryForList(String sql,Class<? extends BaseModel> param) {
    	SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(param);
    	List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, parameterSource);
    	return result;
	}
	
    public <T> List<T> queryForList(String sql, Class<? extends BaseModel> param, Class<T> clz) {
    	SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(param);
    	List<T> result = jdbcTemplate.queryForList(sql, parameterSource, clz);
    	return result;
	}
    
    public <T> T queryForObject(String sql, Class<? extends BaseModel> param, Class<T> clz) {
    	SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(param);
    	BeanPropertyRowMapper<T> beanPropertyRowMapper = new BeanPropertyRowMapper<T>(clz);
    	T object = jdbcTemplate.queryForObject(sql, parameterSource, beanPropertyRowMapper);
		return object;
	}
    
    public <T> T queryForObject(String sql, Map<String, Object> param, Class<T> clz) {
    	BeanPropertyRowMapper<T> beanPropertyRowMapper = new BeanPropertyRowMapper<T>(clz);
    	T object = jdbcTemplate.queryForObject(sql, param, beanPropertyRowMapper);
		return object;
	}
}
