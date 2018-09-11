package com.api.diy_form.model;

import java.util.List;

import com.base.framework.model.BaseModel;

public class FieldModel extends BaseModel{

	private static final long serialVersionUID = -849399602405499678L;
	private Integer fieldId;
	private String fieldCode;
	private String text;
	private List<FieldDataModel> data;
	public Integer getFieldId() {
		return fieldId;
	}
	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<FieldDataModel> getData() {
		return data;
	}
	public void setData(List<FieldDataModel> data) {
		this.data = data;
	}
}
