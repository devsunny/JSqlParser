package com.asksunny.sql.engine.plan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.SignedExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Modulo;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.schema.Column;

import com.asksunny.sql.engine.SqlFunction;
import com.asksunny.sql.engine.SqlFunctionFactory;

public class ExpressionEvaluator extends ExpressionVisitorAdapter {

	public static final int ADDITION = 1;
	public static final int SUBSTRACTION = 2;
	public static final int DIVISION = 4;
	public static final int MUPLICATION = 3;
	public static final int MODULO = 5;
	public static final int BITWISE_AND = 6;
	public static final int BITWISE_OR = 7;
	public static final int BITWISE_XOR = 8;
	public static final String NUMBERED_COLUMN_PATTERN = "\\$\\d+";

	private final Expression expr;
	private final List<Object> rawRow;
	private Object evalResult = null;
	private final Map<String, Integer> columnIndexMap;

	public ExpressionEvaluator(Expression expr, List<Object> rawRow,
			Map<String, Integer> columnIndexMap) {
		this.rawRow = rawRow;
		this.expr = expr;
		this.columnIndexMap = columnIndexMap;
		expr.accept(this);
	}

	public Object getEvalResult() {
		return evalResult;
	}

	public void setEvalResult(Object evalResult) {
		this.evalResult = evalResult;
	}

	public Expression getExpr() {
		return expr;
	}

	public List<Object> getRawRow() {
		return rawRow;
	}

	@Override
	public void visit(NullValue value) {
		evalResult = null;
	}

	@Override
	public void visit(Function function) {
		String name = function.getName();
		List<Object> params = new ArrayList<Object>();
		if (function.getParameters() != null) {
			List<Expression> exprs = function.getParameters().getExpressions();
			for (Expression expression : exprs) {
				ExpressionEvaluator eval = new ExpressionEvaluator(expression,
						rawRow, columnIndexMap);
				params.add(eval.getEvalResult());
			}
		}

		try {
			SqlFunction functImpl = SqlFunctionFactory.createSqlFunction(name);
			evalResult = functImpl.invoke(params);
		} catch (SQLException e) {
			throw new ExpressionEvaluationException(
					"Failed to execution SQLFunction");
		}

	}

	@Override
	public void visit(SignedExpression expr) {
		Expression subexpr = expr.getExpression();
		ExpressionEvaluator eval = new ExpressionEvaluator(subexpr, rawRow,
				columnIndexMap);
		Object ret = eval.getEvalResult();
		short sign = expr.getSign() == '-' ? (short) -1 : (short) 1;
		if (ret == null) {
			return;
		} else if (ret instanceof Double) {
			evalResult = ((Double) ret).doubleValue() * sign;
		} else if (ret instanceof Float) {
			evalResult = ((Float) ret).floatValue() * sign;
		} else if (ret instanceof Long) {
			evalResult = ((Long) ret).longValue() * sign;
		} else if (ret instanceof Integer) {
			evalResult = ((Integer) ret).intValue() * sign;
		} else if (ret instanceof Short) {
			evalResult = ((Short) ret).shortValue() * sign;
		} else {
			throw new ExpressionEvaluationException(String.format(
					"Invalid signed expression %s", expr.toString()));
		}

	}

	@Override
	public void visit(DoubleValue value) {
		evalResult = value.getValue();
	}

	@Override
	public void visit(LongValue value) {
		evalResult = value.getValue();
	}

	@Override
	public void visit(DateValue value) {
		evalResult = value.getValue();
	}

	@Override
	public void visit(TimeValue value) {
		evalResult = value.getValue();
	}

	@Override
	public void visit(TimestampValue value) {
		evalResult = value.getValue();
	}

	@Override
	public void visit(Parenthesis parenthesis) {
		Expression subexpr = parenthesis.getExpression();
		ExpressionEvaluator eval = new ExpressionEvaluator(subexpr, rawRow,
				columnIndexMap);
		evalResult = eval.getEvalResult();
	}

	@Override
	public void visit(StringValue value) {
		evalResult = value.getValue();
	}

