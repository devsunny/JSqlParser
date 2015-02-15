package com.asksunny.sql.engine.plan;

import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;

public class LogicExpressionValuator extends ExpressionVisitorAdapter {

	public static final int EQ = 1;
	public static final int GT = 2;
	public static final int GE = 3;
	public static final int LT = 4;
	public static final int LE = 5;
	public static final int NEQ = 6;

	public static final int LIKE = 9;
	public static final int MATCH = 10;

	private final Expression expr;
	private final List<Object> rawRow;
	private boolean isMatch = false;
	private final Map<String, Integer> columnIndexMap;

	public LogicExpressionValuator(Expression expr, List<Object> rawRow,
			Map<String, Integer> columnIndexMap) {
		this.rawRow = rawRow;
		this.expr = expr;
		this.columnIndexMap = columnIndexMap;
		expr.accept(this);
	}

	public LogicExpressionValuator(Expression left, Expression right, int op,
			List<Object> rawRow, Map<String, Integer> columnIndexMap) {
		this.rawRow = rawRow;
		this.expr = null;
		this.columnIndexMap = columnIndexMap;
		isMatch = compareLogicExpressions(left, right, op);
	}

	public LogicExpressionValuator(Object leftObj, Expression right, int op,
			List<Object> rawRow, Map<String, Integer> columnIndexMap) {
		this.rawRow = rawRow;
		this.expr = null;
		this.columnIndexMap = columnIndexMap;
		isMatch = compareLogicExpressions(leftObj, right, op);
	}

	@Override
	public void visit(AndExpression expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getLeftExpression();
		LogicExpressionValuator leval = new LogicExpressionValuator(left,
				rawRow, columnIndexMap);
		if (!leval.isMatch()) {
			isMatch = leval.isMatch();
			return;
		}
		LogicExpressionValuator reval = new LogicExpressionValuator(right,
				rawRow, columnIndexMap);
		if (!reval.isMatch()) {
			isMatch = reval.isMatch();
			return;
		}
		isMatch = leval.isMatch() && reval.isMatch();

	}

	@Override
	public void visit(OrExpression expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getLeftExpression();
		LogicExpressionValuator leval = new LogicExpressionValuator(left,
				rawRow, columnIndexMap);
		if (leval.isMatch()) {
			isMatch = leval.isMatch();
			return;
		}
		LogicExpressionValuator reval = new LogicExpressionValuator(right,
				rawRow, columnIndexMap);
		if (reval.isMatch()) {
			isMatch = reval.isMatch();
			return;
		}
	}

	protected boolean compareLogicExpressions(Expression left,
			Expression right, int op) {
		ExpressionEvaluator eval = new ExpressionEvaluator(left, rawRow,
				columnIndexMap);
		Object leftOp = eval.getEvalResult();
		if (leftOp == null) {
			return Boolean.FALSE;
		}
		return compareLogicExpressions(leftOp, right, op);
	}

