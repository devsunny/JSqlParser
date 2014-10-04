package net.sf.jsqlparser.statement.set;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;

public class Set implements Statement {

	
	private String name;
	private String value;
	
	
	public Set() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(StatementVisitor statementVisitor) {
		statementVisitor.visit(this);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;		
	}
	
	

}
