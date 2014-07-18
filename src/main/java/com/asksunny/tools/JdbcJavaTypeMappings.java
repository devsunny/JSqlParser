package com.asksunny.tools;

import java.sql.Types;
import java.util.HashMap;

public class JdbcJavaTypeMappings 
{
	public static HashMap<Integer, String> mappings = new HashMap<>();
	public static HashMap<String, String> namemappings = new HashMap<>();
	static
	{
		mappings.put(Types.BIT, "boolean");
		mappings.put(Types.TINYINT, "int");
		mappings.put(Types.SMALLINT, "int");
		mappings.put(Types.INTEGER, "int");
		mappings.put(Types.BIGINT, "long");
		mappings.put(Types.FLOAT, "double");
		mappings.put(Types.REAL, "double");
		mappings.put(Types.DOUBLE, "double");
		mappings.put(Types.NUMERIC, "double");
		mappings.put(Types.DECIMAL, "double");
		mappings.put(Types.CHAR, "String");
		mappings.put(Types.VARCHAR, "String");
		mappings.put(Types.LONGVARCHAR, "String");
		mappings.put(Types.DATE, "java.sql.Date");
		mappings.put(Types.TIME, "java.sql.Time");
		mappings.put(Types.TIMESTAMP, "java.sql.Timestamp");
		mappings.put(Types.BINARY, "byte[]");
		mappings.put(Types.VARBINARY, "byte[]");
		mappings.put(Types.LONGVARBINARY, "byte[]");
		mappings.put(Types.NULL, "String");
		mappings.put(Types.OTHER, "String");
		mappings.put(Types.JAVA_OBJECT, "Object");		
		mappings.put(Types.STRUCT, "Object");
		mappings.put(Types.ARRAY, "String");
		mappings.put(Types.BLOB, "byte[]");
		mappings.put(Types.CLOB, "String");		
		mappings.put(Types.BOOLEAN, "boolean");
		mappings.put(Types.ROWID, "String");
		mappings.put(Types.NCHAR, "String");
		mappings.put(Types.NVARCHAR, "String");
		mappings.put(Types.LONGNVARCHAR, "String");
		mappings.put(Types.NCLOB, "String");
		mappings.put(Types.SQLXML, "String");
		
		
		namemappings.put("BIT", "boolean");
		namemappings.put("TINYINT", "int");
		namemappings.put("SMALLINT", "int");
		namemappings.put("INTEGER", "int");
		namemappings.put("BIGINT", "long");
		namemappings.put("FLOAT", "double");
		namemappings.put("REAL", "double");
		namemappings.put("DOUBLE", "double");
		namemappings.put("NUMERIC", "double");
		namemappings.put("DECIMAL", "double");
		namemappings.put("CHAR", "String");
		namemappings.put("VARCHAR", "String");
		namemappings.put("VARCHAR2", "String");
		namemappings.put("LONGVARCHAR", "String");
		namemappings.put("DATE", "java.sql.Date");
		namemappings.put("TIME", "java.sql.Time");
		namemappings.put("TIMESTAMP", "java.sql.Timestamp");
		namemappings.put("BINARY", "byte[]");
		namemappings.put("VARBINARY", "byte[]");
		namemappings.put("LONGVARBINARY", "byte[]");
		namemappings.put("NULL", "String");
		namemappings.put("OTHER", "String");
		namemappings.put("JAVA_OBJECT", "Object");
		namemappings.put("DISTINCT", "null");
		namemappings.put("STRUCT", "Object");
		namemappings.put("ARRAY", "String");
		namemappings.put("BLOB", "byte[]");
		namemappings.put("CLOB", "String");
		namemappings.put("REF", "null");
		namemappings.put("DATALINK", "null");
		namemappings.put("BOOLEAN", "boolean");
		namemappings.put("ROWID", "String");
		namemappings.put("NCHAR", "String");
		namemappings.put("NVARCHAR", "String");
		namemappings.put("LONGNVARCHAR", "String");
		namemappings.put("NCLOB", "String");
		namemappings.put("SQLXML", "String");		
	}
	
	
	public static String getJavaType(String name)
	{
		String ret = namemappings.get(name.toUpperCase());
		if(ret==null) ret = "String";
		return ret;
	}
	
	
	public static String getJavaType(int jdbcType)
	{
		String ret = mappings.get(jdbcType);
		if(ret==null) ret = "String";
		return ret;
	}
}
