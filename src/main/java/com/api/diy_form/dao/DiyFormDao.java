package com.api.diy_form.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.diy_form.model.FormModel;
import com.api.model.DiyFieldData;
import com.api.model.DiyForm;
import com.api.model.DiyFormField;
import com.base.framework.dao.impl.BaseDao;
import com.base.framework.dao.impl.SqlParameter;

@Repository
public class DiyFormDao extends BaseDao{

	public List<DiyForm> getForm(DiyForm diyForm) {
		String sql = 	"SELECT\n" +
						"	DF.FORM_ID,\n" +
						"	DF.FORM_CODE,\n" +
						"	DF.FORM_VERSION,\n" +
						"	DF.FORM_NAME,\n" +
						"	DF.COMMENTS\n" +
						"FROM\n" +
						"	DIY_FORM DF\n" +
						"ORDER BY\n" +
						"	DF.FORM_CODE,\n" +
						"	DF.FORM_VERSION";
		List<DiyForm> result = query(sql, DiyForm.class);
		return result;
	}
	
	public boolean checkFormCode(String formCode) {
		String sql = 	"SELECT\n" +
						"	1\n" +
						"FROM\n" +
						"	DUAL\n" +
						"WHERE\n" +
						"	EXISTS (\n" +
						"		SELECT\n" +
						"			1\n" +
						"		FROM\n" +
						"			DIY_FORM\n" +
						"		WHERE\n" +
						"			FORM_CODE = :FORM_CODE\n" +
						"	)";
		String result = queryForString(sql, new SqlParameter().add("FORM_CODE", formCode));
		return "1".equals(result);
	}
	
	public List<DiyFormField> getFormField(Integer formId) {
		String sql = 	"SELECT\n" +
						"	FF.FIELD_ID,\n" +
						"	FF.FIELD_CODE,\n" +
						"	FF.FIELD_TEXT\n" +
						"FROM\n" +
						"	DIY_FORM_FIELD FF\n" +
						"WHERE\n" +
						"	FF.FORM_ID = :FORM_ID\n" +
						"ORDER BY\n" +
						"	FF.DISPLAY_ORDER";
		List<DiyFormField> result = query(sql, new SqlParameter().add("FORM_ID", formId),DiyFormField.class);
		return result;
	}
	
	public List<DiyFieldData> getFormFieldData(Integer fieldId) {
		String sql = 	"SELECT\n" +
						"	FD.DATA_ID,\n" +
						"	FD.FIELD_TEXT,\n" +
						"	FD.FIELD_VALUE\n" +
						"FROM\n" +
						"	DIY_FIELD_DATA FD\n" +
						"WHERE\n" +
						"	FD.FIELD_ID = :FIELD_ID\n" +
						"ORDER BY\n" +
						"	FD.DISPLAY_ORDER";
		List<DiyFieldData> result = query(sql, new SqlParameter().add("FIELD_ID", fieldId),DiyFieldData.class);
		return result;
	}
	
	public void deleteRemovedData(FormModel formModel) {
		String sql = null;
		SqlParameter sqlParameter = new SqlParameter();
		if (formModel.getRemovedField() != null && formModel.getRemovedField().size()>0) {
			sql = "DELETE FROM DIY_FORM_FIELD WHERE FIELD_ID IN (:FIELD_ID)";
			sqlParameter.add("FIELD_ID", formModel.getRemovedField());
			delete(sql, sqlParameter);
		}
		if (formModel.getRemovedData() != null && formModel.getRemovedData().size()>0) {
			sql = "DELETE FROM DIY_FIELD_DATA WHERE DATA_ID IN (:DATA_ID)";
			sqlParameter.add("DATA_ID", formModel.getRemovedData());
			delete(sql, sqlParameter);
		}
		
	}

	public void deleteForm(Integer formId) {
		String sql = 	"DELETE\n" +
						"FROM\n" +
						"	DIY_FIELD_DATA\n" +
						"WHERE\n" +
						"	EXISTS (\n" +
						"		SELECT\n" +
						"			1\n" +
						"		FROM\n" +
						"			DIY_FORM_FIELD DFF\n" +
						"		WHERE\n" +
						"			DFF.FORM_ID = :FORM_ID\n" +
						"		AND DIY_FIELD_DATA.FIELD_ID = DFF.FIELD_ID\n" +
						"	)";
		SqlParameter sqlParameter = new SqlParameter().add("FORM_ID", formId);
		delete(sql, sqlParameter);
		sql = 	"DELETE\n" +
				"FROM\n" +
				"	DIY_FORM\n" +
				"WHERE\n" +
				"	FORM_ID = :FORM_ID";
		delete(sql, sqlParameter);
		
		sql = 	"DELETE\n" +
				"FROM\n" +
				"	DIY_FORM_FIELD\n" +
				"WHERE\n" +
				"	FORM_ID = :FORM_ID";
		delete(sql, sqlParameter);
	}
	
}
