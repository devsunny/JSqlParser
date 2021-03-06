package com.asksunny.tools.jdbc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class PreparedStatementImplGenerator implements JDBCImplClassGenerator {

	private JDBCDriverGeneratorConfig config;

	
	
	public PreparedStatementImplGenerator(JDBCDriverGeneratorConfig config) {
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
		classBuf.append(String.format("%s%s%s", IMPORT, ResultSet.class.getCanonicalName(), STATEMENT_BREAK));	
		classBuf.append(String.format("%s%s%s", IMPORT, Statement.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, HashMap.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Array.class.getCanonicalName(), STATEMENT_BREAK));
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
		classBuf.append(String.format("%s%s%s", IMPORT, SQLWarning.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, ResultSetMetaData.class.getCanonicalName(), STATEMENT_BREAK));	
		classBuf.append(String.format("%s%s%s", IMPORT, Base64.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Reader.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, StringReader.class.getCanonicalName(), STATEMENT_BREAK));		
		classBuf.append(String.format("%s%s%s", IMPORT, InputStream.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, ByteArrayInputStream.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, URL.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Connection.class.getCanonicalName(), STATEMENT_BREAK));	
		classBuf.append(String.format("%s%s%s", IMPORT, SQLException.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, SQLFeatureNotSupportedException.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Map.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, ParameterMetaData.class.getCanonicalName(), STATEMENT_BREAK));
		
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements %2$s, %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				PreparedStatement.class.getSimpleName(), JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateDeclare();
		generateConstructor();
		generateMethods();		
		generateWrapMethod();
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	protected void generateDeclare()
	{
		
		classBuf.append(INDENT1).append(String.format("private final static HashMap<Integer, String> stmt_int2cmdMap = %1$sJDBCVTableMaps.STMT_I2FN_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static HashMap<String, Integer> stmt_cmd2intMap = %1$sJDBCVTableMaps.STMT_FN2I_MAP;\n", config.getJdbcClassPrefix()));

		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(INDENT1).append(String.format("private %1$s%2$s connection=null;\n", config.getJdbcClassPrefix(), Connection.class.getSimpleName()));
		classBuf.append(BLOCK_BREAK);
	}
	
	
	protected void generateConstructor()
	{		
		classBuf.append(INDENT1).append("public ").append(" ");
		classBuf.append(String.format("%1$s%3$s(%1$s%2$s conn)", config.getJdbcClassPrefix(), Connection.class.getSimpleName(), PreparedStatement.class.getSimpleName(), ResultSet.class.getSimpleName()));
		classBuf.append(" throws SQLException {\n");
		classBuf.append(INDENT2).append(String.format("this.connection=conn;\n"));		
		classBuf.append(INDENT1).append("}\n\n");	
			
	}
	
	
	protected void generateMethods()
	{
		List<String> foList = Arrays.asList(new String[]{"unwrap", "isWrapperFor", "getConnection"});		
		Method[] methods  =PreparedStatement.class.getMethods();
		for (int i = 0; i < methods.length; i++) 
		{
			Method method = methods[i];	
			if(foList.contains(method.getName())) continue;
			generateRemoteMethods(method);
		}
	}
	
	
	protected void generateRemoteMethods(Method method)
	{
		int pl = method.getParameterTypes().length;
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("(");
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
		classBuf.append(INDENT2).append(String.format("cmdBuf.append(stmt_cmd2intMap.get(\"%s\"));\n", method.getName()));
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
		}else if(method.getReturnType()==ResultSet.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sResultSet(this.connection, this, response.substring(1));", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==ResultSetMetaData.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sResultSetMetaData(response.substring(1));", config.getJdbcClassPrefix()));
		}else if(method.getReturnType()==int[].class){
			classBuf.append(INDENT3).append("String[] retstrs = response.substring(1).split(\",\");\n");	
			classBuf.append(INDENT3).append("int[] ret =new int[retstrs.length];\n");
			classBuf.append(INDENT3).append("for(int i=0; i<retstrs.length; i++){\n");
			classBuf.append(INDENT4).append("ret[i]=Integer.valueOf(retstrs[i]).intValue(); \n");
			classBuf.append(INDENT3).append("}\n");
			classBuf.append(INDENT3).append("return ret;\n");
		}else if(method.getReturnType()==ParameterMetaData.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sParameterMetaData(response.substring(1));", config.getJdbcClassPrefix()));
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
	
	
	
	
	protected void generateNotImplementedMethod(Method method)
	{		
		
		System.err.println(String.format("No template for %s %s:(%s)",method.getReturnType().getSimpleName(), method.getName(), Arrays.asList(method.getParameterTypes())));
		
		
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
		
		classBuf.append(INDENT1).append("@Override\n");
		classBuf.append(INDENT1).append("public Connection getConnection() throws SQLException {		\n");
		classBuf.append(INDENT1).append("	return this.connection;\n");
		classBuf.append(INDENT1).append("}\n");
		
	}
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		PreparedStatementImplGenerator generator = new PreparedStatementImplGenerator(config);		
		System.out.println(generator.generate());

	}

}