	protected Object evalArithmiticExpression(Expression left,
			Expression right, int operator) {
		ExpressionEvaluator eval = new ExpressionEvaluator(left, rawRow,
				columnIndexMap);
		Object leftOp = eval.getEvalResult();
		if (leftOp == null) {
			return null;
		}
		eval = new ExpressionEvaluator(right, rawRow, columnIndexMap);
		Object rightOp = eval.getEvalResult();
		if (rightOp == null) {
			return null;
		}
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
		Object retResult = null;
		switch (operator) {
		case ADDITION:
			if (intCal) {
				retResult = leftLongVal.longValue() + rightLongVal.longValue();
			} else {
				retResult = leftDoubleVal.doubleValue()
						+ rightDoubleVal.doubleValue();
			}
		case SUBSTRACTION:
			if (intCal) {
				retResult = leftLongVal.longValue() - rightLongVal.longValue();
			} else {
				retResult = leftDoubleVal.doubleValue()
						- rightDoubleVal.doubleValue();
			}
		case MUPLICATION:
			if (intCal) {
				retResult = leftLongVal.longValue() * rightLongVal.longValue();
			} else {
				retResult = leftDoubleVal.doubleValue()
						* rightDoubleVal.doubleValue();
			}
		case DIVISION:
			if (intCal) {
				retResult = leftLongVal.longValue() / rightLongVal.longValue();
			} else {
				retResult = leftDoubleVal.doubleValue()
						/ rightDoubleVal.doubleValue();
			}
		case MODULO:
			if (intCal) {
				retResult = leftLongVal.longValue() % rightLongVal.longValue();
			} else {
				retResult = leftDoubleVal.doubleValue()
						% rightDoubleVal.doubleValue();
			}
		case BITWISE_AND:
			if (intCal) {
				retResult = leftLongVal.longValue() & rightLongVal.longValue();
			} else {
				retResult = leftDoubleVal.longValue()
						& rightDoubleVal.longValue();
			}
		case BITWISE_OR:
			if (intCal) {
				retResult = leftLongVal.longValue() | rightLongVal.longValue();
			} else {
				retResult = leftDoubleVal.longValue()
						| rightDoubleVal.longValue();
			}
		case BITWISE_XOR:
			if (intCal) {
				retResult = leftLongVal.longValue() ^ rightLongVal.longValue();
			} else {
				retResult = leftDoubleVal.longValue()
						^ rightDoubleVal.longValue();
			}
		}

		return retResult;
	}

	@Override
	public void visit(Subtraction expr) {

		Expression left = expr.getLeftExpression();
		Expression right = expr.getRightExpression();
		evalResult = evalArithmiticExpression(left, right, SUBSTRACTION);
	}

	@Override
	public void visit(Addition expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getRightExpression();
		evalResult = evalArithmiticExpression(left, right, ADDITION);

	}

	@Override
	public void visit(Multiplication expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getRightExpression();
		evalResult = evalArithmiticExpression(left, right, MUPLICATION);
	}

	@Override
	public void visit(Division expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getRightExpression();
		evalResult = evalArithmiticExpression(left, right, DIVISION);
	}

	@Override
	public void visit(Modulo expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getRightExpression();
		evalResult = evalArithmiticExpression(left, right, MODULO);
	}

	@Override
	public void visit(BitwiseOr expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getRightExpression();
		evalResult = evalArithmiticExpression(left, right, BITWISE_OR);
	}

	@Override
	public void visit(BitwiseXor expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getRightExpression();
		evalResult = evalArithmiticExpression(left, right, BITWISE_XOR);
	}

	@Override
	public void visit(BitwiseAnd expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getRightExpression();
		evalResult = evalArithmiticExpression(left, right, BITWISE_AND);
	}

	@Override
	public void visit(CastExpression expr) {
		Expression subexpr = expr.getLeftExpression();
		ExpressionEvaluator eval = new ExpressionEvaluator(subexpr, rawRow,
				columnIndexMap);
		evalResult = eval.getEvalResult();
	}

	@Override
	public void visit(Column column) {
		String name = column.getColumnName();
		Integer idx = columnIndexMap.get(name);
		if (idx == null && name.matches(NUMBERED_COLUMN_PATTERN)) {
			idx = Integer.valueOf(name.substring(1)) - 1;
		}
		if (idx >= this.rawRow.size()) {
			throw new IndexOutOfBoundsException(String.format(
					"Column index outof bound %d size %s", idx,
					this.rawRow.size()));
		}
		evalResult = this.rawRow.get(idx);
	}

	@Override
	public void visit(CaseExpression expr) {
		Expression switchExpr = expr.getSwitchExpression();
		Object p = null;
		if (switchExpr != null) {
			ExpressionEvaluator eval = new ExpressionEvaluator(switchExpr,
					rawRow, columnIndexMap);
			p = eval.getEvalResult();
		}
		boolean matched = false;
		List<Expression> whens = expr.getWhenClauses();
		for (Expression expression : whens) {
			WhenExpressionEvaluator weval = new WhenExpressionEvaluator(p,
					expression, rawRow, columnIndexMap);
			matched = weval.isMatched();
			if (matched) {
				evalResult = weval.getThenValue();
				break;
			}
		}
		if (!matched && expr.getElseExpression() != null) {
			ExpressionEvaluator eval = new ExpressionEvaluator(
					expr.getElseExpression(), rawRow, columnIndexMap);
			evalResult = eval.getEvalResult();
		}

	}

	@Override
	public void visit(Concat expr) {
		Expression left = expr.getLeftExpression();
		Expression right = expr.getRightExpression();
		ExpressionEvaluator eval = new ExpressionEvaluator(left, rawRow,
				columnIndexMap);
		Object leftOp = eval.getEvalResult();
		eval = new ExpressionEvaluator(right, rawRow, columnIndexMap);
		Object rightOp = eval.getEvalResult();
		evalResult = ((leftOp == null) ? "" : leftOp.toString())
				+ ((rightOp == null) ? "" : rightOp.toString());

	}

}
