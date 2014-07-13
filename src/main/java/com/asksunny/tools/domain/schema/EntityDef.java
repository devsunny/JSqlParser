package com.asksunny.tools.domain.schema;

import java.util.ArrayList;
import java.util.List;

public class EntityDef {

	
	String databaseName;
	String schemaName;
	
	private String name;
	private List<FieldDef> fields = new ArrayList<FieldDef>();

	public String getName() {
		return name;
	}

	public EntityDef setName(String name) {
		this.name = name.toLowerCase();
		return this;
	}

	public EntityDef addField(FieldDef def) {
		this.fields.add(def);
		return this;
	}

	public List<FieldDef> getFields() {
		return fields;
	}

	public EntityDef setFields(List<FieldDef> fields) {
		this.fields = fields;
		return this;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public EntityDef setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
		return this;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public EntityDef setSchemaName(String schemaName) {
		this.schemaName = schemaName;
		return this;
	}
	
	
	

}
