package com.asksunny.tools.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ParameterMetaDataImplGenerator implements JDBCImplClassGenerator {

	private JDBCDriverGeneratorConfig config;

	
	
	public ParameterMetaDataImplGenerator(JDBCDriverGeneratorConfig config) {
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
		classBuf.append(String.format("%s%s%s", IMPORT, ParameterMetaData.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, SQLException.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, HashMap.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, List.class.getCanonicalName(), STATEMENT_BREAK));
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements %2$s, %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				ParameterMetaData.class.getSimpleName(), JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateDeclaration();
		generateConstructor();
		generateMethods();		
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	
	protected void generateDeclaration()
	{		
		classBuf.append(INDENT1).append("private List<HashMap<String, String>> paramMetaDatas = null").append(STATEMENT_BREAK);
		classBuf.append(BLOCK_BREAK);		
	}
	
	protected void generateConstructor()
	{		
		classBuf.append(INDENT1).append("public ").append(" ");
		classBuf.append(String.format("%1$s%4$s(String metadata)", config.getJdbcClassPrefix(), Connection.class.getSimpleName(),  ResultSet.class.getSimpleName(), ParameterMetaData.class.getSimpleName()));
		classBuf.append(" throws SQLException {\n");		
		classBuf.append(INDENT2).append(String.format("this.paramMetaDatas=%1$s%2$s.convert2ParameterMetaData(metadata);\n",  config.getJdbcClassPrefix(), 
				"MetaDataUtil"));			
		classBuf.append(INDENT1).append("}\n\n");	
	}
	
	protected void generateMethods()
	{
		Method[] methods  = ParameterMetaData.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			int pc = method.getParameterTypes().length;
			if(pc==0){
				generateGlobalMethod(method);
			}else if(pc==1 && method.getParameterTypes()[0]==int.class){
				generateColumnBasedMethod(method);
			}
		}
		generateWrapMethod();
	}
	
	
	protected void generateGlobalMethod(Method method)
	{
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("() throws SQLException {\n");
		if(method.getReturnType() == int.class){
			classBuf.append(INDENT2).append(String.format("return paramMetaDatas.size();\n"));
		}else{
			System.err.println(String.format("No template for column  %s  %s: %d",method.getReturnType().getSimpleName(), method.getName(), method.getParameterTypes().length));
		}
		classBuf.append(INDENT1).append("}\n\n");	
	}
	
	
	
	protected void generateColumnBasedMethod(Method method)
	{
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("(int colidx) throws SQLException {\n");
		classBuf.append(INDENT2).append(String.format("if(colidx>paramMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException(\"Parameter index out of bound. Max=\" + paramMetaDatas.size());\n",  method.getName()));
		classBuf.append(INDENT2).append(String.format("HashMap<String, String> rsMetaData = paramMetaDatas.get(colidx-1);\n",  method.getName()));
		classBuf.append(INDENT2).append(String.format("String val = rsMetaData.get(\"%1$s\");\n",  method.getName()));
		if(method.getReturnType() == String.class){
			classBuf.append(INDENT2).append(String.format("return val;\n"));	
		}else if(method.getReturnType() == int.class){
			classBuf.append(INDENT2).append(String.format("return Integer.valueOf(val);\n"));
		}else if(method.getReturnType() == boolean.class){
			classBuf.append(INDENT2).append(String.format("return Boolean.valueOf(val);\n"));
		}else{
			System.err.println(String.format("No template for column  %s  %s: %d",method.getReturnType().getSimpleName(), method.getName(), method.getParameterTypes().length));
		}
		classBuf.append(INDENT1).append("}\n\n");	
	}
	
	
	public void generateWrapMethod()
	{
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
	
	
	protected void debugMethod(Method method)
	{		
		
		System.err.println(String.format("No template for  %s  %s: %d",method.getReturnType().getSimpleName(), method.getName(), method.getParameterTypes().length));
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		ParameterMetaDataImplGenerator generator = new ParameterMetaDataImplGenerator(config);		
		System.out.println(generator.generate());

	}

}
