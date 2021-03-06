package com.asksunny.tools.domain.schema;

public class FieldDef {
	public static final int UNDEF_TYPE = -99999;
	
	String name;
	String typename;
	boolean nullable;
	int length;
	boolean decimal;
	boolean primaryKey;
	boolean foreignKey;
	int jdbcType = UNDEF_TYPE;	
	String foreignTable;
	String foreignColumnName;
	
	
	

	public int getJdbcType() {
		return jdbcType;
	}

	public FieldDef setJdbcType(int jdbcType) {
		this.jdbcType = jdbcType;
		return this;
	}

	public boolean isDecimal() {
		return decimal;
	}

	public FieldDef setDecimal(boolean decimal) {
		this.decimal = decimal;
		return this;
	}

	public boolean isForeignKey() {
		return foreignKey;
	}

	public FieldDef setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
		return this;
	}

	public String getForeignTable() {
		return foreignTable;
	}

	public FieldDef setForeignTable(String foreignTable) {
		this.foreignTable = foreignTable;
		return this;
	}

	public String getForeignColumnName() {
		return foreignColumnName;
	}

	public FieldDef setForeignColumnName(String foreignColumnName) {
		this.foreignColumnName = foreignColumnName;
		return this;
	}

	public String getName() {
		return name;
	}

	public FieldDef setName(String name) {
		this.name = name.toLowerCase();
		return this;
	}

	public String getTypename() {
		return typename;
	}

	public FieldDef setTypename(String typename) {

		this.typename = typename.toUpperCase();
		return this;
	}

	public boolean isNullable() {
		return nullable;
	}

	public FieldDef setNullable(boolean nullable) {
		this.nullable = nullable;
		return this;
	}

	public int getLength() {
		return length;
	}

	public FieldDef setLength(int length) {
		this.length = length;
		return this;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public FieldDef setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
		return this;
	}
	
	

}
