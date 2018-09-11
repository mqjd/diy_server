package com.base.framework.model;

import java.util.List;

public class  SqlBuilder {
	
	@SuppressWarnings("unused")
	private BaseModel model;
	private ModelMetadata metadata;

	public <T extends BaseModel> SqlBuilder(T model) {
		this.model = model;
		this.metadata = model.metadata();
	}
	
	public String getInsertSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ").append(metadata.getTableName()).append(" (");
		StringBuilder valueSql = new StringBuilder();
		List<TableColumnDef> valueColumns = metadata.getValueNotNullColumns();
		for (int i = 0; i < valueColumns.size(); i++) {
			sql.append(valueColumns.get(i).getDefinition().name()).append(",");
			valueSql.append(":").append(valueColumns.get(i).getDefinition().name()).append(",");
		}
		sql = sql.deleteCharAt(sql.length() - 1);
		valueSql = valueSql.deleteCharAt(valueSql.length() - 1);
		sql.append(") VALUES (").append(valueSql).append(")");
		return sql.toString();
	}
	
	public String getDeleteSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ").append(metadata.getTableName()).append(" WHERE 1=1");
		List<TableColumnDef> valueColumns = metadata.getValueNotNullColumns();
		for (int i = 0; i < valueColumns.size(); i++) {
			sql.append(" AND ").append(valueColumns.get(i).getDefinition().name()).append("=:").append(valueColumns.get(i).getDefinition().name());
		}
		return sql.toString();
	}
	
	public String getDeleteById() {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM ").append(metadata.getTableName()).append(" WHERE 1=1")
		.append(" AND ").append(metadata.getPKColumnName()).append("=:").append(metadata.getPKColumnName());
		return sql.toString();
	}
	
	public String getUpdateSql() {
		StringBuilder sql = new StringBuilder();
		String pkName = metadata.getPKName();
		String pkColumnName = metadata.getPKColumnName();
		sql.append("UPDATE ").append(metadata.getTableName()).append(" SET ");
		List<TableColumnDef> columns = metadata.getValueNotNullColumns();
		for (int i = 0; i < columns.size(); i++) {
			TableColumnDef column = columns.get(i);
			if (pkName.equals(column.getName())) {
				continue;
			}
			sql.append(" ")
			.append(column.getDefinition().name()).append("=:")
			.append(column.getDefinition().name()).append(",");
		}
		sql = sql.deleteCharAt(sql.length() - 1);
		sql.append(" WHERE ").append(pkColumnName).append("=:").append(pkColumnName);
		return sql.toString();
	}
	
	public String getQuerySql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		List<TableColumnDef> columns = metadata.getAllColumns();
		for (int i = 0; i < columns.size(); i++) {
			sql.append(" ").append(columns.get(i).getDefinition().name()).append(",");
		}
		sql = sql.deleteCharAt(sql.length() - 1);
		sql.append(" FROM ").append(metadata.getTableName()).append(" WHERE 1=1 ");
		List<TableColumnDef> valueColumns = metadata.getValueNotNullColumns();
		for (int i = 0; i < valueColumns.size(); i++) {
			sql.append(" AND ").append(valueColumns.get(i).getDefinition().name()).append("=:").append(valueColumns.get(i).getDefinition().name());
		}
		return sql.toString();
	}
	
	public String getQueryByIdSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		List<TableColumnDef> columns = metadata.getAllColumns();
		for (int i = 0; i < columns.size(); i++) {
			sql.append(" ").append(columns.get(i).getDefinition().name()).append(",");
		}
		sql = sql.deleteCharAt(sql.length() - 1);
		sql.append(" FROM ").append(metadata.getTableName()).append(" WHERE 1=1 ");
		sql.append(" AND ").append(metadata.getPKColumnName()).append("=:").append(metadata.getPKColumnName());
		return sql.toString();
	}
	
}
