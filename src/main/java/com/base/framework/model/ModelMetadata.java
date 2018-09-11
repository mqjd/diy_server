package com.base.framework.model;

import java.util.List;

import com.base.framework.model.ModelUtil.COLUMN_FILTER;
import com.base.framework.model.ModelUtil.PK_TYPE;
import com.base.util.TextUtil;

public class ModelMetadata {

	private BaseModel baseModel;

	public <T extends BaseModel> ModelMetadata(T model) {
		this.baseModel = model;
	}
	
	public String getTableName() {
		return ModelUtil.getTableName(baseModel.getClass());
	}
	
	public String getPKName(){
		return ModelUtil.getPKName(baseModel.getClass(), PK_TYPE.NAME);
	}
	
	public String getPKColumnName(){
		return ModelUtil.getPKName(baseModel.getClass(), PK_TYPE.COLUMN_NAME);
	}
	
	public List<TableColumnDef> getAllColumns() {
		return ModelUtil.getColumns(baseModel, COLUMN_FILTER.ALL);
	}
	
	public List<TableColumnDef> getValueNotNullColumns() {
		return ModelUtil.getColumns(baseModel, COLUMN_FILTER.VALUE_NOT_NULL);
	}
	
	public Object getColumnValue(String columnName) {
		
		String column = getColumn(columnName);
		if (TextUtil.isNotEmpty(column)) {
			return ModelUtil.getFieldValue(column, baseModel);
		}else {
			return null;
		}
		
	}

	public List<String> getAllFields() {
		List<String> properties = ModelUtil.getProperties(baseModel.getClass());
		List<TableColumnDef> allColumns = getAllColumns();
		for (int i = 0; i < allColumns.size(); i++) {
			TableColumnDef columnDef = allColumns.get(i);
			properties.add(columnDef.getDefinition().name());
		}
		return properties;
	}
	
	public String getColumn(String columnName) {
		List<TableColumnDef> allColumns = getAllColumns();
		for (int i = 0; i < allColumns.size(); i++) {
			TableColumnDef columnDef = allColumns.get(i);
			if (columnDef.getDefinition().name().equals(columnName)||columnDef.getName().equals(columnName)) {
				return columnDef.getName();
			}
		}
		return null;
	}
	
	public boolean hasColumn(String columnName) {
		List<TableColumnDef> allColumns = getAllColumns();
		for (int i = 0; i < allColumns.size(); i++) {
			TableColumnDef columnDef = allColumns.get(i);
			if (columnDef.getDefinition().name().equals(columnName)) {
				return true;
			}
		}
		return false;
	}
	public boolean hasProperty(String propertyName) {
		boolean result = getAllFields().indexOf(propertyName)!=-1;
		return result;
	}
}
