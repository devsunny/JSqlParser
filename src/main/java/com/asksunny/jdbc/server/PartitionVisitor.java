package com.asksunny.jdbc.server;

import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.ExtractExpression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.OracleHierarchicalExpression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.SignedExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Modulo;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

public class PartitionVisitor extends ExpressionVisitorAdapter {

	
	
	public PartitionVisitor() 
	{
		
	}

	@Override
	public void visit(NullValue value) {		
		super.visit(value);
	}

	@Override
	public void visit(Function function) {		
		System.out.println("Function");
		super.visit(function);
	}

	@Override
	public void visit(SignedExpression expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(JdbcParameter parameter) {		
		super.visit(parameter);
	}

	@Override
	public void visit(JdbcNamedParameter parameter) {		
		super.visit(parameter);
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
	public void visit(Addition expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(Division expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(Multiplication expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(Subtraction expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(AndExpression expr) {		
		Expression leftExpr = expr.getLeftExpression();
		Expression rightExpr = expr.getRightExpression();
		leftExpr.accept(new PartitionVisitor());
		rightExpr.accept(new PartitionVisitor());
	}

	@Override
	public void visit(OrExpression expr) {		
		Expression leftExpr = expr.getLeftExpression();
		Expression rightExpr = expr.getRightExpression();
		leftExpr.accept(new PartitionVisitor());
		rightExpr.accept(new PartitionVisitor());		
	}

	@Override
	public void visit(Between expr) {		
		Expression leftExpr = expr.getLeftExpression();
		Expression startExpr = expr.getBetweenExpressionStart();
		Expression endExpr = expr.getBetweenExpressionEnd();		
		System.out.println("Between");
		leftExpr.accept(new PartitionVisitor());
		startExpr.accept(new PartitionVisitor());	
		endExpr.accept(new PartitionVisitor());		
	}

	@Override
	public void visit(EqualsTo expr) {		
		Expression leftExpr = expr.getLeftExpression();
		Expression rightExpr = expr.getRightExpression();
		super.visit(expr);
	}

	@Override
	public void visit(GreaterThan expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(GreaterThanEquals expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(InExpression expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(IsNullExpression expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(LikeExpression expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(MinorThan expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(MinorThanEquals expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(NotEqualsTo expr) {		
		super.visit(expr);
	}

	@Override
	public void visit(Column column) {		
		System.out.println("Column:" + column.getColumnName());
	}

	@Override
	public void visit(SubSelect subSelect) {		
		super.visit(subSelect);
	}

	@Override
	public void visit(CaseExpression expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}

	@Override
	public void visit(WhenClause expr) {
		// TODO Auto-generated method stub
		super.visit(expr);
	}

	@Override
	public void visit(ExistsExpression expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(AllComparisonExpression expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(AnyComparisonExpression expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(Concat expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(Matches expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(BitwiseAnd expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(BitwiseOr expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(BitwiseXor expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(CastExpression expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(Modulo expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(AnalyticExpression expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(ExtractExpression expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(IntervalExpression expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(OracleHierarchicalExpression expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(RegExpMatchOperator expr) {
		
		super.visit(expr);
	}

	@Override
	public void visit(ExpressionList expressionList) {
		
		super.visit(expressionList);
	}

	@Override
	public void visit(MultiExpressionList multiExprList) {
		
		super.visit(multiExprList);
	}

	

}
