package com.base.framework.model;

import javax.persistence.Column;

public class TableColumnDef {

	private String name;
	private Column definition;
	public TableColumnDef() {
	}
	public TableColumnDef(String name, Column definition) {
		this.name = name;
		this.definition = definition;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Column getDefinition() {
		return definition;
	}
	public void setDefinition(Column definition) {
		this.definition = definition;
	}
	@Override
	public String toString() {
		return "TableColumnDef [name=" + name + ", definition=" + definition + "]";
	}
	
}
