package com.asksunny.sql.engine;

import java.sql.SQLException;
import java.util.List;

public interface ColumnQueryPlan<T, R> {
	public R eval(List<T> fieldData) throws SQLException;
	public R eval(T[] fieldData)  throws SQLException;
}
