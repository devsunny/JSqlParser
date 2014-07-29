package com.asksunny.tools.jdbc;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;


public class ConnectionImplGenerator implements JDBCImplClassGenerator {

	private JDBCDriverGeneratorConfig config;

	
	
	public ConnectionImplGenerator(JDBCDriverGeneratorConfig config) {
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
		classBuf.append(String.format("%s%s%s", IMPORT, PreparedStatement.class.getCanonicalName(), STATEMENT_BREAK));	
		classBuf.append(String.format("%s%s%s", IMPORT, Statement.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, CallableStatement.class.getCanonicalName(), STATEMENT_BREAK));		
		classBuf.append(String.format("%s%s%s", IMPORT, Connection.class.getCanonicalName(), STATEMENT_BREAK));	
		classBuf.append(String.format("%s%s%s", IMPORT, SQLException.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, HashMap.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Executor.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, SQLWarning.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, DatabaseMetaData.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, BigDecimal.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Date.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Time.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Timestamp.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Calendar.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Blob.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Clob.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, NClob.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, RowId.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, SQLXML.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Ref.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Struct.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, java.sql.SQLFeatureNotSupportedException.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Savepoint.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Array.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Properties.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Map.class.getCanonicalName(), STATEMENT_BREAK));
		
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements %2$s, %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				Connection.class.getSimpleName(), JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateDeclare();
		generateConstructor();
		generateMethods();
		generateWrapMethod();
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	protected void generateConstructor()
	{		
		classBuf.append(INDENT1).append("public ").append(" ");
		classBuf.append(String.format("%1$s%2$s(String url)", config.getJdbcClassPrefix(), Connection.class.getSimpleName()));
		classBuf.append(" throws SQLException {\n");		
		classBuf.append(INDENT2).append(String.format("this.conntionUrl=url;\n"));		
		classBuf.append(INDENT1).append("}\n\n");	
		
	}
	
	
	
	
	protected void generateDeclare()
	{
		classBuf.append(INDENT1).append(String.format("private final static HashMap<Integer, String> conn_int2cmdMap = %1$sJDBCVTableMaps.CONN_I2FN_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static HashMap<String, Integer> conn_cmd2intMap = %1$sJDBCVTableMaps.CONN_FN2I_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append("private Properties clientInfo = new Properties();").append(STATEMENT_BREAK);		
		classBuf.append(INDENT1).append("private String conntionUrl = null").append(STATEMENT_BREAK);
		
	}
	
	protected void generateMethods()
	{
		List<String> foList = Arrays.asList(new String[]{"unwrap", "isWrapperFor", "getConnection"});
		List<String> notSupported = Arrays.asList(new String[]{"createBlob", "createClob", "createArrayOf", "createNClob", "createSQLXML", "createStruct"});
		Method[] methods  =Connection.class.getMethods();
		for (int i = 0; i < methods.length; i++) 
		{
			Method method = methods[i];	
			if(foList.contains(method.getName())) continue;
			
			if(notSupported.contains(method.getName())){
				generateNotSupportFeatureMethod(method);
			}else if(method.getName().equals("setClientInfo")){
				generateSetClientInfoMethods(method);
			}else{
				generateRemoteMethods(method);
			}
		}
	}
	
	protected void generateSetClientInfoMethods(Method method)
	{
		int pl = method.getParameterTypes().length;
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("(");
		for(int i=0; i<pl; i++)
		{
			classBuf.append(method.getParameterTypes()[i].getSimpleName());
			classBuf.append(" ").append(String.format("param%d", i));
			if( i<pl-1) classBuf.append(",");
		}
		classBuf.append(")");		
		if(method.getExceptionTypes().length>0) classBuf.append(String.format(" throws %s ", method.getExceptionTypes()[0].getName()));
		classBuf.append(" {\n");
		if(pl==1){			
			classBuf.append(INDENT2).append("this.clientInfo.putAll(param0);\n");	
		}else{
			classBuf.append(INDENT2).append("this.clientInfo.setProperty(param0, param1);\n");
		}		
		classBuf.append(INDENT1).append("}\n\n");	
	}
	
	protected void generateRemoteMethods(Method method)
	{
		int pl = method.getParameterTypes().length;
		String retTypeName = method.getReturnType().getSimpleName();
		if(method.getReturnType()==Map.class){
			retTypeName = retTypeName + "<String, Class<?>>";
		}		
		classBuf.append(INDENT1).append("public ").append(retTypeName).append(" ").append(method.getName()).append("(");
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
		classBuf.append(INDENT2).append(String.format("cmdBuf.append(conn_cmd2intMap.get(\"%s\"));\n", method.getName()));
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
		if(method.getReturnType()==void.class){
			classBuf.append(INDENT3).append(";//No op need.\n");
		}else if(method.getReturnType()==int.class){
			classBuf.append(INDENT3).append(String.format("String respVal = response.substring(1);\n", config.getJdbcClassPrefix()));
			classBuf.append(INDENT3).append(String.format("return Integer.valueOf(respVal);\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==boolean.class){
			classBuf.append(INDENT3).append(String.format("String respVal = response.substring(1);\n", config.getJdbcClassPrefix()));
			classBuf.append(INDENT3).append(String.format("return respVal.equals(\"1\") || respVal.equals(\"true\");\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==String.class){
			classBuf.append(INDENT3).append(String.format("String respVal = response.substring(1);\n", config.getJdbcClassPrefix()));
			classBuf.append(INDENT3).append(String.format("return respVal;\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==DatabaseMetaData.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sDatabaseMetaData(this, response.substring(1));\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==Statement.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sStatement(this);\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==Properties.class){
			classBuf.append(INDENT3).append(String.format("return this.clientInfo;\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==PreparedStatement.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sPreparedStatement(this);\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==CallableStatement.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sCallableStatement(this);\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==Savepoint.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sSavepoint(response.substring(1));\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==Map.class){
			classBuf.append(INDENT3).append(String.format("return %1$sMetaDataUtil.convert2TypeMap(response.substring(1));\n", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==int[].class){
			classBuf.append(INDENT3).append("String[] retstrs = response.substring(1).split(\",\");\n");	
			classBuf.append(INDENT3).append("int[] ret =new int[retstrs.length];\n");
			classBuf.append(INDENT3).append("for(int i=0; i<retstrs.length; i++){\n");
			classBuf.append(INDENT4).append("ret[i]=Integer.valueOf(retstrs[i]).intValue(); \n");
			classBuf.append(INDENT3).append("}\n");
			classBuf.append(INDENT3).append("return ret;\n");
		}else{			
			if(method.getName().equals("getWarnings")){
				classBuf.append(INDENT3).append("return new SQLWarning(response.substring(1));");			
			}else{			
				classBuf.append(INDENT3).append(String.format("//add implementation here;\n", config.getJdbcClassPrefix()));
			}
			
		}	
		classBuf.append(INDENT2).append("}\n");					
		classBuf.append(INDENT1).append("}\n\n");	
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
		classBuf.append(INDENT1).append("\n");
		
		
		
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
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		ConnectionImplGenerator generator = new ConnectionImplGenerator(config);		
		System.out.println(generator.generate());

	}

}
