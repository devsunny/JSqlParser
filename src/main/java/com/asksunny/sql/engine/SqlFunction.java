package com.asksunny.sql.engine;

import java.sql.SQLException;
import java.util.List;

public interface SqlFunction 
{
	String getName();
	int getReturnType();
	Object invoke(List<Object> parameters) throws SQLException;
	int getMaxParameterCount();
	int getMinParameterCount();	
	boolean isStateless();
}
