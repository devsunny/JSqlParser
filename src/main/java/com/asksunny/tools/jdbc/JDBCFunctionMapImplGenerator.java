package com.asksunny.tools.jdbc;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		genResultsetMapping();
		genStatementMapping();
		genParameterMetaDataMapping();
		genConnectionMapping();
		
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	protected void generateStaticMapping()
	{
		classBuf.append(INDENT1).append("public final static java.util.HashMap<Integer, String> DBMD_I2FN_MAP = new java.util.HashMap<Integer, String>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<String, Integer> DBMD_FN2I_MAP = new java.util.HashMap<String, Integer>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<Integer, String> RSMD_I2FN_MAP = new java.util.HashMap<Integer, String>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<String, Integer> RSMD_FN2I_MAP = new java.util.HashMap<String, Integer>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<Integer, String> PARAMMD_I2FN_MAP = new java.util.HashMap<Integer, String>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<String, Integer> PARAMMD_FN2I_MAP = new java.util.HashMap<String, Integer>();\n");
		
		
		classBuf.append(INDENT1).append("public final static java.util.HashMap<Integer, String> RS_I2FN_MAP = new java.util.HashMap<Integer, String>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<String, Integer> RS_FN2I_MAP = new java.util.HashMap<String, Integer>();\n");
		
		classBuf.append(INDENT1).append("public final static java.util.HashMap<Integer, String> STMT_I2FN_MAP = new java.util.HashMap<Integer, String>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<String, Integer> STMT_FN2I_MAP = new java.util.HashMap<String, Integer>();\n");
		
		classBuf.append(INDENT1).append("public final static java.util.HashMap<Integer, String> CONN_I2FN_MAP = new java.util.HashMap<Integer, String>();\n");
		classBuf.append(INDENT1).append("public final static java.util.HashMap<String, Integer> CONN_FN2I_MAP = new java.util.HashMap<String, Integer>();\n");
		
		
		
		
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
	
	protected void genParameterMetaDataMapping()
	{
		
		Method[] methods  =ParameterMetaData.class.getMethods();
		classBuf.append(INDENT1).append("static {\n");
		int startCmd = 101;
		for (int i = 0; i < methods.length; i++) {
			classBuf.append(INDENT2).append(String.format("PARAMMD_I2FN_MAP.put(%2$d, \"%1$s\");\n", methods[i].getName(), startCmd));
			classBuf.append(INDENT2).append(String.format("PARAMMD_FN2I_MAP.put(\"%1$s\", %2$d);\n", methods[i].getName(), startCmd++));	
		}
		classBuf.append(INDENT1).append(BLOCK_CLOSE);
		classBuf.append(BLOCK_BREAK);	
	}
	
	protected void genResultsetMapping()
	{
		
		Filter<Method> colIdxMethodFilter = new Filter<Method>() {			
			@Override
			public boolean match(Method method) {				
				return method.getName().startsWith("get") && method.getParameterTypes().length>0 && method.getParameterTypes()[0]==int.class;
			}
		};
		List<Method> methods = ArraysUtil.filterFunction(ResultSet.class.getMethods(), colIdxMethodFilter);
		final Set<String> colMethodNames = new HashSet<String>();
		for (Method method : methods) {
			colMethodNames.add(method.getName());			
		}
		int startCmd = 101;
		classBuf.append(INDENT1).append("static {\n");
		for (Method method : ResultSet.class.getMethods()) {
			if(method.getName().equals("unwrap") 
					|| method.getName().equals("isWrapperFor") 
					|| method.getName().equals("getMetaData") 
					|| method.getName().equals("getStatement") ){
				continue;
			}
			
			if(!colMethodNames.contains(method.getName())){
				if( method.getName().equals("rowUpdated")
						|| method.getName().equals("rowDeleted")
						|| method.getName().equals("rowInserted")
						|| method.getName().equals("moveToCurrentRow")
						|| method.getName().equals("moveToInsertRow")						
						|| method.getName().equals("refreshRow")
						|| method.getName().equals("cancelRowUpdates")
						|| method.getName().startsWith("update") 
						){
					//ignore it
				}else{					
					classBuf.append(INDENT2).append(String.format("RS_I2FN_MAP.put(%2$d, \"%1$s\");\n", method.getName(), startCmd));
					classBuf.append(INDENT2).append(String.format("RS_FN2I_MAP.put(\"%1$s\", %2$d);\n", method.getName(), startCmd++));	
				}
			}
		}
		classBuf.append(INDENT1).append(BLOCK_CLOSE);
		classBuf.append(BLOCK_BREAK);	
	}
	
	protected void genStatementMapping()
	{
		List<String> foList = Arrays.asList(new String[]{"unwrap", "isWrapperFor"});
		Method[] methods  = CallableStatement.class.getMethods();
		int startCmd = 101;
		classBuf.append(INDENT1).append("static {\n");
		for (int i = 0; i < methods.length; i++) 
		{
			Method method = methods[i];	
			if(foList.contains(method.getName())) continue;
			if(method.getName().startsWith("execute")){
				
			}else{
				
				classBuf.append(INDENT2).append(String.format("STMT_I2FN_MAP.put(%2$d, \"%1$s\");\n", method.getName(), startCmd));
				classBuf.append(INDENT2).append(String.format("STMT_FN2I_MAP.put(\"%1$s\", %2$d);\n", method.getName(), startCmd++));	
				
			}
		}
		classBuf.append(INDENT1).append(BLOCK_CLOSE);
		classBuf.append(BLOCK_BREAK);	
	}
	
	protected void genConnectionMapping()
	{
		List<String> foList = Arrays.asList(new String[]{"unwrap", "isWrapperFor"});
		Method[] methods  = Connection.class.getMethods();
		int startCmd = 101;
		classBuf.append(INDENT1).append("static {\n");
		for (int i = 0; i < methods.length; i++) 
		{
			Method method = methods[i];	
			if(foList.contains(method.getName())) continue;
			if(method.getName().startsWith("execute")){
				
			}else{
				
				classBuf.append(INDENT2).append(String.format("STMT_I2FN_MAP.put(%2$d, \"%1$s\");\n", method.getName(), startCmd));
				classBuf.append(INDENT2).append(String.format("STMT_FN2I_MAP.put(\"%1$s\", %2$d);\n", method.getName(), startCmd++));	
				
			}
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
