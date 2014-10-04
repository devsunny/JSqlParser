package com.asksunny.db;



import org.junit.Test;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
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
		System.out.println(stmt.getClass());		
	}

}
