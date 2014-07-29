package com.asksunny.tools.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class SavepointImplGenerator implements JDBCImplClassGenerator {

	private JDBCDriverGeneratorConfig config;

	
	
	public SavepointImplGenerator(JDBCDriverGeneratorConfig config) {
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
		classBuf.append(String.format("%s%s%s", IMPORT, Savepoint.class.getCanonicalName(), STATEMENT_BREAK));
		classBuf.append(String.format("%s%s%s", IMPORT, SQLException.class.getCanonicalName(), STATEMENT_BREAK));
		
		
		classBuf.append(BLOCK_BREAK);
		classBuf.append(String.format("public class %1$s%2$s implements %2$s, %1$s%3$s {\n", 
				config.getJdbcClassPrefix(), 
				Savepoint.class.getSimpleName(), JDBC_DRIVER_CONSTANTS_INTERFACE));
		
		generateDeclaration();
		generateConstructor();
		generateMethods();		
		classBuf.append(BLOCK_CLOSE);
		return classBuf.toString();
	}
	
	
	
	protected void generateDeclaration()
	{		
		classBuf.append(INDENT1).append("private int savepointId = -1").append(STATEMENT_BREAK);
		classBuf.append(INDENT1).append("private String savepointName = null").append(STATEMENT_BREAK);
		classBuf.append(BLOCK_BREAK);		
	}
	
	protected void generateConstructor()
	{		
		classBuf.append(INDENT1).append("public ").append(" ");
		classBuf.append(String.format("%1$s%4$s(String savepointstr)", config.getJdbcClassPrefix(), Connection.class.getSimpleName(),  ResultSet.class.getSimpleName(), Savepoint.class.getSimpleName()));
		classBuf.append(" throws SQLException {\n");		
		
		
		classBuf.append(INDENT1).append("}\n\n");	
	}
	
	protected void generateMethods()
	{
		Method[] methods  = Savepoint.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			generateSetterGetterMethod( method);			
		}
		
	}
	
	
	protected void generateSetterGetterMethod(Method method)
	{
		classBuf.append(INDENT1).append("public ").append(method.getReturnType().getSimpleName()).append(" ").append(method.getName()).append("() throws SQLException {\n");
		if(method.getReturnType() == int.class){
			classBuf.append(INDENT2).append(String.format("return this.savepointId;\n"));
		}else{
			classBuf.append(INDENT2).append(String.format("return this.savepointName;\n"));
		}
		classBuf.append(INDENT1).append("}\n\n");	
	}
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		SavepointImplGenerator generator = new SavepointImplGenerator(config);		
		System.out.println(generator.generate());

	}

}
