package com.base.framework.dao.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.base.framework.model.BaseModel;

public class SqlParameter extends MapSqlParameterSource {
	
	
	private final Map<String, Object> params = new LinkedHashMap<String, Object>();
	
	private BaseModel model;
	
	public SqlParameter() {
	}
	public <T extends BaseModel> SqlParameter(T model) {
		this.model = model;
	}
	
	public SqlParameter add(String paraName, Object paraValue) {
		params.put(paraName, paraValue);
		return this;
	}
	
	public SqlParameter remove(String paraName) {
		params.remove(paraName);
		return this;
	}

	@Override
	public boolean hasValue(String paramName) {
		boolean result = params.containsKey(paramName)
				||(model!=null&&model.metadata().hasProperty(paramName));
		return result;
	}

	@Override
	public Object getValue(String paramName) throws IllegalArgumentException {
		if (params.containsKey(paramName)) {
			return params.get(paramName);
		}else if (model != null) {
			return model.metadata().getColumnValue(paramName);
		}else {
			return null;
		}
	}

}
