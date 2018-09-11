package com.api.diy_form.model;

import java.util.List;

import com.base.framework.model.BaseModel;

public class FormModel extends BaseModel{

	private static final long serialVersionUID = 6029965777111688978L;
	
	private Integer formId;
	private String formCode;
	private String formName;
	private String comments;
	private Integer formVersion;
	private Integer saveType;
	private String formContent;
	private List<Integer> removedField;
	private List<Integer> removedData;
	private List<FieldModel> fields;

	public List<Integer> getRemovedField() {
		return removedField;
	}
	public void setRemovedField(List<Integer> removedField) {
		this.removedField = removedField;
	}
	public List<Integer> getRemovedData() {
		return removedData;
	}
	public void setRemovedData(List<Integer> removedData) {
		this.removedData = removedData;
	}
	public Integer getSaveType() {
		return saveType;
	}
	public void setSaveType(Integer saveType) {
		this.saveType = saveType;
	}
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	public String getFormCode() {
		return formCode;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getFormVersion() {
		return formVersion;
	}
	public void setFormVersion(Integer formVersion) {
		this.formVersion = formVersion;
	}
	public String getFormContent() {
		return formContent;
	}
	public void setFormContent(String formContent) {
		this.formContent = formContent;
	}
	public List<FieldModel> getFields() {
		return fields;
	}
	public void setFields(List<FieldModel> fields) {
		this.fields = fields;
	}
}
