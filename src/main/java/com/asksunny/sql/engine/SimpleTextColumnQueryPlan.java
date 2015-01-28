package com.asksunny.sql.engine;

import java.sql.SQLException;
import java.util.List;

public class SimpleTextColumnQueryPlan<R> extends TextDataColumnQueryPlan<R> {

	private int columnIndex;
	

	public SimpleTextColumnQueryPlan(Class<R> returnType) {
		super(returnType);
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	@Override
	public R eval(List<String> fieldData) throws SQLException {
		String rawData = fieldData.get(columnIndex);
		return cast(rawData, returnType);
	}

	@Override
	public R eval(String[] fieldData) throws SQLException {
		String rawData = fieldData[columnIndex];
		return cast(rawData, returnType);
	}

}
