package com.asksunny.tools.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class DriverConstantsInterfaceGenerator implements JDBCImplClassGenerator{

	private JDBCDriverGeneratorConfig config;

	
	
	public DriverConstantsInterfaceGenerator(JDBCDriverGeneratorConfig config) {
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
		
		classBuf.append(String.format("public interface %s%s {\n", config.getJdbcClassPrefix(), JDBC_DRIVER_CONSTANTS_INTERFACE, BLOCK_BREAK));
	
	    
		classBuf.append(INDENT1).append("final static char FIELD_DELIMITER = (char)1;\n");		
		classBuf.append(INDENT1).append("final static char SUBFIELD_DELIMITER = (char)2;\n");	
		classBuf.append(INDENT1).append("final static char RECORD_DELIMITER = (char)2;\n");
		
		Class<?>[] dbClasses = new Class<?>[]{Connection.class, DatabaseMetaData.class, Statement.class, PreparedStatement.class, CallableStatement.class};
		for (int i = 0; i < dbClasses.length; i++) {
			Class<?> cls = dbClasses[i];
			classBuf.append(INDENT1).append(String.format("final static char %s_CMD = (char)%d;\n", cls.getSimpleName(), i+33));
		}
				
		
		classBuf.append(INDENT1).append("final static char SQL_ERROR = (char)255;\n");	
		
		
		
		
		
		classBuf.append(BLOCK_CLOSE);		
		
		return classBuf.toString();
	}
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDBCDriverGeneratorConfig config = new JDBCDriverGeneratorConfig();
		config.setPackageName("com.asksunny.jdbc");
		config.setJdbcClassPrefix("Sunny");
		DriverConstantsInterfaceGenerator generator = new DriverConstantsInterfaceGenerator(config);		
		System.out.println(generator.generate());

	}

}
