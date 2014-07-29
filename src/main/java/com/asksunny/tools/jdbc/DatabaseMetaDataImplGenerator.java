package com.asksunny.tools.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Arrays;



public class DatabaseMetaDataImplGenerator implements JDBCImplClassGenerator {

	private JDBCDriverGeneratorConfig config;

	
	
	public DatabaseMetaDataImplGenerator(JDBCDriverGeneratorConfig config) {
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
		classBuf.append(String.format("%s%s%s", IMPORT, DatabaseMetaData.class.getCanonicalName(), STATEMENT_BREAK));	
		classBuf.append(String.format("%s%s%s", IMPORT, SQLException.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, SQLFeatureNotSupportedException.class.getCanonicalName(), STATEMENT_BREAK));
		
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements %2$s, %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				DatabaseMetaData.class.getSimpleName(), JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateDeclaration();
		generateConstructor();
		generateMethods();	
		generateWrapMethod();
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	protected void generateDeclaration()
	{
		
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<Integer, String> db_int2cmdMap = %1$sJDBCVTableMaps.DBMD_I2FN_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<String, Integer> db_cmd2intMap = %1$sJDBCVTableMaps.DBMD_FN2I_MAP;\n", config.getJdbcClassPrefix()));

		classBuf.append(INDENT1).append(BLOCK_BREAK);		
		classBuf.append(INDENT1).append("private java.util.HashMap<String, String> dbMetaData = new java.util.HashMap<String, String>()").append(";\n");
		
		classBuf.append(INDENT1).append(String.format("private %1$s%2$s conn = null", config.getJdbcClassPrefix(), Connection.class.getSimpleName())).append(STATEMENT_BREAK);
		classBuf.append(BLOCK_BREAK);
		
		
	}
	
	protected void generateConstructor()
	{
		classBuf.append(INDENT1).append("public ").append(" ");
		classBuf.append(String.format("%1$s%3$s(%1$s%2$s conn, String metadata)", config.getJdbcClassPrefix(), Connection.class.getSimpleName(), DatabaseMetaData.class.getSimpleName()));
		classBuf.append(" throws SQLException {\n");
		classBuf.append(INDENT2).append(String.format("this.conn=conn;\n"));	
		classBuf.append(INDENT2).append(String.format("this.dbMetaData=%1$s%2$s.convert2DatabaseMetaData(metadata);\n",  config.getJdbcClassPrefix(), 
				"MetaDataUtil"));	
		classBuf.append(INDENT1).append("}\n\n");	
	}
	
	protected void generateMethods()
	{
		Method[] methods  =DatabaseMetaData.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if(method.getReturnType()==ResultSet.class){
				generateResultMethod(method);
			}else{
				generateNonResultMethod(method);
			}
		}
	}
	
	
	protected void generateResultMethod(Method method)
	{		
		
		int pl = method.getParameterTypes().length;
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getName()).append(" ").append(method.getName()).append("(");
		for(int i=0; i<pl; i++)
		{
			classBuf.append(method.getParameterTypes()[i].getSimpleName());
			classBuf.append(" ").append(String.format("param%d", i));
			if( i<pl-1) classBuf.append(",");
		}
		classBuf.append(") throws SQLException {\n");	
		classBuf.append(INDENT2).append("StringBuilder cmdBuf = new StringBuilder();\n");
		classBuf.append(INDENT2).append("cmdBuf.append(DBMETA_CMD);\n");
		classBuf.append(INDENT2).append("cmdBuf.append(FIELD_DELIMITER);\n");
		classBuf.append(INDENT2).append(String.format("cmdBuf.append(db_cmd2intMap.get(\"%s\"));\n", method.getName()));
		classBuf.append(INDENT2).append("cmdBuf.append(FIELD_DELIMITER);\n");
		for(int i=0; i<pl; i++)			{
			classBuf.append(INDENT2).append(String.format("cmdBuf.append(param%d);\n", i)  );
			if( i<pl-1) classBuf.append(INDENT2).append("cmdBuf.append(FIELD_DELIMITER);\n");
		}		
		classBuf.append(INDENT2).append("String response = sendRemoteRequest(cmdBuf.toString());\n");
		classBuf.append(INDENT2).append("char c = response.charAt(0);\n");			
		classBuf.append(INDENT2).append("if (c==SQL_ERROR){\n");
		classBuf.append(INDENT3).append("throw new SQLException(response.substring(1));\n");						
		classBuf.append(INDENT2).append("}else{\n");
		classBuf.append(INDENT3).append(String.format("%1$sResultSet  rs = new  %1$sResultSet(conn, response.substring(1));\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT3).append(String.format("return rs;\n"));		
		classBuf.append(INDENT2).append("}\n");					
		classBuf.append(INDENT1).append("}\n\n");	
	}
	
	
	
	
	
	
	protected void generateNonResultMethod(Method method)
	{
		
		if(method.getName().equals("unwrap") || method.getName().equals("isWrapperFor") ){
			return;
		}else if(method.getName().equals("getRowIdLifetime")){
			generateNotSupportFeatureMethod(method);
			return;
		}
		
		int pl = method.getParameterTypes().length;
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getName()).append(" ").append(method.getName()).append("(");
		for(int i=0; i<pl; i++)
		{
			classBuf.append(method.getParameterTypes()[i].getSimpleName());
			classBuf.append(" ").append(String.format("param%d", i));
			if( i<pl-1) classBuf.append(",");
		}
		classBuf.append(")  ");	
		if(method.getExceptionTypes().length>0){
			classBuf.append(" throws SQLException");
		}
		classBuf.append(" {\n");
		if(pl==0){
			classBuf.append(INDENT2).append(String.format("String val = dbMetaData.get(\"%1$s\");\n",  method.getName()));
			if(method.getReturnType()==String.class){
				classBuf.append(INDENT2).append(String.format("return val;\n",  method.getName()));
			}else if(method.getReturnType()==boolean.class){
				classBuf.append(INDENT2).append(String.format("if(val==null) return false;\n",  method.getName()));
				classBuf.append(INDENT2).append(String.format("return (val.equals(\"1\") || val.equals(\"true\"));\n",  method.getName()));
			}else if(method.getReturnType()==int.class){
				classBuf.append(INDENT2).append(String.format("if(val==null) return 0;\n",  method.getName()));
				classBuf.append(INDENT2).append(String.format("return Integer.valueOf(val);\n",  method.getName()));
			}else if(method.getReturnType()==Connection.class){				
				classBuf.append(INDENT3).append("return this.conn;\n");
			}else{
				classBuf.append(INDENT2).append("//Implement proper return here;\n");
			}
		}else{
			classBuf.append(INDENT2).append("StringBuilder cmdBuf = new StringBuilder();\n");
			classBuf.append(INDENT2).append("cmdBuf.append(DBMETA_CMD);\n");
			classBuf.append(INDENT2).append("cmdBuf.append(FIELD_DELIMITER);\n");
			classBuf.append(INDENT2).append(String.format("cmdBuf.append(db_cmd2intMap.get(\"%s\"));\n", method.getName()));
			classBuf.append(INDENT2).append("cmdBuf.append(FIELD_DELIMITER);\n");
			for(int i=0; i<pl; i++)			{
				classBuf.append(INDENT2).append(String.format("cmdBuf.append(param%d);\n", i)  );
				if( i<pl-1) classBuf.append(INDENT2).append("cmdBuf.append(FIELD_DELIMITER);\n");
			}
			classBuf.append(INDENT2).append("String response = sendRemoteRequest(cmdBuf.toString());\n");
			classBuf.append(INDENT2).append("char c = response.charAt(0);\n");			
			classBuf.append(INDENT2).append("if (c==SQL_ERROR){\n");
			classBuf.append(INDENT3).append("throw new SQLException(response.substring(1));\n");						
			classBuf.append(INDENT2).append("}else{\n");
			classBuf.append(INDENT3).append("String val = response.substring(1);\n");
			if(method.getReturnType()==String.class){
				classBuf.append(INDENT3).append(String.format("return val;\n",  method.getName()));
			}else if(method.getReturnType()==boolean.class){
				classBuf.append(INDENT3).append(String.format("if(val==null) return false;\n",  method.getName()));
				classBuf.append(INDENT3).append(String.format("return (val.equals(\"1\") || val.equals(\"true\"));\n",  method.getName()));
			}else if(method.getReturnType()==int.class){
				classBuf.append(INDENT3).append(String.format("if(val==null) return 0;\n",  method.getName()));
				classBuf.append(INDENT3).append(String.format("return Integer.valueOf(val);\n",  method.getName()));
			}else{
				classBuf.append(INDENT3).append("//Implement proper return here;\n");
			}				
			classBuf.append(INDENT2).append("}\n");	
		}		
		classBuf.append(INDENT1).append("}\n\n");	
		
	}
	
	
	protected void generateNotSupportFeatureMethod(Method method)
	{		
		int pl = method.getParameterTypes().length;
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getName()).append(" ").append(method.getName()).append("(");
		for(int i=0; i<pl; i++)
		{
			classBuf.append(method.getParameterTypes()[i].getSimpleName());
			classBuf.append(" ").append(String.format("param%d", i));
			if( i<pl-1) classBuf.append(",");
		}
		classBuf.append(") throws SQLException {\n");	
		classBuf.append(INDENT2).append(String.format("throw new SQLFeatureNotSupportedException(\"%1$s(%2$s)\");\n", method.getName(), Arrays.asList(method.getParameterTypes())));
		classBuf.append(INDENT1).append("}\n\n");	
		
	}
	
	
	protected void generateNonResultMethodDefault(Method method)
	{		
		
		System.err.println(String.format("No template for %s: %s", method.getName(),method.getParameterTypes()[0].getSimpleName(), method.getParameterTypes()[1].getSimpleName()));
		
	}
	
	public void generateWrapMethod()
	{
		classBuf.append(INDENT1).append("protected String sendRemoteRequest(String command) throws SQLException\n");
		classBuf.append(INDENT1).append("{    	\n");
		classBuf.append(INDENT1).append("	return \"0\";\n");
		classBuf.append(INDENT1).append("}\n");		
		classBuf.append(INDENT1).append("@Override\n");
		classBuf.append(INDENT1).append("public <T> T unwrap(Class<T> iface) throws SQLException {		\n");
		classBuf.append(INDENT1).append("	return null;\n");
		classBuf.append(INDENT1).append("}\n");
		classBuf.append(INDENT1).append("\n");
		classBuf.append(INDENT1).append("@Override\n");
		classBuf.append(INDENT1).append("public boolean isWrapperFor(Class<?> iface) throws SQLException {	\n");
		classBuf.append(INDENT1).append("	return false;\n");
		classBuf.append(INDENT1).append("}\n");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		DatabaseMetaDataImplGenerator generator = new DatabaseMetaDataImplGenerator(config);		
		System.out.println(generator.generate());

	}

}
