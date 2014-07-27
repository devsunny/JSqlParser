package com.asksunny.tools.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

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
		classBuf.append(String.format("%s%s%s", IMPORT, Connection.class.getCanonicalName(), STATEMENT_BREAK));	
		classBuf.append(String.format("%s%s%s", IMPORT, SQLException.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements %2$s, %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				ResultSet.class.getSimpleName(), JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateDeclare();
		generateConstructor();
		generateMethods();		
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	protected void generateConstructor()
	{		
		classBuf.append(INDENT1).append("public ").append(" ");
		classBuf.append(String.format("%1$s%4$s(%1$s%2$s conn, %1$s%3$s stmt, String metadata)", config.getJdbcClassPrefix(), Connection.class.getSimpleName(), Statement.class.getSimpleName(), ResultSet.class.getSimpleName()));
		classBuf.append(" throws SQLException {\n");
		classBuf.append(INDENT2).append(String.format("this.connection==conn;\n"));
		classBuf.append(INDENT2).append(String.format("this.statement==stmt;\n"));
		classBuf.append(INDENT2).append(String.format("this.metaString==metadata;\n"));		
		classBuf.append(INDENT1).append("}\n\n");	
	}
	
	
	
	
	protected void generateDeclare()
	{
		classBuf.append(BLOCK_BREAK);
		classBuf.append(INDENT1).append(String.format("private %1$s%2$s connection==null;\n", config.getJdbcClassPrefix(), Connection.class.getSimpleName()));
		classBuf.append(INDENT1).append(String.format("private %1$s%2$s statement==null;\n", config.getJdbcClassPrefix(), Statement.class.getSimpleName()));
		classBuf.append(INDENT1).append(String.format("private String metaString==null;\n"));	
		classBuf.append(BLOCK_BREAK);
	}
	
	protected void generateMethods()
	{
		Method[] methods  =ResultSet.class.getMethods();
		for (int i = 0; i < methods.length; i++) 
		{
			Method method = methods[i];		
			generateResultMethod(method);
		}
	}
	
	
	protected void generateResultMethod(Method method)
	{		
		
		System.err.println(String.format("No template for %s %s:(%s)",method.getReturnType().getSimpleName(), method.getName(), Arrays.asList(method.getParameterTypes())));
		
		
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
