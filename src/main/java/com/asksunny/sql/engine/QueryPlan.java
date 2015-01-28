package com.asksunny.sql.engine;

import java.sql.SQLException;
import java.util.List;

public interface QueryPlan<T, R> 
{
	
	List<R> executePlan(List<T> textRowData)  throws SQLException;
	List<R> executePlan(T[] textRowData)  throws SQLException;
}
