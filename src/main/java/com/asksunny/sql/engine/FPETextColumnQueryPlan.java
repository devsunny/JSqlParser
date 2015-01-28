package com.asksunny.sql.engine;

import java.sql.SQLException;
import java.util.List;

public class FPETextColumnQueryPlan extends TextDataColumnQueryPlan<String> {

	private boolean deIdentify = false;
	

	public FPETextColumnQueryPlan(Class<String> returnType) {
		super(returnType);		
	}

	
	@Override
	public String eval(List<String> fieldData) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String eval(String[] fieldData) throws SQLException {
		
		return null;
	}

	public boolean isDeIdentify() {
		return deIdentify;
	}

	public void setDeIdentify(boolean deIdentify) {
		this.deIdentify = deIdentify;
	}
	
	

}
