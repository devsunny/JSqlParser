package com.asksunny.tools.jdbc;

import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;

public class JDBCFunctionMapImplGenerator implements JDBCImplClassGenerator {

	private JDBCDriverGeneratorConfig config;

	
	
	public JDBCFunctionMapImplGenerator(JDBCDriverGeneratorConfig config) {
		super();
		this.config = config;
	}

	private StringBuilder classBuf = new StringBuilder();
	
	
	public JDBCDriverGeneratorConfig getConfig() {
		return config;
	}

	public void setConfig(JDBCDriverGeneratorConfig config)
	{
		this.config = config;
	}
	
	
	public String generate()
	{
		
		classBuf.append(String.format("%s%s%s", PACKAGE, config.getPackageName(), STATEMENT_BREAK));
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements  %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				"JDBCVTableMaps", JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateStaticMapping();
			
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	protected void generateStaticMapping()
	{
		classBuf.append(INDENT1).append("public final static java.util.HashMap<Integer, String> DBMD_I2FN_MAP = new java.util.HashMap<Integer, String>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<String, Integer> DBMD_FN2I_MAP = new java.util.HashMap<String, Integer>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<Integer, String> RSMD_I2FN_MAP = new java.util.HashMap<Integer, String>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<String, Integer> RSMD_FN2I_MAP = new java.util.HashMap<String, Integer>();\n");
		
		Method[] methods  =DatabaseMetaData.class.getMethods();
		classBuf.append(INDENT1).append("static {\n");
		int startCmd = 101;
		for (int i = 0; i < methods.length; i++) {
			classBuf.append(INDENT2).append(String.format("DBMD_I2FN_MAP.put(%2$d, \"%1$s\");\n", methods[i].getName(), startCmd));
			classBuf.append(INDENT2).append(String.format("DBMD_FN2I_MAP.put(\"%1$s\", %2$d);\n", methods[i].getName(), startCmd++));	
		}
		startCmd = 101;
		methods  =ResultSetMetaData.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			classBuf.append(INDENT2).append(String.format("RSMD_I2FN_MAP.put(%2$d, \"%1$s\");\n", methods[i].getName(), startCmd));
			classBuf.append(INDENT2).append(String.format("RSMD_FN2I_MAP.put(\"%1$s\", %2$d);\n", methods[i].getName(), startCmd++));	
		}
		classBuf.append(INDENT1).append(BLOCK_CLOSE);
		classBuf.append(BLOCK_BREAK);		
	}
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		JDBCFunctionMapImplGenerator generator = new JDBCFunctionMapImplGenerator(config);		
		System.out.println(generator.generate());

	}

}
