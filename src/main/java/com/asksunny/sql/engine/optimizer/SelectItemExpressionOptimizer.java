package com.asksunny.sql.engine.optimizer;

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

import com.asksunny.sql.engine.EntityDefinition;

public class SelectItemExpressionOptimizer extends ExpressionVisitorAdapter {

	
	
	private final EntityDefinition entityDefinition;
	private final Expression expression;
	public SelectItemExpressionOptimizer(EntityDefinition entityDrfinition,
			Expression expr) {
		this.entityDefinition = entityDrfinition;	
		this.expression = expr;
		expr.accept(this);
	}
	
	@Override
	public void visit(Function function) {		
		super.visit(function);
	}
	
	@Override
	public void visit(Column column) {		
		super.visit(column);
	}
	
	@Override
	public void visit(CaseExpression expr) {		
		super.visit(expr);
	}
	
	@Override
	public void visit(CastExpression expr) {		
		super.visit(expr);
	}
	
	
	
	
	@Override
	public void visit(NullValue value) {		
		super.visit(value);
	}
	@Override
	public void visit(DoubleValue value) {		
		super.visit(value);
	}
	@Override
	public void visit(LongValue value) {		
		super.visit(value);
	}
	@Override
	public void visit(DateValue value) {		
		super.visit(value);
	}
	@Override
	public void visit(TimeValue value) {	
		super.visit(value);
	}
	@Override
	public void visit(TimestampValue value) {		
		super.visit(value);
	}
	@Override
	public void visit(Parenthesis parenthesis) {		
		super.visit(parenthesis);
	}
	@Override
	public void visit(StringValue value) {		
		super.visit(value);
	}
	
	
	
	
	@Override
	public void visit(Concat expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}
	@Override
	public void visit(BitwiseAnd expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}
	@Override
	public void visit(BitwiseOr expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}
	@Override
	public void visit(BitwiseXor expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}
	@Override
	public void visit(Modulo expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}
	
	@Override
	public void visit(Addition expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}
	@Override
	public void visit(Division expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}
	@Override
	public void visit(Multiplication expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}
	@Override
	public void visit(Subtraction expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}

}
