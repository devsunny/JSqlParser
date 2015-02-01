package com.asksunny.sql.engine.optimizer;

import java.util.List;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.Distinct;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.Limit;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.Top;
import net.sf.jsqlparser.statement.select.WithItem;

public class QueryOptimizer implements SelectVisitor {

	private FromItem fromItem;
	private List<Join> joins = null;
	private List<SelectItem> selectItems = null;
	private Expression whereClause = null;
	private Limit limit = null;
	private Top top = null;
	private Distinct distinct = null;

	public QueryOptimizer(Select query) {
		query.getSelectBody().accept(this);
	}

	@Override
	public void visit(PlainSelect plainSelect) {
		fromItem = plainSelect.getFromItem();
		joins = plainSelect.getJoins();
		selectItems = plainSelect.getSelectItems();
		whereClause = plainSelect.getWhere();
		limit = plainSelect.getLimit();
		top = plainSelect.getTop();
		distinct = plainSelect.getDistinct();
	}

	@Override
	public void visit(SetOperationList setOpList) {

	}

	@Override
	public void visit(WithItem withItem) {

	}

	public FromItem getFromItem() {
		return fromItem;
	}

	public List<Join> getJoins() {
		return joins;
	}

	public List<SelectItem> getSelectItems() {
		return selectItems;
	}

	public Expression getWhereClause() {
		return whereClause;
	}

	public Limit getLimit() {
		return limit;
	}

	public Top getTop() {
		return top;
	}

	public Distinct getDistinct() {
		return distinct;
	}

}
