package com.asksunny.tools;

public class JavaIdentifierTransformer {

	String varName = null;
	String accessorName = null;
	String column_name = null;

	
	
	
	public JavaIdentifierTransformer(String identifier) 
	{

		boolean sep = identifier.contains("_");
		if (sep) {
			String[] nps = identifier.split("_");
			StringBuilder vnBuf = new StringBuilder();
			StringBuilder anBuf = new StringBuilder();
			for (int i = 0; i < nps.length; i++) {
				String l_col_name = nps[i].toLowerCase();
				if (i == 0) {
					vnBuf.append(Character.toLowerCase(l_col_name.charAt(0)));
					vnBuf.append(nps[i].substring(1));
					anBuf.append(Character.toUpperCase(l_col_name.charAt(0)));
					anBuf.append(nps[i].substring(1));
				} else {
					vnBuf.append(Character.toUpperCase(l_col_name.charAt(0)));
					vnBuf.append(nps[i].substring(1));
					anBuf.append(Character.toUpperCase(l_col_name.charAt(0)));
					anBuf.append(nps[i].substring(1));
				}
			}
			column_name = identifier.toLowerCase();
			varName = vnBuf.toString();
			accessorName = anBuf.toString();
		} else {
			column_name = identifier.toLowerCase();
			varName = column_name;
			accessorName = (new StringBuilder())
					.append(Character.toUpperCase(column_name.charAt(0)))
					.append(column_name.substring(1)).toString();
		}

	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;		
	}

	public String getAccessorName() {
		return accessorName;
	}

	public void setAccessorName(String accessorName) {
		this.accessorName = accessorName;		
	}

	public String getDbName() {
		return column_name;
	}

	
}
