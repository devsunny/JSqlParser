package com.asksunny.sql.engine;


import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;

import com.asksunny.sql.engine.optimizer.QueryOptimizer;
import com.asksunny.sql.engine.optimizer.SelectItemOptimizer;

public class SQLEngine {

	public SQLEngine() {
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws JSQLParserException {
		String sql = "SELECT $1 as \"index\", 12  abc, 'String ' as name, 23.34, count(name) FROM test where A=2 and B=4";		
		Select query = (Select)CCJSqlParserUtil.parse(sql);
		QueryOptimizer q = new QueryOptimizer(query);
		List<SelectItem> items = q.getSelectItems();
		for (SelectItem selectItem : items) {
		
			new SelectItemOptimizer(null,  selectItem);			
		}

	}

}
