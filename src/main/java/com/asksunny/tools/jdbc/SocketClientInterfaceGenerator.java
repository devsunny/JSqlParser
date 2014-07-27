package com.asksunny.tools.jdbc;

import java.util.HashMap;

public class SocketClientInterfaceGenerator implements JDBCImplClassGenerator{

	private JDBCDriverGeneratorConfig config;

	
	
	public SocketClientInterfaceGenerator(JDBCDriverGeneratorConfig config) {
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
		classBuf.append(String.format("%s%s%s", IMPORT, HashMap.class.getCanonicalName(), STATEMENT_BREAK));
		
		classBuf.append(String.format("public interface %s%s {\n", config.getJdbcClassPrefix(), JDBC_SOCKET_CLIENT_INTERFACE, BLOCK_BREAK));
		classBuf.append(INDENT1).append(" void open() throws SQLException;\n");
		classBuf.append(INDENT1).append(" String sendCommand(String cmd) throws SQLException;\n");
		classBuf.append(INDENT1).append(" void close() throws SQLException;\n");
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
		SocketClientInterfaceGenerator generator = new SocketClientInterfaceGenerator(config);		
		System.out.println(generator.generate());

	}

}
