package com.asksunny.sql.engine.optimizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;

import com.asksunny.sql.engine.EntityDefinition;

public class PartitionOptimizer extends ExpressionVisitorAdapter {

	static final Map<Class<? extends Expression>, Integer> atomicLogicExpressions = new HashMap<Class<? extends Expression>, Integer>();
	static {
		atomicLogicExpressions.put(EqualsTo.class, new Integer(1));
		atomicLogicExpressions.put(NotEqualsTo.class, new Integer(1));
		atomicLogicExpressions.put(GreaterThan.class, new Integer(1));
		atomicLogicExpressions.put(GreaterThanEquals.class, new Integer(1));
		atomicLogicExpressions.put(MinorThan.class, new Integer(1));
		atomicLogicExpressions.put(MinorThanEquals.class, new Integer(1));
		atomicLogicExpressions.put(InExpression.class, new Integer(2));
		atomicLogicExpressions.put(Between.class, new Integer(3));
	}
	
	private final EntityDefinition entityDefinition;
	private Expression whereClause;
	private final List<Expression> partitions;

	public PartitionOptimizer(EntityDefinition entityDrfinition, Expression expr) {
		this.entityDefinition = entityDrfinition;
		partitions = new ArrayList<Expression>();
		expr.accept(this);
	}

	public Expression getWhereClause() {
		return whereClause;
	}

	public List<Expression> getPartitions() {
		return partitions;
	}

	@Override
	public void visit(AndExpression expr) {
		visitLogicExpression(expr);
	}

	@Override
	public void visit(OrExpression expr) {
		visitLogicExpression(expr);
	}

	

	protected void visitLogicExpression(BinaryExpression brExpr) {
		Expression bl = brExpr.getLeftExpression();
		Expression br = brExpr.getRightExpression();

		if (isPartitionExpression(bl)) {
			this.partitions.add(bl);
			bl = null;
		} else {
			if (!atomicLogicExpressions.containsKey(bl.getClass())) {
				PartitionOptimizer lptOp = new PartitionOptimizer(
						this.entityDefinition, bl);
				bl = lptOp.getWhereClause();
				this.partitions.addAll(lptOp.getPartitions());
			}
		}

		if (isPartitionExpression(br)) {
			this.partitions.add(br);
			br = null;
		} else {
			if (!atomicLogicExpressions.containsKey(bl.getClass())) {
				PartitionOptimizer rptOp = new PartitionOptimizer(
						this.entityDefinition, br);
				br = rptOp.getWhereClause();
				this.partitions.addAll(rptOp.getPartitions());
			}
		}

		if (bl != null && br != null) {
			brExpr.setLeftExpression(bl);
			brExpr.setRightExpression(br);
			this.whereClause = brExpr;
		} else if (bl != null) {
			this.whereClause = bl;
		} else if (br != null) {
			this.whereClause = br;
		}
	}

	protected boolean isPartitionExpression(Expression expr) {
		Integer type = atomicLogicExpressions.get(expr.getClass());
		if (type == null) {
			type = new Integer(0);
		}
		switch (type.intValue()) {
		case 1:
			BinaryExpression brExpr = (BinaryExpression) expr;
			Expression bl = brExpr.getLeftExpression();
			Expression br = brExpr.getRightExpression();
			return (isPartitionColumn(bl) || isPartitionColumn(br));
		case 2:
			InExpression inexpr = (InExpression) expr;
			return isPartitionColumn(inexpr.getLeftExpression());

		case 3:
			Between btExpr = (Between) expr;
			return isPartitionColumn(btExpr.getLeftExpression());
		default:
			return Boolean.FALSE;
		}

	}

	protected boolean isPartitionColumn(Expression expr) {
		return (expr instanceof Column && this.entityDefinition
				.isPartitionColumn(((Column) expr).getColumnName()));
	}

	protected void visitAtomicExpression(Expression expr) {
		if (isPartitionExpression(expr)) {
			this.partitions.add(expr);
		} else {
			this.whereClause = expr;
		}
	}

	@Override
	public void visit(Between expr) {
		visitAtomicExpression(expr);
	}

	@Override
	public void visit(EqualsTo expr) {
		visitAtomicExpression(expr);
	}

	@Override
	public void visit(GreaterThan expr) {
		visitAtomicExpression(expr);
	}

	@Override
	public void visit(GreaterThanEquals expr) {
		visitAtomicExpression(expr);
	}

	@Override
	public void visit(InExpression expr) {
		visitAtomicExpression(expr);
	}

	@Override
	public void visit(MinorThan expr) {
		visitAtomicExpression(expr);
	}

	@Override
	public void visit(MinorThanEquals expr) {
		visitAtomicExpression(expr);
	}

	@Override
	public void visit(NotEqualsTo expr) {
		visitAtomicExpression(expr);
	}

}
