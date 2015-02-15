package com.asksunny.sql.engine.plan;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.WhenClause;

public class WhenExpressionEvaluator extends ExpressionVisitorAdapter {

	private final Expression expr;
	private final List<Object> rawRow;

	private final Map<String, Integer> columnIndexMap;
	private final Object switchObject;
	private final boolean hasSwitchExpression;
	private boolean matched = false;
	private Object thenValue = null;

	public WhenExpressionEvaluator(Object switchObject, Expression expr, List<Object> rawRow,
			Map<String, Integer> columnIndexMap) {
		this.rawRow = rawRow;
		this.expr = expr;
		this.columnIndexMap = columnIndexMap;
		this.switchObject = switchObject;
		this.hasSwitchExpression = switchObject!=null;
		expr.accept(this);
	}
	

	@Override
	public void visit(WhenClause expr) {
		Expression wexpr = expr.getWhenExpression();
		Expression thenExpr = expr.getThenExpression();
		if (hasSwitchExpression) {
			matched = new LogicExpressionValuator(switchObject, wexpr,
					LogicExpressionValuator.EQ, rawRow, columnIndexMap)
					.isMatch();
		} else {
			matched = new LogicExpressionValuator(wexpr, rawRow, columnIndexMap)
					.isMatch();
		}
		if (matched) {
			ExpressionEvaluator evalt = new ExpressionEvaluator(thenExpr,
					rawRow, columnIndexMap);
			this.thenValue = evalt.getEvalResult();
		}
	}

	public boolean isMatched() {
		return matched;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	public Object getThenValue() {
		return thenValue;
	}

	public void setThenValue(Object thenValue) {
		this.thenValue = thenValue;
	}

	public Expression getExpr() {
		return expr;
	}

	public List<Object> getRawRow() {
		return rawRow;
	}

	public Map<String, Integer> getColumnIndexMap() {
		return columnIndexMap;
	}

}
