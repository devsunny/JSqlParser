package com.asksunny.tools.jdbc;

import java.io.File;

public class JDBCDriverGeneratorConfig {

	private String baseDir = ".";
	private String packageName = "com.asksunny.jdbc";
	private String jdbcClassPrefix = "Sunny";

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;

	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;

	}

	public String getJdbcClassPrefix() {
		return jdbcClassPrefix;
	}

	public void setJdbcClassPrefix(String jdbcClassPrefix) {
		this.jdbcClassPrefix = jdbcClassPrefix;

	}
	
	public File getPackageDirFile()
	{
		String x = packageName.replace('.', '/');
		File dir = new File(this.baseDir, x);
		if(!dir.exists()){
			if(!dir.mkdirs()){
				throw new RuntimeException("Permission denied to create package directory");
			}
		}		
		return dir;		
	}
	
	

}
