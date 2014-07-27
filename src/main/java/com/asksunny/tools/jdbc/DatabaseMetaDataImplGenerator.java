package com.asksunny.tools.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements %2$s, %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				DatabaseMetaData.class.getSimpleName(), JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateStaticMapping();
		generateConstructor();
		generateMethods();		
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	protected void generateStaticMapping()
	{
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<Integer, String> int2cmdMap = %1$sJDBCVTableMaps.DBMD_I2FN_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<Integer, String> cmd2intMap = %1$sJDBCVTableMaps.DBMD_FN2I_MAP;\n", config.getJdbcClassPrefix()));
		
		
		classBuf.append(INDENT1).append(BLOCK_BREAK);		
		classBuf.append(INDENT1).append("private java.util.HashMap<String, String> dbMetaData = new java.util.HashMap<String, String>()").append(";\n");
		classBuf.append(INDENT1).append("private JdbcSocketClientInterface client = null").append(STATEMENT_BREAK);
		classBuf.append(INDENT1).append(String.format("private %1$s%2$s conn = null", config.getJdbcClassPrefix(), Connection.class.getSimpleName())).append(STATEMENT_BREAK);
		
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(INDENT1).append("public  JdbcSocketClientInterface getJdbcSocketClient(){ return this.client; }").append(BLOCK_BREAK);
		classBuf.append(INDENT1).append("public  void setJdbcSocketClient(JDBCSocketClient sclient){  this.client = sclient; }").append(BLOCK_BREAK);		
		
	}
	
	protected void generateConstructor()
	{
		
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
		
		Class<?>[]  paramClasses = method.getParameterTypes();
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getName()).append(" ").append(method.getName()).append("(");
		for (int i = 0; i < paramClasses.length; i++) {
			Class<?> paramClass  = paramClasses[i];
			classBuf.append(" ").append(paramClass.getSimpleName()).append(" ");
			classBuf.append("param" + i);
			if(i<paramClasses.length-1) classBuf.append(",");
		}
		
		classBuf.append(") throws SQLException {\n");
		classBuf.append(INDENT2).append("StringBuilder cmdBuf = new StringBuilder();\n");
		classBuf.append(INDENT2).append("cmdBuf.append(DBMETA_CMD);\n");
		classBuf.append(INDENT2).append("cmdBuf.append(FIELD_DELIMITER);\n");
		classBuf.append(INDENT2).append(String.format("cmdBuf.append(cmd2intMap.get(\"%s\"));\n", method.getName()));
		classBuf.append(INDENT2).append("cmdBuf.append(FIELD_DELIMITER);\n");
		for (int i = 0; i < paramClasses.length; i++) {			
			classBuf.append(INDENT2).append(String.format("cmdBuf.append(param%d);\n", i));
			if(i < paramClasses.length-1) classBuf.append(INDENT2).append("cmdBuf.append(FIELD_DELIMITER);\n");
		}
		classBuf.append(INDENT2).append("String response = getJDBCSocketClient().sendCommand(cmdBuf.toString());\n");						
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
		
		int pl = method.getParameterTypes().length;
		switch(pl)
		{
		case 0:
			generateNonResultMethod0P(method);
			break;
		case 1:
			generateNonResultMethod1P(method);
			break;
		case 2:
			generateNonResultMethod2P(method);
			break;
		default:
			generateNonResultMethodDefault(method);
			break;
		}
		
	}
	
	
	protected void generateNonResultMethod0P(Method method)
	{
		
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getName()).append(" ").append(method.getName()).append("() throws SQLException {\n");
		if(method.getReturnType()==boolean.class){
			classBuf.append(INDENT2).append(String.format("String boolval = dbMetaData.get(\"%1$s\")", method.getName())).append(";\n");
			classBuf.append(INDENT2).append("if(boolval==null) return false").append(";\n");
			classBuf.append(INDENT2).append("return Boolean.valueOf(boolval);\n");
			
		}else if(method.getReturnType()==int.class){
			classBuf.append(INDENT2).append(String.format("String intval = dbMetaData.get(\"%1$s\")", method.getName())).append(";\n");
			classBuf.append(INDENT2).append("if(intval==null) return 0").append(";\n");
			classBuf.append(INDENT2).append("return Integer.valueOf(intval);\n");
			
		}else if(method.getReturnType()==String.class){
			classBuf.append(INDENT2).append(String.format("String strval = dbMetaData.get(\"%1$s\")", method.getName())).append(";\n");
			classBuf.append(INDENT2).append("return strval;\n");					
		}else if(method.getReturnType()==Connection.class){			
			classBuf.append(INDENT2).append("return conn;\n");					
		}else {
			classBuf.append(INDENT2).append("return null;\n");	
		}
		classBuf.append(INDENT1).append("}\n\n");		
		
	}
	
	
	protected void generateNonResultMethod1P(Method method)
	{
		Class<?> pt  = method.getParameterTypes()[0];
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getName()).append(" ").append(method.getName());
		classBuf.append(String.format("(%s %sParam) throws SQLException {\n", method.getParameterTypes()[0].getName(),  method.getParameterTypes()[0].getName().toLowerCase()));
		if(pt==int.class){					
			String xf = String.format("String dbmdcmd = String.format(\"%%1$c%%2$d%%3$c%%4$d\", DBMETA_CMD, cmd2intMap.get(\"%s\"), FIELD_DELIMITER, intParam);\n", method.getName());
			classBuf.append(INDENT2).append(xf);
			//xf = String.format("String response = getJDBCSocketClient().send(xmd);\n", method.getName());		
			classBuf.append(INDENT2).append("String response = getJDBCSocketClient().sendCommand(dbmdcmd);\n");					
			classBuf.append(INDENT2).append("char c = response.charAt(0);\n");			
			if(method.getReturnType()==boolean.class){						
				classBuf.append(INDENT2).append("if (c==SQL_ERROR){\n");
				classBuf.append(INDENT2).append("    throw new SQLException(response.substring(1));\n");						
				classBuf.append(INDENT2).append("}else{\n");
				classBuf.append(INDENT2).append("    return response.substring(1).equalsIgnoreCase(\"true\");\n");
				classBuf.append(INDENT2).append("}\n");						
			}else if(method.getReturnType()==int.class){						
				classBuf.append(INDENT2).append("if (c==SQL_ERROR){\n");
				classBuf.append(INDENT2).append("    throw new SQLException(response.substring(1));\n");						
				classBuf.append(INDENT2).append("}else{\n");
				classBuf.append(INDENT2).append("    return response.substring(1).equalsIgnoreCase(\"true\");\n");
				classBuf.append(INDENT2).append("}\n");						
			}else{
				System.err.println(String.format("No template for %s: %s", method.getName(), pt.getName()));
			}			
		}else if(pt==Class.class){
			if(method.getReturnType()==boolean.class){						
				classBuf.append(INDENT2).append("return false;\n");			
			}else if(method.getReturnType()==int.class){	
				classBuf.append(INDENT2).append("throw new SQLException(\"Not implemented function\")\n");						
			}else{
				classBuf.append(INDENT2).append("throw new SQLException(\"Not implemented function\")\n");	
			}
		}else if(pt==boolean.class){
			System.err.println(String.format("No template for %s: %s", method.getName(), pt.getName()));
		}else if(pt==String.class){
			System.err.println(String.format("No template for %s: %s", method.getName(), pt.getName()));
		}else{
			System.err.println(String.format("No template for %s: %s", method.getName(), pt.getName()));
		}
		classBuf.append(INDENT1).append("}\n\n");
	}
	
	protected void generateNonResultMethod2P(Method method)
	{
		
		
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getName()).append(" ").append(method.getName());
		classBuf.append(String.format("(%1$s param1, %2$s param2) throws SQLException {\n", 
				method.getParameterTypes()[0].getSimpleName(),  
				method.getParameterTypes()[1].getSimpleName()));
		String xf = String.format("String dbmdcmd = String.format(\"%%1$c%%2$d%%3$c\", DBMETA_CMD, cmd2intMap.get(\"%s\"), FIELD_DELIMITER);\n", method.getName());
		classBuf.append(INDENT2).append(xf);
		classBuf.append(INDENT2).append("StringBuilder cmdBuf = new StringBuilder();\n");
		classBuf.append(INDENT2).append("cmdBuf.append(dbmdcmd).append(param1).append(FIELD_DELIMITER).append(param2);\n");
		classBuf.append(INDENT2).append("String response = getJDBCSocketClient().sendCommand(cmdBuf.toString());\n");						
		classBuf.append(INDENT2).append("char c = response.charAt(0);\n");			
		if(method.getReturnType()==boolean.class){						
			classBuf.append(INDENT2).append("if (c==SQL_ERROR){\n");
			classBuf.append(INDENT2).append("    throw new SQLException(response.substring(1));\n");						
			classBuf.append(INDENT2).append("}else{\n");
			classBuf.append(INDENT2).append("    return response.substring(1).equalsIgnoreCase(\"true\");\n");
			classBuf.append(INDENT2).append("}\n");						
		}else if(method.getReturnType()==int.class){						
			classBuf.append(INDENT2).append("if (c==SQL_ERROR){\n");
			classBuf.append(INDENT2).append("    throw new SQLException(response.substring(1));\n");						
			classBuf.append(INDENT2).append("}else{\n");
			classBuf.append(INDENT2).append("    return response.substring(1).equalsIgnoreCase(\"true\");\n");
			classBuf.append(INDENT2).append("}\n");						
		}else{
			System.err.println(String.format("No template for %s: %s", method.getName(),method.getParameterTypes()[0].getSimpleName(), method.getParameterTypes()[1].getSimpleName()));
		}			
		classBuf.append(INDENT1).append("}\n\n");
	}
	
	
	
	protected void generateNonResultMethodDefault(Method method)
	{		
		
		System.err.println(String.format("No template for %s: %s", method.getName(),method.getParameterTypes()[0].getSimpleName(), method.getParameterTypes()[1].getSimpleName()));
		
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
