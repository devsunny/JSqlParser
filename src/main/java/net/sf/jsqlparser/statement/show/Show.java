package net.sf.jsqlparser.statement.show;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;

public class Show implements Statement {

	
	private String name = null;
	
	public Show() {		
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
	
	

}
