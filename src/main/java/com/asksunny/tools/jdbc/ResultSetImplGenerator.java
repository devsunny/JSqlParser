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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;

public class ResultSetImplGenerator implements JDBCImplClassGenerator {

	private JDBCDriverGeneratorConfig config;

	
	
	public ResultSetImplGenerator(JDBCDriverGeneratorConfig config) {
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
		
		
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements %2$s, %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				ResultSet.class.getSimpleName(), JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateDeclare();
		generateConstructor();
		generateMethods();		
		generateWrapMethod();
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	protected void generateDeclare()
	{
		classBuf.append(INDENT1).append(String.format("private final static HashMap<Integer, String> rs_int2cmdMap = %1$sJDBCVTableMaps.RS_I2FN_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static HashMap<String, Integer> rs_cmd2intMap = %1$sJDBCVTableMaps.RS_FN2I_MAP;\n", config.getJdbcClassPrefix()));

		classBuf.append(BLOCK_BREAK);
		classBuf.append(INDENT1).append(String.format("private %1$s%2$s connection=null;\n", config.getJdbcClassPrefix(), Connection.class.getSimpleName()));
		classBuf.append(INDENT1).append(String.format("private %2$s statement=null;\n", config.getJdbcClassPrefix(), Statement.class.getSimpleName()));
		classBuf.append(INDENT1).append(String.format("private %1$s%2$s rsMetaData=null;\n", config.getJdbcClassPrefix(), ResultSetMetaData.class.getSimpleName()));	
		classBuf.append(INDENT1).append(String.format("private String metaString=null;\n"));
		classBuf.append(INDENT1).append(String.format("private String[] rawrow=null;\n"));	
		classBuf.append(INDENT1).append(String.format("private boolean wasNullValue = false;\n"));	
		classBuf.append(INDENT1).append(String.format("private HashMap<String, Integer> col_idx_map = new HashMap<String, Integer>();\n"));

		classBuf.append(BLOCK_BREAK);
	}
	
	protected void generateConstructor()
	{		
		classBuf.append(INDENT1).append("public ").append(" ");
		classBuf.append(String.format("%1$s%4$s(%1$s%2$s conn, %3$s stmt, String metadata)", config.getJdbcClassPrefix(), Connection.class.getSimpleName(), Statement.class.getSimpleName(), ResultSet.class.getSimpleName()));
		classBuf.append(" throws SQLException {\n");
		classBuf.append(INDENT2).append(String.format("this.connection=conn;\n"));
		classBuf.append(INDENT2).append(String.format("this.statement=stmt;\n"));
		classBuf.append(INDENT2).append(String.format("this.metaString=metadata;\n"));
		classBuf.append(INDENT2).append(String.format("this.rsMetaData = new %1$sResultSetMetaData(this.metaString);\n", config.getJdbcClassPrefix()));	
		classBuf.append(INDENT2).append("int colcount = this.rsMetaData.getColumnCount();\n");
		classBuf.append(INDENT2).append("for (int i=0; i<colcount; i++) {\n");
		classBuf.append(INDENT2).append("	String colname = this.rsMetaData.getColumnName(i+1);\n");
		classBuf.append(INDENT2).append("	col_idx_map.put(colname.toUpperCase(), (i+1));\n");
		classBuf.append(INDENT2).append("}\n");
		classBuf.append(INDENT1).append("}\n\n");	
		classBuf.append(INDENT1).append("public ").append(" ");
		classBuf.append(String.format("%1$s%4$s(%1$s%2$s conn, String metadata)", config.getJdbcClassPrefix(), Connection.class.getSimpleName(), Statement.class.getSimpleName(), ResultSet.class.getSimpleName()));
		classBuf.append(" throws SQLException {\n");
		classBuf.append(INDENT2).append("this(conn, null, metadata);\n");
		classBuf.append(INDENT1).append("}\n\n");			
		
	}
	
	
	
	
	
	
	protected void generateMethods()
	{
		
		Filter<Method> colIdxMethodFilter = new Filter<Method>() {			
			@Override
			public boolean match(Method method) {				
				return (!method.getName().equals("getRef"))  
						&& (!method.getName().equals("getBlob"))  
						&& (!method.getName().equals("getClob"))  
						&& (!method.getName().equals("getNClob")) 
						&& (!method.getName().equals("getRowId")) 
						&& (!method.getName().equals("getSQLXML")) 
						
						&& method.getName().startsWith("get") 
						&& method.getParameterTypes().length>0 
						&& method.getParameterTypes()[0]==int.class;
			}
		};
		List<Method> methods = ArraysUtil.filterFunction(ResultSet.class.getMethods(), colIdxMethodFilter);
		final Set<String> colMethodNames = new HashSet<String>();
		for (Method method : methods) {
			colMethodNames.add(method.getName());			
		}
		
		
		
		for (Method method : ResultSet.class.getMethods()) {
			if(method.getName().equals("unwrap") 
					|| method.getName().equals("isWrapperFor") 
					|| method.getName().equals("getMetaData") 
					|| method.getName().equals("getStatement")
					|| method.getName().equals("wasNull")){
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
						|| method.getName().startsWith("getRef") 
						|| method.getName().startsWith("getBlob") 
						|| method.getName().startsWith("getClob") 
						|| method.getName().startsWith("getNClob") 
						|| method.getName().equals("getRowId") 
						|| method.getName().equals("getSQLXML")
						){
					generateNotSupportFeatureMethod(method);
				}else{					
					generateRemoteMethods(method);
				}				
			}else{
				generateColumnMethod(method);
			}
		}
		
	}
	
