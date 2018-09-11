package com.base.framework.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.base.framework.exception.BusinessException;
import com.base.framework.exception.SystemException;
import com.base.framework.exception.base.BaseExceptionDesc;

public class ModelUtil {
	
	public enum COLUMN_FILTER {
		ALL,
		VALUE_NOT_NULL,
	}
	public enum PK_TYPE {
		NAME,
		COLUMN_NAME,
	}
	

	public static <T extends BaseModel> String getTableName(Class<T> clz) {
		Table table = clz.getAnnotation(Table.class);
		if(table != null){
			return table.name();
		}else {
			throw new SystemException(new BaseExceptionDesc("001","实体解析表名异常："+clz.getName()));
		}
	}
	
	public static <T extends BaseModel> String getPKName(Class<T> clz, PK_TYPE type) {
		for(Method method : clz.getDeclaredMethods()){
			String methodName = method.getName();
			if(!methodName.startsWith("get")){
				continue;
			}
			String propertyName = getGetterName(methodName);
			try {
				if(method.isAnnotationPresent(Id.class) || clz.getDeclaredField(propertyName).isAnnotationPresent(Id.class)){
					if (type == PK_TYPE.NAME) {
						return propertyName;
					}else {
						Column column = method.getAnnotation(Column.class);
						return column.name();
					}
					
				}
			} catch (Exception e) {
				throw new BusinessException(new BaseExceptionDesc("1", "获取主键异常："+clz.getName()+"",e));
			}
		}
		throw new BusinessException(new BaseExceptionDesc("1", "该类没有主键："+clz.getName()+""));
	}
	
	public static String getGetterName(String getterName) {
		String propertyName = getterName.replaceFirst("get", "");
		return propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
	}
	public static <T extends BaseModel> List<String> getProperties(Class<T> entity) {
		Field[] fields = entity.getDeclaredFields();
		List<String> result = new ArrayList<>();
		for (int i = 0; i < fields.length; i++) {
			result.add(fields[i].getName());
		}
		return result;
	}
	public static <T extends BaseModel> List<TableColumnDef> getColumns(T entity, COLUMN_FILTER filter) {
		Class<? extends BaseModel> clz = entity.getClass();
		List<TableColumnDef> result = new ArrayList<TableColumnDef>();
		try {
			for(Method method : clz.getDeclaredMethods()){
				String methodName = method.getName();
				if(!methodName.startsWith("get")){
					continue;
				}
				String propertyName = getGetterName(methodName);
				Column colDef = null;
				
				if(method.isAnnotationPresent(Column.class) || method.isAnnotationPresent(JoinColumn.class)){
					colDef = method.getAnnotation(Column.class);
				}else if(clz.getDeclaredField(propertyName).isAnnotationPresent(Column.class) || clz.getDeclaredField(propertyName).isAnnotationPresent(JoinColumn.class)){
					colDef = clz.getDeclaredField(propertyName).getAnnotation(Column.class);
				}
				if (colDef != null) {
					if (filter == COLUMN_FILTER.ALL) {
						result.add(new TableColumnDef(propertyName,colDef));
					}else if (filter == COLUMN_FILTER.VALUE_NOT_NULL && isValueNotNull(propertyName, entity)) {
						result.add(new TableColumnDef(propertyName,colDef));
					}
				}
			}
		} catch (Exception e) {
			throw new BusinessException(new BaseExceptionDesc("1", "获取字段异常："+clz.getName()+"",e));
		}
		return result;
	}
	private static boolean isValueNotNull(String name, Object entity) {
		Object value = getFieldValue(name, entity);
		return value != null;
	}
	public static Object getFieldValue(String name, Object entity) {
		try {
			Class<?> clz = entity.getClass();
			Field field = clz.getDeclaredField(name);
			field.setAccessible(true);
			return field.get(entity);
		} catch (Exception e) {
			throw new BusinessException(new BaseExceptionDesc("1", "读取字段值'"+name+"'异常："+entity.getClass().getName()+"",e));
		}
	}
}