	protected boolean compareLogicExpressions(Object leftOp, Expression right,
			int op) {

		ExpressionEvaluator eval = new ExpressionEvaluator(right, rawRow,
				columnIndexMap);
		Object rightOp = eval.getEvalResult();
		if (rightOp == null) {
			return Boolean.FALSE;
		}
		if (op == LIKE) {
			String patern = rightOp.toString().replaceAll("!%", "<000>");
			patern = patern.replaceAll("%", ".*?");
			patern = patern.replaceAll("<000>", "!%");
			return leftOp.toString().matches(patern);
		} else if (op == MATCH) {
			return leftOp.toString().matches(rightOp.toString());
		} else if (leftOp instanceof String || rightOp instanceof String) {
			int cmp = leftOp.toString().compareTo(rightOp.toString());
			switch (op) {
			case EQ:
				return cmp == 0;
			case NEQ:
				return cmp != 0;
			case GT:
				return cmp == 1;
			case GE:
				return cmp >= 0;
			case LT:
				return cmp == -1;
			case LE:
				return cmp <= 0;
			}
		} else {
			Long leftLongVal = null;
			Double leftDoubleVal = null;
			Long rightLongVal = null;
			Double rightDoubleVal = null;
			if (leftOp instanceof Long) {
				leftLongVal = (Long) leftOp;
			} else if (leftOp instanceof Integer) {
				leftLongVal = ((Integer) leftOp).longValue();
			} else if (leftOp instanceof Short) {
				leftLongVal = ((Short) leftOp).longValue();
			} else if (leftOp instanceof Double) {
				leftDoubleVal = ((Double) leftOp).doubleValue();
			} else if (leftOp instanceof Float) {
				leftDoubleVal = ((Float) leftOp).doubleValue();
			}
			if (rightOp instanceof Long) {
				rightLongVal = (Long) rightOp;
			} else if (rightOp instanceof Integer) {
				rightLongVal = ((Integer) rightOp).longValue();
			} else if (rightOp instanceof Short) {
				rightLongVal = ((Short) leftOp).longValue();
			} else if (rightOp instanceof Double) {
				rightDoubleVal = ((Double) rightOp).doubleValue();
			} else if (rightOp instanceof Float) {
				rightDoubleVal = ((Float) rightOp).doubleValue();
			}
			boolean intCal = false;
			if (leftLongVal != null && rightLongVal != null) {
				intCal = true;
			} else {
				if (leftDoubleVal == null) {
					leftDoubleVal = leftLongVal.doubleValue();
				}
				if (rightDoubleVal == null) {
					rightDoubleVal = rightLongVal.doubleValue();
				}
			}
			int cmp = intCal ? (leftLongVal.compareTo(rightLongVal))
					: leftDoubleVal.compareTo(rightDoubleVal);
			switch (op) {
			case EQ:
				return cmp == 0;
			case NEQ:
				return cmp != 0;
			case GT:
				return cmp == 1;
			case GE:
				return cmp >= 0;
			case LT:
				return cmp == -1;
			case LE:
				return cmp <= 0;
			}
		}
		return false;
	}

	@Override
	public void visit(EqualsTo expr) {
		isMatch = compareLogicExpressions(expr.getLeftExpression(),
				expr.getRightExpression(), EQ);

	}

	@Override
	public void visit(GreaterThan expr) {
		isMatch = compareLogicExpressions(expr.getLeftExpression(),
				expr.getRightExpression(), GT);
	}

	@Override
	public void visit(GreaterThanEquals expr) {
		isMatch = compareLogicExpressions(expr.getLeftExpression(),
				expr.getRightExpression(), GE);
	}

	@Override
	public void visit(LikeExpression expr) {
		isMatch = compareLogicExpressions(expr.getLeftExpression(),
				expr.getRightExpression(), LIKE);
	}

	@Override
	public void visit(MinorThan expr) {
		isMatch = compareLogicExpressions(expr.getLeftExpression(),
				expr.getRightExpression(), LT);
	}

	@Override
	public void visit(MinorThanEquals expr) {
		isMatch = compareLogicExpressions(expr.getLeftExpression(),
				expr.getRightExpression(), LE);
	}

	@Override
	public void visit(NotEqualsTo expr) {
		isMatch = compareLogicExpressions(expr.getLeftExpression(),
				expr.getRightExpression(), NEQ);
	}

	@Override
	public void visit(Matches expr) {
		isMatch = compareLogicExpressions(expr.getLeftExpression(),
				expr.getRightExpression(), MATCH);
	}

	@Override
	public void visit(IsNullExpression expr) {
		Expression left = expr.getLeftExpression();
		ExpressionEvaluator eval = new ExpressionEvaluator(left, rawRow,
				columnIndexMap);
		Object leftOp = eval.getEvalResult();
		isMatch = (leftOp == null);
	}

	public boolean isMatch() {
		return isMatch;
	}

	public void setMatch(boolean isMatch) {
		this.isMatch = isMatch;
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
