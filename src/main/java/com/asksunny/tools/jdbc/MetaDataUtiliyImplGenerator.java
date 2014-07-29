package com.asksunny.tools.jdbc;

import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MetaDataUtiliyImplGenerator implements JDBCImplClassGenerator {

	private JDBCDriverGeneratorConfig config;

	
	
	public MetaDataUtiliyImplGenerator(JDBCDriverGeneratorConfig config) {
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
		classBuf.append(String.format("%s%s%s", IMPORT, DatabaseMetaData.class.getName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, ResultSetMetaData.class.getName(), STATEMENT_BREAK));		
		classBuf.append(String.format("%s%s%s", IMPORT, SQLException.class.getName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, HashMap.class.getName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, List.class.getName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, ArrayList.class.getName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, ParameterMetaData.class.getName(), STATEMENT_BREAK));
		
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public final class %1$s%2$s implements  %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				"MetaDataUtil", JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateDeclaration();
		classBuf.append(BLOCK_BREAK);
		generateConstructor();
		classBuf.append(BLOCK_BREAK);
		generateCommons();
		generateRSMD2String();
		generateString2RSMD();
		generateDBMD2String();
		generateString2DBMD();
		
		generatePARAMMD2String();
		generateString2PARAMMD();
		
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	
	protected void generateDeclaration()
	{
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<Integer, String> rs_int2cmdMap = %1$sJDBCVTableMaps.RSMD_I2FN_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<String, Integer> rs_cmd2intMap = %1$sJDBCVTableMaps.RSMD_FN2I_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<Integer, String> db_int2cmdMap = %1$sJDBCVTableMaps.DBMD_I2FN_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<String, Integer> db_cmd2intMap = %1$sJDBCVTableMaps.DBMD_FN2I_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<Integer, String> prm_int2cmdMap = %1$sJDBCVTableMaps.PARAMMD_I2FN_MAP;\n", config.getJdbcClassPrefix()));
		classBuf.append(INDENT1).append(String.format("private final static java.util.HashMap<String, Integer> prm_cmd2intMap = %1$sJDBCVTableMaps.PARAMMD_FN2I_MAP;\n", config.getJdbcClassPrefix()));
	}
	
	protected void generateConstructor()
	{		
		
	}
	
	protected void generateRSMD2String()
	{		
		classBuf.append(INDENT1).append("public static String convert2String(ResultSetMetaData rsmd)  throws SQLException {\n\n");
		classBuf.append(INDENT2).append("StringBuilder dbmsbuf = new StringBuilder()").append(STATEMENT_BREAK);
		classBuf.append(INDENT2).append("int colcount = rsmd.getColumnCount()").append(STATEMENT_BREAK);
		classBuf.append(INDENT2).append("for (int i = 0; i < colcount; i++) {\n");
		classBuf.append(INDENT3).append("int colidx = i+1").append(STATEMENT_BREAK);
		
		Filter<Method> mfilter = new Filter<Method>() {			
			@Override
			public boolean match(Method method) {				
				return method.getParameterTypes().length==1 && method.getParameterTypes()[0]==int.class;
			}
		};		
		List<Method> methods1 = ArraysUtil.filterFunction(ResultSetMetaData.class.getMethods(), mfilter);		
		Method[]  methods = new Method[methods1.size()];
		methods1.toArray(methods);
		
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];							
			classBuf.append(INDENT3).append(String.format("dbmsbuf.append(rs_cmd2intMap.get(\"%1$s\"))", method.getName())).append(STATEMENT_BREAK);
			classBuf.append(INDENT3).append(String.format("dbmsbuf.append(FIELD_DELIMITER)", method.getName())).append(STATEMENT_BREAK);
			if(method.getExceptionTypes().length>0){
				classBuf.append(INDENT3).append(String.format("try{\n", method.getName()));			
				classBuf.append(INDENT4).append(String.format("dbmsbuf.append(rsmd.%1$s(colidx))", method.getName())).append(STATEMENT_BREAK);
				classBuf.append(INDENT3).append(String.format("} catch(SQLException sex) {\n", method.getName()));
				classBuf.append(INDENT4).append(String.format(";//ignore it \n", method.getName()));
				classBuf.append(INDENT3).append(String.format("} \n", method.getName()));
			}else{
				classBuf.append(INDENT4).append(String.format("dbmsbuf.append(rsmd.%1$s(colidx))", method.getName())).append(STATEMENT_BREAK);
			}
			if(i < methods.length-1) classBuf.append(INDENT3).append(String.format("dbmsbuf.append(FIELD_DELIMITER)", method.getName())).append(STATEMENT_BREAK);
				//classBuf.append(INDENT3).append(method.getName()).append(STATEMENT_BREAK);
		}		
		classBuf.append(INDENT3).append(" if (colidx<colcount) dbmsbuf.append(RECORD_DELIMITER) ").append(STATEMENT_BREAK);		
		classBuf.append(INDENT2).append(BLOCK_CLOSE);		
		classBuf.append(INDENT2).append("return dbmsbuf.toString()").append(STATEMENT_BREAK);		
		classBuf.append(INDENT1).append(BLOCK_CLOSE);
	}
	
	protected void generateString2RSMD()
	{
		classBuf.append(INDENT1).append("public static List<HashMap<String, String>> convert2ResultSetMetaData(String metadata)  throws SQLException {\n\n");
		classBuf.append(INDENT2).append("List<HashMap<String, String>> rsMds = new ArrayList<HashMap<String, String>>();\n");
		classBuf.append(INDENT2).append("HashMap<String, String> rsmdmap = new HashMap<String, String>();\n");
		classBuf.append(INDENT2).append("int len = metadata.length();\n");
		classBuf.append(INDENT2).append("StringBuilder buf = new StringBuilder();\n");
		classBuf.append(INDENT2).append("for (int i = 0; i < len; i++) {\n");
		classBuf.append(INDENT2).append("	Stat stat = readField(buf, metadata, i, len, FIELD_DELIMITER, RECORD_DELIMITER);\n");
		classBuf.append(INDENT2).append("	i = stat.offset;\n");
		classBuf.append(INDENT2).append("	String key = buf.toString();\n");
		classBuf.append(INDENT2).append("	if(key.length()>0){\n");
		classBuf.append(INDENT2).append("		buf.setLength(0);\n");
		classBuf.append(INDENT2).append("		key = rs_int2cmdMap.get(Integer.valueOf(key));\n");
		classBuf.append(INDENT2).append("		i++;\n");
		classBuf.append(INDENT2).append("		stat = readField(buf, metadata, i, len,  FIELD_DELIMITER, RECORD_DELIMITER);\n");
		classBuf.append(INDENT2).append("		i = stat.offset;\n");
		classBuf.append(INDENT2).append("		String val = buf.toString();\n");
		classBuf.append(INDENT2).append("		buf.setLength(0);\n");
		classBuf.append(INDENT2).append("		rsmdmap.put(key, val);\n");
		classBuf.append(INDENT2).append("	}			\n");
		classBuf.append(INDENT2).append("	if(stat.endOfRecord || key.length()==0){\n");
		classBuf.append(INDENT2).append("		rsMds.add(rsmdmap);\n");
		classBuf.append(INDENT2).append("		rsmdmap = new HashMap<String, String>();\n");
		classBuf.append(INDENT2).append("	}\n");
		classBuf.append(INDENT2).append("}		\n");
		classBuf.append(INDENT2).append("return rsMds;\n");		
		classBuf.append(INDENT1).append(BLOCK_CLOSE);
		
		
		
	}
	
	protected void generateCommons()
	{
		classBuf.append(INDENT1).append("protected static Stat readField(StringBuilder buf, String dbmd, int offset,int len, char delimiter, char delimiter2) {\n\n");
		classBuf.append(INDENT1).append("	int i = offset;\n");
		classBuf.append(INDENT1).append("	Stat stat = new Stat();\n");
		classBuf.append(INDENT1).append("	for (; i < len; i++) {\n");
		classBuf.append(INDENT1).append("		char c = dbmd.charAt(i);\n");
		classBuf.append(INDENT1).append("		if (c == delimiter ){\n");
		classBuf.append(INDENT1).append("			break;\n");
		classBuf.append(INDENT1).append("		} else if(delimiter2 > 0 && c == delimiter2){\n");
		classBuf.append(INDENT1).append("			stat.endOfRecord = true;\n");
		classBuf.append(INDENT1).append("			break;\n");
		classBuf.append(INDENT1).append("		}else{\n");
		classBuf.append(INDENT1).append("			buf.append(c);\n");
		classBuf.append(INDENT1).append("		}				\n");
		classBuf.append(INDENT1).append("		if(i==len-1) stat.endOfRecord = true;\n");		
		classBuf.append(INDENT1).append("	}\n");
		classBuf.append(INDENT1).append("	stat.offset = i;\n");
		classBuf.append(INDENT1).append("	return stat;\n");
		classBuf.append(INDENT1).append("}\n");
		classBuf.append(INDENT1).append("\n");
		classBuf.append(INDENT1).append("protected static class Stat\n");
		classBuf.append(INDENT1).append("{\n");
		classBuf.append(INDENT1).append("	public int offset;\n");
		classBuf.append(INDENT1).append("	public boolean endOfRecord = false;\n");
		classBuf.append(INDENT1).append("}\n");
		
		classBuf.append(INDENT1).append("public static HashMap<String, Class<?>> convert2TypeMap(String dbmd) throws SQLException {\n");
		classBuf.append(INDENT1).append("	HashMap<String, Class<?>> dbmdmap = new HashMap<>();\n");
		classBuf.append(INDENT1).append("	int len = dbmd.length();\n");
		classBuf.append(INDENT1).append("	StringBuilder buf = new StringBuilder();\n");
		classBuf.append(INDENT1).append("	for (int i = 0; i < len; i++) {\n");
		classBuf.append(INDENT1).append("		Stat stat = readField(buf, dbmd, i, len, FIELD_DELIMITER, RECORD_DELIMITER);\n");
		classBuf.append(INDENT1).append("		if(stat.endOfRecord) continue;\n");
		classBuf.append(INDENT1).append("		i = stat.offset;\n");
		classBuf.append(INDENT1).append("		String key = buf.toString();	\n");
		classBuf.append(INDENT1).append("		buf.setLength(0);			\n");
		classBuf.append(INDENT1).append("		i++;\n");
		classBuf.append(INDENT1).append("		stat = readField(buf, dbmd, i, len, RECORD_DELIMITER, (char) -1);\n");
		classBuf.append(INDENT1).append("		String val = buf.toString();\n");
		classBuf.append(INDENT1).append("		buf.setLength(0);\n");
		classBuf.append(INDENT1).append("		i = stat.offset;\n");
		classBuf.append(INDENT1).append("		try{\n");
		classBuf.append(INDENT1).append("			Class<?> cl = Class.forName(val);\n");
		classBuf.append(INDENT1).append("			dbmdmap.put(key, cl);	\n");
		classBuf.append(INDENT1).append("		}catch(Exception ex){\n");
		classBuf.append(INDENT1).append("			throw new SQLException(String.format(\"Class not found exception [%s]\", val));\n");
		classBuf.append(INDENT1).append("		}\n");
		classBuf.append(INDENT1).append("	}\n");
		classBuf.append(INDENT1).append("	return dbmdmap;\n");
		classBuf.append(INDENT1).append("}\n");
		
	}
	
	protected void generateDBMD2String()
	{		
		classBuf.append(INDENT1).append("public static String convert2String(DatabaseMetaData dbmd) throws SQLException {\n\n");
		classBuf.append(INDENT2).append("StringBuilder dbmsbuf = new StringBuilder()").append(STATEMENT_BREAK);
		Filter<Method> mfilter = new Filter<Method>() {			
			@Override
			public boolean match(Method method) {				
				return method.getParameterTypes().length==0 && method.getReturnType()!=ResultSet.class;
			}
		};		
		List<Method> methods1 = ArraysUtil.filterFunction(DatabaseMetaData.class.getMethods(), mfilter);		
		Method[]  methods = new Method[methods1.size()];
		methods1.toArray(methods);
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];							
			classBuf.append(INDENT3).append(String.format("dbmsbuf.append(db_cmd2intMap.get(\"%1$s\"))", method.getName())).append(STATEMENT_BREAK);
			classBuf.append(INDENT3).append(String.format("dbmsbuf.append(FIELD_DELIMITER)", method.getName())).append(STATEMENT_BREAK);
			if(method.getExceptionTypes().length>0){
				classBuf.append(INDENT3).append(String.format("try{\n", method.getName()));			
				classBuf.append(INDENT4).append(String.format("dbmsbuf.append(dbmd.%1$s())", method.getName())).append(STATEMENT_BREAK);
				classBuf.append(INDENT3).append(String.format("} catch(SQLException sex) {\n", method.getName()));
				classBuf.append(INDENT4).append(String.format(";//ignore it \n", method.getName()));
				classBuf.append(INDENT3).append(String.format("} \n", method.getName()));
			}else{
				classBuf.append(INDENT4).append(String.format("dbmsbuf.append(dbmd.%1$s())", method.getName())).append(STATEMENT_BREAK);
			}
			classBuf.append(INDENT3).append(String.format("dbmsbuf.append(RECORD_DELIMITER)", method.getName())).append(STATEMENT_BREAK);
				//classBuf.append(INDENT3).append(method.getName()).append(STATEMENT_BREAK);
		}	
		
		classBuf.append(INDENT2).append("return dbmsbuf.toString()").append(STATEMENT_BREAK);	
		classBuf.append(INDENT1).append(BLOCK_CLOSE);
	}
	
	
	public void generateString2DBMD()
	{
		classBuf.append(INDENT1).append("public static HashMap<String, String> convert2DatabaseMetaData(String dbmd) throws SQLException {\n");
		classBuf.append(INDENT1).append("	HashMap<String, String> dbmdmap = new HashMap<>();\n");
		classBuf.append(INDENT1).append("	int len = dbmd.length();\n");
		classBuf.append(INDENT1).append("	StringBuilder buf = new StringBuilder();\n");
		classBuf.append(INDENT1).append("	for (int i = 0; i < len; i++) {\n");
		classBuf.append(INDENT1).append("		Stat stat = readField(buf, dbmd, i, len, FIELD_DELIMITER, RECORD_DELIMITER);\n");
		classBuf.append(INDENT1).append("		if(stat.endOfRecord) continue;\n");
		classBuf.append(INDENT1).append("		i = stat.offset;\n");
		classBuf.append(INDENT1).append("		String key = buf.toString();			\n");
		classBuf.append(INDENT2).append("		key = db_int2cmdMap.get(Integer.valueOf(key));\n");		
		classBuf.append(INDENT1).append("		buf.setLength(0);			\n");
		classBuf.append(INDENT1).append("		i++;\n");
		classBuf.append(INDENT1).append("		stat = readField(buf, dbmd, i, len, RECORD_DELIMITER, (char) -1);\n");
		classBuf.append(INDENT1).append("		String val = buf.toString();\n");
		classBuf.append(INDENT1).append("		buf.setLength(0);\n");
		classBuf.append(INDENT1).append("		i = stat.offset;\n");	
		classBuf.append(INDENT1).append("		dbmdmap.put(key, val);			\n");
		classBuf.append(INDENT1).append("	}\n");
		classBuf.append(INDENT1).append("	return dbmdmap;\n");
		classBuf.append(INDENT1).append("}\n");
	}
	
	
	protected void generatePARAMMD2String()
	{		
		
		classBuf.append(INDENT1).append("public static String convert2String(ParameterMetaData prmmd)  throws SQLException {\n\n");
		classBuf.append(INDENT2).append("StringBuilder dbmsbuf = new StringBuilder()").append(STATEMENT_BREAK);
		classBuf.append(INDENT2).append("int pcount = prmmd.getParameterCount()").append(STATEMENT_BREAK);
		classBuf.append(INDENT2).append("for (int i = 0; i <pcount ; i++) {\n");
		classBuf.append(INDENT3).append("int colidx = i+1").append(STATEMENT_BREAK);
		
		Filter<Method> mfilter = new Filter<Method>() {			
			@Override
			public boolean match(Method method) {				
				return method.getParameterTypes().length==1 && method.getParameterTypes()[0]==int.class;
			}
		};		
		List<Method> methods1 = ArraysUtil.filterFunction(ParameterMetaData.class.getMethods(), mfilter);		
		Method[]  methods = new Method[methods1.size()];
		methods1.toArray(methods);		
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];							
			classBuf.append(INDENT3).append(String.format("dbmsbuf.append(prm_cmd2intMap.get(\"%1$s\"))", method.getName())).append(STATEMENT_BREAK);
			classBuf.append(INDENT3).append(String.format("dbmsbuf.append(FIELD_DELIMITER)", method.getName())).append(STATEMENT_BREAK);
			if(method.getExceptionTypes().length>0){
				classBuf.append(INDENT3).append(String.format("try{\n", method.getName()));			
				classBuf.append(INDENT4).append(String.format("dbmsbuf.append(prmmd.%1$s(colidx))", method.getName())).append(STATEMENT_BREAK);
				classBuf.append(INDENT3).append(String.format("} catch(SQLException sex) {\n", method.getName()));
				classBuf.append(INDENT4).append(String.format(";//ignore it \n", method.getName()));
				classBuf.append(INDENT3).append(String.format("} \n", method.getName()));
			}else{
				classBuf.append(INDENT4).append(String.format("dbmsbuf.append(rsmd.%1$s(colidx))", method.getName())).append(STATEMENT_BREAK);
			}
			if(i < methods.length-1) classBuf.append(INDENT3).append(String.format("dbmsbuf.append(FIELD_DELIMITER)", method.getName())).append(STATEMENT_BREAK);
				//classBuf.append(INDENT3).append(method.getName()).append(STATEMENT_BREAK);
		}		
		classBuf.append(INDENT3).append(" if (colidx<pcount) dbmsbuf.append(RECORD_DELIMITER) ").append(STATEMENT_BREAK);		
		classBuf.append(INDENT2).append(BLOCK_CLOSE);		
		classBuf.append(INDENT2).append("return dbmsbuf.toString()").append(STATEMENT_BREAK);		
		classBuf.append(INDENT1).append(BLOCK_CLOSE);
				
	}
	
	
	public void generateString2PARAMMD()
	{
		
		
		classBuf.append(INDENT1).append("public static List<HashMap<String, String>> convert2ParameterMetaData(String metadata)  throws SQLException {\n\n");
		classBuf.append(INDENT2).append("List<HashMap<String, String>> rsMds = new ArrayList<HashMap<String, String>>();\n");
		classBuf.append(INDENT2).append("HashMap<String, String> rsmdmap = new HashMap<String, String>();\n");
		classBuf.append(INDENT2).append("int len = metadata.length();\n");
		classBuf.append(INDENT2).append("StringBuilder buf = new StringBuilder();\n");
		classBuf.append(INDENT2).append("for (int i = 0; i < len; i++) {\n");
		classBuf.append(INDENT2).append("	Stat stat = readField(buf, metadata, i, len, FIELD_DELIMITER, RECORD_DELIMITER);\n");
		classBuf.append(INDENT2).append("	i = stat.offset;\n");
		classBuf.append(INDENT2).append("	String key = buf.toString();\n");
		classBuf.append(INDENT2).append("	if(key.length()>0){\n");
		classBuf.append(INDENT2).append("		buf.setLength(0);\n");
		classBuf.append(INDENT2).append("		key = prm_int2cmdMap.get(Integer.valueOf(key));\n");
		classBuf.append(INDENT2).append("		i++;\n");
		classBuf.append(INDENT2).append("		stat = readField(buf, metadata, i, len,  FIELD_DELIMITER, RECORD_DELIMITER);\n");
		classBuf.append(INDENT2).append("		i = stat.offset;\n");
		classBuf.append(INDENT2).append("		String val = buf.toString();\n");
		classBuf.append(INDENT2).append("		buf.setLength(0);\n");
		classBuf.append(INDENT2).append("		rsmdmap.put(key, val);\n");
		classBuf.append(INDENT2).append("	}			\n");
		classBuf.append(INDENT2).append("	if(stat.endOfRecord || key.length()==0){\n");
		classBuf.append(INDENT2).append("		rsMds.add(rsmdmap);\n");
		classBuf.append(INDENT2).append("		rsmdmap = new HashMap<String, String>();\n");
		classBuf.append(INDENT2).append("	}\n");
		classBuf.append(INDENT2).append("}		\n");
		classBuf.append(INDENT2).append("return rsMds;\n");		
		classBuf.append(INDENT1).append(BLOCK_CLOSE);		
		
	}
	
	
	protected void generateNonResultMethodDefault(Method method)
	{		
		
		System.err.println(String.format("No template for %s: %d", method.getName(), method.getParameterTypes().length));
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		MetaDataUtiliyImplGenerator generator = new MetaDataUtiliyImplGenerator(config);		
		System.out.println(generator.generate());

	}

}
