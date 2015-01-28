package com.asksunny.sql.engine;

public class EntityFieldDefinition 
{
		private int jdbcDataType;
		private String fieldName;
		private int fieldIndex;
		private String defaultValue;
		
		
		public int getJdbcDataType() {
			return jdbcDataType;
		}
		public void setJdbcDataType(int jdbcDataType) {
			this.jdbcDataType = jdbcDataType;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public int getFieldIndex() {
			return fieldIndex;
		}
		public void setFieldIndex(int fieldIndex) {
			this.fieldIndex = fieldIndex;
		}
		public String getDefaultValue() {
			return defaultValue;
		}
		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
		
		
	

}
