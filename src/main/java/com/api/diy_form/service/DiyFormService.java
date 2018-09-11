package com.api.diy_form.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.diy_form.dao.DiyFormDao;
import com.api.diy_form.model.FieldDataModel;
import com.api.diy_form.model.FieldModel;
import com.api.diy_form.model.FormModel;
import com.api.model.DiyFieldData;
import com.api.model.DiyForm;
import com.api.model.DiyFormField;
import com.base.framework.exception.BusinessException;
import com.base.framework.exception.base.BaseExceptionDesc;
import com.base.framework.service.BaseService;

@Service
public class DiyFormService extends BaseService{
	
	@Autowired
	private DiyFormDao diyFormDao;
	
	public List<DiyForm> getForm(DiyForm diyForm) {
		return diyFormDao.getForm(diyForm);
	}
	
	@Transactional
	public void saveForm(FormModel formModel) {
		boolean forceNewVersion = formModel.getSaveType() != 2;
		DiyForm diyForm = saveDiyForm(formModel);
		saveDiyFormField(diyForm, formModel, forceNewVersion);
		diyFormDao.deleteRemovedData(formModel);
	}
	

	private void saveDiyFormField(DiyForm diyForm, FormModel formModel, boolean forceNewVersion) {
		List<FieldModel> fields = formModel.getFields();
		if (fields != null) {
			for (int i = 0; i < fields.size(); i++) {
				FieldModel fieldModel = fields.get(i);
				DiyFormField formField = saveFormField(fieldModel,diyForm.getFormId(),forceNewVersion, i);
				saveFieldData(fieldModel, formField, forceNewVersion);
			}
		}
	}
	
	private void saveFieldData(FieldModel fieldModel, DiyFormField formField, boolean forceNewVersion) {
		List<FieldDataModel> data = fieldModel.getData();
		if (data != null) {
			for (int i = 0; i < data.size(); i++) {
				FieldDataModel fieldDataModel = data.get(i);
				saveFieldData(fieldDataModel, formField.getFieldId(), forceNewVersion, i);
			}
		}
	}
	
	private DiyFieldData saveFieldData(FieldDataModel fieldDataModel, Integer fieldId, 
			boolean forceNewVersion, int index) {
		DiyFieldData fieldData = new DiyFieldData();
		if (!forceNewVersion) {
			fieldData.setDataId(fieldDataModel.getDataId());
		}
		fieldData.setDisplayOrder(index);
		fieldData.setFieldId(fieldId);
		fieldData.setFieldText(fieldDataModel.getText());
		fieldData.setFieldValue(fieldDataModel.getValue());
		if (fieldData.getDataId() == null) {
			diyFormDao.insert(fieldData);
		}else {
			diyFormDao.update(fieldData);
		}
		return fieldData;
	}
	
	private DiyFormField saveFormField(FieldModel model, Integer formId, boolean forceNewVersion, int index) {
		DiyFormField field = new DiyFormField();
		field.setFormId(formId);
		if (!forceNewVersion) {
			field.setFieldId(model.getFieldId());
		}
		field.setDisplayOrder(index);
		field.setFieldCode(model.getFieldCode());
		field.setFieldText(model.getText());
		if (field.getFieldId() == null) {
			int fieldId = Integer.parseInt(diyFormDao.insert(field).toString());
			field.setFieldId(fieldId);
		}else {
			diyFormDao.update(field);
		}
		return field;
	}

	private DiyForm saveDiyForm(FormModel formModel) {
		DiyForm diyForm = new DiyForm();
		if (formModel.getSaveType() == 1) {
			Integer formVersion = formModel.getFormVersion();
			diyForm.setFormVersion(formVersion==null?1:formVersion+1);
		}else if (formModel.getSaveType() == 2) {
			diyForm.setFormId(formModel.getFormId());
			diyForm.setFormVersion(diyForm.getFormVersion());
		}else {
			diyForm.setFormVersion(1);
		}
		diyForm.setFormCode(formModel.getFormCode());
		diyForm.setFormName(formModel.getFormName());
		diyForm.setComments(formModel.getComments());
		diyForm.setFormContent(formModel.getFormContent());
		if (diyForm.getFormId() == null) {
			if (!diyFormDao.checkFormCode(diyForm.getFormCode())) {
				int formId = Integer.parseInt(diyFormDao.insert(diyForm).toString());
				diyForm.setFormId(formId);
			}else {
				throw new BusinessException(new BaseExceptionDesc("001", "表单编码已经存在!"));
			}
		}else {
			diyFormDao.update(diyForm);
		}
		return diyForm;
	}

	public FormModel getFormConfig(DiyForm diyForm) {
		diyForm = findById(diyForm);
		FormModel formModel = new FormModel();
		formModel.setComments(diyForm.getComments());
		formModel.setFormCode(diyForm.getFormCode());
		formModel.setFormName(diyForm.getFormName());
		formModel.setFormVersion(diyForm.getFormVersion());
		formModel.setFormId(diyForm.getFormId());
		formModel.setFormContent(diyForm.getFormContent());
		formModel.setFields(getFieldModel(formModel.getFormId()));
		return formModel;
	}
	
	private List<FieldModel> getFieldModel(Integer formId) {
		List<FieldModel> result = new ArrayList<>();
		List<DiyFormField> formFields = diyFormDao.getFormField(formId);
		for (int i = 0; i < formFields.size(); i++) {
			FieldModel fieldModel = new FieldModel();
			fieldModel.setFieldCode(formFields.get(i).getFieldCode());
			fieldModel.setFieldId(formFields.get(i).getFieldId());
			fieldModel.setText(formFields.get(i).getFieldText());
			fieldModel.setData(getFieldDataModel(fieldModel.getFieldId()));
			result.add(fieldModel);
		}
		return result;
	}
	
	private List<FieldDataModel> getFieldDataModel(Integer fieldId) {

		List<DiyFieldData> fieldDatas = diyFormDao.getFormFieldData(fieldId);
		if (fieldDatas.size() == 0) {
			return null;
		}
		List<FieldDataModel> dataModels = new ArrayList<>();
		for (int i = 0; i < fieldDatas.size(); i++) {
			DiyFieldData diyFieldData = fieldDatas.get(i);
			FieldDataModel dataModel = new FieldDataModel();
			dataModel.setDataId(diyFieldData.getDataId());
			dataModel.setText(diyFieldData.getFieldText());
			dataModel.setValue(diyFieldData.getFieldValue());
			dataModels.add(dataModel);
		}
		return dataModels;
	}

	public void deleteForm(Integer formId) {
		diyFormDao.deleteForm(formId);
	}
}
