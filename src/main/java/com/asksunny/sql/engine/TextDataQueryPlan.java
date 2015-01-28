package com.asksunny.sql.engine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TextDataQueryPlan<R> implements QueryPlan<String, R> {

	private List<ColumnQueryPlan<String, R>> columnPlans = null;

	public List<R> executePlan(List<String> textRowData)  throws SQLException {
		if (textRowData == null) {
			return null;
		} else if (columnPlans != null) {
			List<R> rs = new ArrayList<>();
			for (ColumnQueryPlan<String, R> p : columnPlans) {
				rs.add(p.eval(textRowData));
			}
			return rs;
		} else {
			return null;
		}
	}

	public List<R> executePlan(String[] textRowData)  throws SQLException {

		if (textRowData == null) {
			return null;
		} else if (columnPlans != null) {
			List<R> rs = new ArrayList<>();
			for (ColumnQueryPlan<String, R> p : columnPlans) {
				rs.add(p.eval(textRowData));
			}
			return rs;
		} else {
			return null;
		}
	}

	public List<ColumnQueryPlan<String, R>> getColumnPlans() {
		return columnPlans;
	}

	public void setColumnPlans(List<ColumnQueryPlan<String, R>> columnPlans) {
		this.columnPlans = columnPlans;
	}

}
