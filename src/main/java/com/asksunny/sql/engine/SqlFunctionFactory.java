package com.asksunny.sql.engine;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SqlFunctionFactory {

	public static final Map<String, Class<? extends SqlFunction>> sqlFunctionMap = new ConcurrentHashMap<>();

	private SqlFunctionFactory() {
	}

	public static void register(String name,
			Class<? extends SqlFunction> functClass) {
		sqlFunctionMap.put(name.toUpperCase(), functClass);
	}

	public static void register(SqlFunction functImpl) {
		sqlFunctionMap.put(functImpl.getName(), functImpl.getClass());
	}

	public static SqlFunction createSqlFunction(String name)
			throws SQLException {
		Class<? extends SqlFunction> functClass = sqlFunctionMap.get(name
				.toUpperCase());
		if (functClass == null) {
			throw new SQLException(String.format("Function %s does not exist",
					name));
		}

		SqlFunction funct = null;
		try {
			funct = functClass.newInstance();
		} catch (InstantiationException e) {
			throw new SQLException(
					String.format(
							"Function %s implementation does not default constructor or zero parameter constructor",
							name));
		} catch (IllegalAccessException e) {
			throw new SQLException(
					String.format(
							"Function %s implementation default constructor is not accessible",
							name));
		}

		return funct;
	}

}
