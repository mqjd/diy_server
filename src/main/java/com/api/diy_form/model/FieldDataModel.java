package com.api.diy_form.model;

import com.base.framework.model.BaseModel;

public class FieldDataModel extends BaseModel{

	private static final long serialVersionUID = 495268942841522970L;

	private Integer dataId;
	private String text;
	private String value;
	
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
