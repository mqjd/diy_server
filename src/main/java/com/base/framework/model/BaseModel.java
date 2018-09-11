package com.base.framework.model;

import java.io.Serializable;

import javax.persistence.Transient;

public class BaseModel implements Serializable{
	
	private static final long serialVersionUID = 4488293584962300985L;

	@Transient
	public SqlBuilder sqlBuilder() {
		return new SqlBuilder(this);
	}
	
	@Transient
	public ModelMetadata metadata() {
		return new ModelMetadata(this);
	}
	
}