	protected void generateColumnMethod(Method method)
	{
		Class<?> retType = method.getReturnType();
		int pl = method.getParameterTypes().length;	
		if(retType==Array.class){
			classBuf.append(INDENT1).append("public ").append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("(");
			for(int i=0; i<pl; i++)
			{
				classBuf.append(method.getParameterTypes()[i].getSimpleName());
				classBuf.append(" ").append(String.format("param%d", i));
				if( i<pl-1) classBuf.append(",");
			}
			classBuf.append(") throws SQLException {\n");	
			classBuf.append(INDENT2).append(String.format("throw new SQLFeatureNotSupportedException(\"%1$s(%2$s)\");\n", method.getName(), Arrays.asList(method.getParameterTypes())));
			classBuf.append(INDENT1).append("}\n\n");
			return;
		}		
		if(pl==1 
				|| ( pl==2 && method.getName().equals("getBigDecimal") && method.getParameterTypes()[1] == int.class) 				
				|| (pl==2 && method.getParameterTypes()[1] == Calendar.class)){
			classBuf.append(INDENT1).append("public ").append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("(");
			for(int i=0; i<pl; i++)
			{
				classBuf.append(method.getParameterTypes()[i].getSimpleName());
				classBuf.append(" ").append(String.format("param%d", i));
				if( i<pl-1) classBuf.append(",");
			}
			classBuf.append(") throws SQLException {\n");	
			classBuf.append(INDENT2).append(String.format("this.wasNullValue = false;\n"));
			if( method.getParameterTypes()[0]==int.class)
			{
				classBuf.append(INDENT2).append("int colidx = param0;\n");								
			}else{
				classBuf.append(INDENT2).append("int colidx = col_idx_map.get(param0.toUpperCase());\n");				
			}
			classBuf.append(INDENT2).append("String rawcol = rawrow[colidx-1];\n");
			if(retType==int.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return 0;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("return Integer.valueOf(rawcol);\n");
				classBuf.append(INDENT2).append("}\n");
			} else if(retType==long.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return 0;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("return Long.valueOf(rawcol);\n");
				classBuf.append(INDENT2).append("}\n");
			} else if(retType==short.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return 0;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("return Short.valueOf(rawcol);\n");
				classBuf.append(INDENT2).append("}\n");
			} else if(retType==double.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return 0;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("return Double.valueOf(rawcol);\n");
				classBuf.append(INDENT2).append("}\n");
			} else if(retType==float.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return 0;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("return Float.valueOf(rawcol);\n");
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==boolean.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return false;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("return Boolean.valueOf(rawcol);\n");
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==byte.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return 0;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("return ((byte)Integer.valueOf(rawcol).intValue());\n");
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==String.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("return rawcol;\n");
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==URL.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("URL retUrl = null;\n");
				classBuf.append(INDENT3).append("try{ retUrl = new URL(rawcol); }catch(Exception ex){ throw new SQLException(\"Invalid URL\", ex);};\n");
				classBuf.append(INDENT3).append("return retUrl;\n");
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==Object.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");
				classBuf.append(INDENT3).append("String nclazzname = this.rsMetaData.getColumnClassName(colidx);\n");						
				classBuf.append(INDENT3).append("return castObject(rawcol, nclazzname);\n");
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==byte[].class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");				
				classBuf.append(INDENT3).append("return Base64.decodeBase64(rawcol);\n");				
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==Date.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");				
				if(pl==2){
					classBuf.append(INDENT3).append("Calendar cal = Calendar.getInstance();\n");	
					classBuf.append(INDENT3).append(" cal.setTimeInMillis(Long.valueOf(rawcol).longValue());\n");
					classBuf.append(INDENT3).append(" if(param1!=null)cal.setTimeZone(param1.getTimeZone());\n");
					classBuf.append(INDENT3).append("return new Date(cal.getTimeInMillis());\n"); 
				}else{
					classBuf.append(INDENT3).append("return new Date(Long.valueOf(rawcol).longValue());\n");	
				}
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==Time.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");				
				if(pl==2){
					classBuf.append(INDENT3).append("Calendar cal = Calendar.getInstance();\n");	
					classBuf.append(INDENT3).append(" cal.setTimeInMillis(Long.valueOf(rawcol).longValue());\n");
					classBuf.append(INDENT3).append(" if(param1!=null)cal.setTimeZone(param1.getTimeZone());\n");
					classBuf.append(INDENT3).append("return new Time(cal.getTimeInMillis());\n"); 
				}else{
					classBuf.append(INDENT3).append("return new Time(Long.valueOf(rawcol).longValue());\n");	
				}
								
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==Timestamp.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");				
				if(pl==2){
					classBuf.append(INDENT3).append("Calendar cal = Calendar.getInstance();\n");	
					classBuf.append(INDENT3).append(" cal.setTimeInMillis(Long.valueOf(rawcol).longValue());\n");
					classBuf.append(INDENT3).append(" if(param1!=null)cal.setTimeZone(param1.getTimeZone());\n");
					classBuf.append(INDENT3).append("return new Timestamp(cal.getTimeInMillis());\n"); 
				}else{
					classBuf.append(INDENT3).append("return new Timestamp(Long.valueOf(rawcol).longValue());\n");	
				}			
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==BigDecimal.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");				
				classBuf.append(INDENT3).append("return BigDecimal.valueOf(Long.valueOf(rawcol).longValue());\n");				
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==InputStream.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");				
				classBuf.append(INDENT3).append("return new ByteArrayInputStream(Base64.decodeBase64(rawcol));\n");				
				classBuf.append(INDENT2).append("}\n");
			}else if(retType==Reader.class){
				classBuf.append(INDENT2).append("if( rawcol==null || rawcol.length()==0){ \n");
				classBuf.append(INDENT3).append("wasNullValue = true; \n");
				classBuf.append(INDENT3).append("return null;\n");
				classBuf.append(INDENT2).append("}else{ \n");				
				classBuf.append(INDENT3).append("return new StringReader(rawcol);\n");				
				classBuf.append(INDENT2).append("}\n");
			}				
			classBuf.append(INDENT1).append("}\n\n");	
		}else if( pl==2 && method.getName().equals("getObject")){
			if(method.getParameterTypes()[1]==Map.class){
				classBuf.append(INDENT1).append("public ").append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("(");
				classBuf.append(method.getParameterTypes()[0].getSimpleName());
				classBuf.append(" ").append(String.format("param%d", 0));
				classBuf.append(", ");
				classBuf.append("Map<String, Class<?>> map");
			}else{
				classBuf.append(INDENT1).append("public <T> T ").append(method.getName()).append("(");
				classBuf.append(method.getParameterTypes()[0].getSimpleName());
				classBuf.append(" ").append(String.format("param%d", 0));
				classBuf.append(", ");
				classBuf.append("Class<T> type");
			}			
			classBuf.append(") throws SQLException {\n");	
			classBuf.append(INDENT2).append(String.format("this.wasNullValue = false;\n"));
			if( method.getParameterTypes()[0]==int.class)
			{
				classBuf.append(INDENT2).append("int colidx = param0;\n");								
			}else{
				classBuf.append(INDENT2).append("int colidx = col_idx_map.get(param0.toUpperCase());\n");				
			}
			classBuf.append(INDENT2).append("String rawcol = rawrow[colidx-1];\n");	
			if(method.getParameterTypes()[1]==Map.class){
				classBuf.append(INDENT2).append(" String sqlTypeName = this.rsMetaData.getColumnTypeName(colidx);\n");
				classBuf.append(INDENT2).append(" Class<?> cls = map.get(sqlTypeName);\n");
				classBuf.append(INDENT2).append("return castObject(rawcol, cls);\n");				
			}else{
				classBuf.append(INDENT2).append("String clazzName = type.getName();\n");
				classBuf.append(INDENT2).append("Object obj =  castObject(rawcol, clazzName);\n");
				classBuf.append(INDENT2).append("return type.cast(obj);\n");
			}
			classBuf.append(INDENT1).append("}\n\n");	
			
		}else{
			System.err.println(String.format("No template for %s %s:(%s)",method.getReturnType().getSimpleName(), method.getName(), Arrays.asList(method.getParameterTypes())));
		}
		
		
		
	}
	
	
	protected void generateResultMethod(Method method)
	{		
		
		System.err.println(String.format("No template for %s %s:(%s)",method.getReturnType().getSimpleName(), method.getName(), Arrays.asList(method.getParameterTypes())));
		
		
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
		classBuf.append(INDENT2).append(String.format("cmdBuf.append(rs_cmd2intMap.get(\"%s\"));\n", method.getName()));
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
		classBuf.append(INDENT1).append("public ResultSetMetaData getMetaData() throws SQLException {		\n");
		classBuf.append(INDENT1).append(String.format("	return rsMetaData;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append("}\n");
		classBuf.append(INDENT1).append("\n");
		classBuf.append(INDENT1).append("@Override\n");
		classBuf.append(INDENT1).append("public Statement getStatement() throws SQLException {		\n");
		classBuf.append(INDENT1).append("	return this.statement;\n");
		classBuf.append(INDENT1).append("}\n");
		classBuf.append(INDENT1).append("@Override\n");
		classBuf.append(INDENT1).append("public boolean wasNull() throws SQLException {\n");
		classBuf.append(INDENT1).append("	return this.wasNullValue;\n");
		classBuf.append(INDENT1).append("}\n\n");
		classBuf.append(INDENT1).append("public Object castObject(String rawcol, String clazzName) throws SQLException\n");
		classBuf.append(INDENT1).append("{\n");
		classBuf.append(INDENT1).append("	if(clazzName.equals(String.class.getName())){\n");
		classBuf.append(INDENT1).append("		return rawcol;\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(Integer.class.getName())){\n");
		classBuf.append(INDENT1).append("		return Integer.valueOf(rawcol);\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(Long.class.getName())){\n");
		classBuf.append(INDENT1).append("		return Integer.valueOf(rawcol);\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(Double.class.getName())){\n");
		classBuf.append(INDENT1).append("		return Integer.valueOf(rawcol);\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(BigDecimal.class.getName())){\n");
		classBuf.append(INDENT1).append("		return BigDecimal.valueOf(Double.valueOf(rawcol));\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(Date.class.getName())){\n");
		classBuf.append(INDENT1).append("		return new Date(Long.valueOf(rawcol));\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(java.util.Date.class.getName())){\n");
		classBuf.append(INDENT1).append("		java.util.Date d = new java.util.Date();\n");
		classBuf.append(INDENT1).append("		d.setTime(Long.valueOf(rawcol));\n");
		classBuf.append(INDENT1).append("		return d;\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(Time.class.getName())){\n");
		classBuf.append(INDENT1).append("		return new Time(Long.valueOf(rawcol));\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(java.sql.Timestamp.class.getName())){\n");
		classBuf.append(INDENT1).append("		return new java.sql.Timestamp(Long.valueOf(rawcol));\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(Calendar.class.getName())){\n");
		classBuf.append(INDENT1).append("		Calendar cal = Calendar.getInstance();\n");
		classBuf.append(INDENT1).append("		cal.setTimeInMillis(Long.valueOf(rawcol));\n");
		classBuf.append(INDENT1).append("		return cal;\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(Byte.class.getName())){\n");
		classBuf.append(INDENT1).append("		return Byte.valueOf(((byte)Integer.valueOf(rawcol).intValue()));\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(Float.class.getName())){\n");
		classBuf.append(INDENT1).append("		return Float.valueOf(rawcol);\n");
		classBuf.append(INDENT1).append("	}else if(clazzName.equals(Short.class.getName())){\n");
		classBuf.append(INDENT1).append("		return Short.valueOf(rawcol);\n");
		classBuf.append(INDENT1).append("	}else{\n");
		classBuf.append(INDENT1).append("		throw new SQLException(\"Failed to cast value to \" + clazzName);\n");
		classBuf.append(INDENT1).append("	}		\n");
		classBuf.append(INDENT1).append("}\n\n");
		classBuf.append(INDENT1).append(" public <T> T castObject(String rawcol, Class<T> type) throws SQLException\n");
		classBuf.append(INDENT1).append("{ \n");
		classBuf.append(INDENT1).append("	if(type==null) throw new SQLException(\"Java type must be specified\");\n");
		classBuf.append(INDENT1).append("	String clazzName = type.getName();\n");
		classBuf.append(INDENT1).append("	Object obj =  castObject(rawcol, clazzName);\n");
		classBuf.append(INDENT1).append("	return type.cast(obj);\n");
		classBuf.append(INDENT1).append("}\n");
		
		
	}
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		ResultSetImplGenerator generator = new ResultSetImplGenerator(config);		
		System.out.println(generator.generate());

	}

}
