package com.asksunny.db;



import java.util.List;

import org.junit.Test;

import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitor;
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
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.set.Set;

public class SetTest {

	@Test
	public void SetTest() throws Exception {
		String sql = "set DateStyle 'ISO'";
		Statement stmt = CCJSqlParserUtil.parse(sql);
		if(stmt instanceof Set){
			Set set = (Set)stmt;
			System.out.println(set.getName());
			System.out.println(set.getValue());
		}
			
		sql = "select pg_client_encoding()";
		stmt = CCJSqlParserUtil.parse(sql);
		if(stmt instanceof Select){
			Select select = (Select)stmt;
			
			final PGFunctionVisitor pgFunctVisitor = new PGFunctionVisitor("pg_client_encoding");
			
			select .getSelectBody().accept(new SelectVisitor() {
				
				@Override
				public void visit(WithItem withItem) {				
				}
				
				@Override
				public void visit(SetOperationList setOpList) {				
					
				}
				
				@Override
				public void visit(PlainSelect plainSelect) {
				  List<SelectItem> selectItems = plainSelect.getSelectItems();
				  for (SelectItem selectItem : selectItems) {
					  selectItem.accept(pgFunctVisitor);
				  }
					
				}
			});
			System.out.println(pgFunctVisitor.hasFoundMatch());
			
		}
	}
	
	
	private static class PGFunctionVisitor extends ExpressionVisitorAdapter implements SelectItemVisitor
	{

		private String functionName = null;
		private boolean foundMatch = false;
		
		public PGFunctionVisitor(String functname)
		{
			this.functionName = functname;
		}
		
		@Override
		public void visit(AllColumns allColumns) {			
			
		}

		@Override
		public void visit(AllTableColumns allTableColumns) {			
			
		}

		@Override
		public void visit(SelectExpressionItem selectExpressionItem) {
			selectExpressionItem.getExpression().accept(this);		
		}

		@Override
		public void visit(Function function) {
			String name = function.getName();
			this.foundMatch = name.equalsIgnoreCase(this.functionName);
		}

		public boolean hasFoundMatch() {
			return foundMatch;
		}

				
		
		
		
	}

}
