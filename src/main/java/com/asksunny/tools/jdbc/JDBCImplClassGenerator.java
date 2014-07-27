package com.asksunny.tools.jdbc;

public interface JDBCImplClassGenerator {
	final static String INDENT1 = "    ";
	final static String INDENT2 = "        ";
	final static String INDENT3 = "            ";
	final static String INDENT4 = "                ";
	final static String PACKAGE = "package ";
	final static String IMPORT = "import ";
	final static String STATEMENT_BREAK = ";\n";
	final static String BLOCK_BREAK = "\n\n";
	final static String BLOCK_CLOSE = "}\n\n";
	final static String JDBC_SOCKET_CLIENT_INTERFACE = "JdbcSocketClientInterface";
	final static String JDBC_DRIVER_CONSTANTS_INTERFACE = "JdbcDriverConstants";
	
	public String generate();
}
