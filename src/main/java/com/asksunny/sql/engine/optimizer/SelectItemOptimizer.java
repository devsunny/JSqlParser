package com.asksunny.sql.engine.optimizer;

import java.util.HashMap;
import java.util.Map;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;

import com.asksunny.sql.engine.EntityDefinition;

public class SelectItemOptimizer extends ExpressionVisitorAdapter implements
		SelectItemVisitor {

	
	private static final Map<Class<? extends Expression>, Integer> SelectItemMap = new HashMap<Class<? extends Expression>, Integer>();
	static{
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
		SelectItemMap.put(Column.class, 1);
	}
	private final SelectItem selectItem;
	private final EntityDefinition entityDefinition;
	private SelectItemType type = null;
	public SelectItemOptimizer(EntityDefinition entityDrfinition,
			SelectItem selectItem) {
		this.entityDefinition = entityDrfinition;
		this.selectItem = selectItem;
		this.selectItem.accept(this);
	}

	@Override
	public void visit(AllColumns allColumns) {
		type  = SelectItemType.ALL_COLUMNS;
	}

	@Override
	public void visit(AllTableColumns allTableColumns) {		
		type  = SelectItemType.ALL_TABLE_COLUMNS;
	}

	
	@Override
	public void visit(SelectExpressionItem selectExpressionItem) 
	{	
		Expression expr = selectExpressionItem.getExpression();
		System.out.println(expr.getClass().getName());
		
		if(selectExpressionItem.getAlias()!=null) System.out.println(selectExpressionItem.getAlias().getName());
		
	}
	
	
	
	

}
