package com.asksunny.tools.jdbc;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
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

public class CallableStatementImplGenerator implements JDBCImplClassGenerator {

	private JDBCDriverGeneratorConfig config;

	
	
	public CallableStatementImplGenerator(JDBCDriverGeneratorConfig config) {
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
		classBuf.append(String.format("%s%s%s", IMPORT, ResultSet.class.getCanonicalName(), STATEMENT_BREAK));			
		classBuf.append(String.format("%s%s%s", IMPORT, CallableStatement.class.getCanonicalName(), STATEMENT_BREAK));
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
		classBuf.append(String.format("%s%s%s", IMPORT, URL.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Connection.class.getCanonicalName(), STATEMENT_BREAK));	
		classBuf.append(String.format("%s%s%s", IMPORT, SQLException.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, SQLFeatureNotSupportedException.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, Map.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, ParameterMetaData.class.getCanonicalName(), STATEMENT_BREAK));
		
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements %2$s, %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				CallableStatement.class.getSimpleName(), JDBC_DRIVER_CONSTANTS_INTERFACE));
		
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
		classBuf.append(String.format("%1$s%3$s(%1$s%2$s conn)", config.getJdbcClassPrefix(), Connection.class.getSimpleName(), CallableStatement.class.getSimpleName(), ResultSet.class.getSimpleName()));
		classBuf.append(" throws SQLException {\n");
		classBuf.append(INDENT2).append(String.format("this.connection=conn;\n"));		
		classBuf.append(INDENT1).append("}\n\n");	
			
	}
	
	
	protected void generateMethods()
	{
		List<String> foList = Arrays.asList(new String[]{"unwrap", "isWrapperFor", "getConnection"});		
		List<String> notSupported = Arrays.asList(new String[]{"getBlob", "getClob", "getArrayOf", "getNClob","getRowId", "getSQLXML","getRef", "getStruct", "getArray"});
		
		Method[] methods  =CallableStatement.class.getMethods();
		for (int i = 0; i < methods.length; i++) 
		{
			Method method = methods[i];	
			if(foList.contains(method.getName())) continue;
			if(notSupported.contains(method.getName())){
				generateNotSupportFeatureMethod(method);
			}else{
				generateRemoteMethods(method);
			}
			
		}
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
		Class<?> retType = method.getReturnType();
		 if(retType==void.class){
			classBuf.append(INDENT3).append(";//No op need.\n");
		}else if(retType==int.class){
			classBuf.append(INDENT4).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return 0;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return Integer.valueOf(rawcol);\n");
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==long.class){
			classBuf.append(INDENT4).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return 0;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return Long.valueOf(rawcol);\n");
			classBuf.append(INDENT3).append("}\n");
		} else if(retType==short.class){
			classBuf.append(INDENT4).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return 0;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return Short.valueOf(rawcol);\n");
			classBuf.append(INDENT3).append("}\n");
		} else if(retType==double.class){
			classBuf.append(INDENT4).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return 0;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return Double.valueOf(rawcol);\n");
			classBuf.append(INDENT3).append("}\n");
		} else if(retType==float.class){
			classBuf.append(INDENT4).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return 0;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return Float.valueOf(rawcol);\n");
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==boolean.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return false;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return rawcol.equals(\"1\") || rawcol.equals(\"true\") ;\n");
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==byte.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return 0;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return ((byte)Integer.valueOf(rawcol).intValue());\n");
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==ResultSet.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sResultSet(this.connection, this, response.substring(1));", config.getJdbcClassPrefix()));
		}else if(retType==ResultSetMetaData.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sResultSetMetaData(response.substring(1));", config.getJdbcClassPrefix()));
		}else if(retType==int[].class){
			classBuf.append(INDENT3).append("String[] retstrs = response.substring(1).split(\",\");\n");	
			classBuf.append(INDENT3).append("int[] ret =new int[retstrs.length];\n");
			classBuf.append(INDENT3).append("for(int i=0; i<retstrs.length; i++){\n");
			classBuf.append(INDENT4).append("ret[i]=Integer.valueOf(retstrs[i]).intValue(); \n");
			classBuf.append(INDENT3).append("}\n");
			classBuf.append(INDENT3).append("return ret;\n");
		}else if(retType==ParameterMetaData.class){
			classBuf.append(INDENT3).append(String.format("return new %1$sParameterMetaData(response.substring(1));", config.getJdbcClassPrefix()));
		}else if(retType==Date.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}else{ \n");				
			if(pl==2){
				classBuf.append(INDENT4).append("Calendar cal = Calendar.getInstance();\n");	
				classBuf.append(INDENT4).append(" cal.setTimeInMillis(Long.valueOf(rawcol).longValue());\n");
				classBuf.append(INDENT4).append(" if(param1!=null)cal.setTimeZone(param1.getTimeZone());\n");
				classBuf.append(INDENT4).append("return new Date(cal.getTimeInMillis());\n"); 
			}else{
				classBuf.append(INDENT4).append("return new Date(Long.valueOf(rawcol).longValue());\n");	
			}
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==Time.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}else{ \n");				
			if(pl==2){
				classBuf.append(INDENT4).append("Calendar cal = Calendar.getInstance();\n");	
				classBuf.append(INDENT4).append(" cal.setTimeInMillis(Long.valueOf(rawcol).longValue());\n");
				classBuf.append(INDENT4).append(" if(param1!=null)cal.setTimeZone(param1.getTimeZone());\n");
				classBuf.append(INDENT4).append("return new Time(cal.getTimeInMillis());\n"); 
			}else{
				classBuf.append(INDENT4).append("return new Time(Long.valueOf(rawcol).longValue());\n");	
			}
							
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==Timestamp.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}else{ \n");				
			if(pl==2){
				classBuf.append(INDENT4).append("Calendar cal = Calendar.getInstance();\n");	
				classBuf.append(INDENT4).append(" cal.setTimeInMillis(Long.valueOf(rawcol).longValue());\n");
				classBuf.append(INDENT4).append(" if(param1!=null)cal.setTimeZone(param1.getTimeZone());\n");
				classBuf.append(INDENT4).append("return new Timestamp(cal.getTimeInMillis());\n"); 
			}else{
				classBuf.append(INDENT4).append("return new Timestamp(Long.valueOf(rawcol).longValue());\n");	
			}			
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==BigDecimal.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}else{ \n");				
			classBuf.append(INDENT4).append("return BigDecimal.valueOf(Long.valueOf(rawcol).longValue());\n");				
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==byte[].class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}else{ \n");				
			classBuf.append(INDENT4).append("return Base64.decodeBase64(rawcol);\n");				
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==String.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return rawcol;\n");
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==URL.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("URL retUrl = null;\n");
			classBuf.append(INDENT4).append("try{ retUrl = new URL(rawcol); }catch(Exception ex){ throw new SQLException(\"Invalid URL\", ex);};\n");
			classBuf.append(INDENT4).append("return retUrl;\n");
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==Reader.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return new StringReader(rawcol);\n");
			classBuf.append(INDENT3).append("}\n");
		}else if(retType==Object.class){
			classBuf.append(INDENT3).append("String rawcol = response.substring(1); \n");
			classBuf.append(INDENT3).append("if( rawcol==null || rawcol.length()==0){ \n");			
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}else{ \n");
			classBuf.append(INDENT4).append("return null;\n");
			classBuf.append(INDENT3).append("}\n");
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
	public static void main(String[] args) throws Exception {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		CallableStatementImplGenerator generator = new CallableStatementImplGenerator(config);		
		FileWriter fw = new FileWriter("target/tmp.txt");
		fw.write(generator.generate());
		fw.flush();
		fw.close();
		//System.out.println(generator.generate());

	}

}
