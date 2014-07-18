package com.asksunny.tools.spring.gen;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.asksunny.tools.domain.schema.EntityDef;
import com.asksunny.tools.domain.schema.FieldDef;

public class FieldTypeExtractor 
{
	/**
	 * Example: number(12, 4)
	 * Group 1: TypeName: number
	 * Group 3: Size of field: 12
	 * GROUP 5: Decimal length: 4
	 */
	public final static Pattern TYPE_Pattern = Pattern.compile("(\\w+)(\\s*\\(\\s*(\\d+)\\s*(,\\s*(\\d+)\\s*)?\\))?",  Pattern.CASE_INSENSITIVE);
	public final static Pattern NULLABLE_Pattern = Pattern.compile("\\s+not\\s+null(\\s+)?",  Pattern.CASE_INSENSITIVE);
	public final static Pattern PK_Pattern = Pattern.compile("\\s+primary\\s+key(\\s+)?",  Pattern.CASE_INSENSITIVE);
	public final static Pattern CNSTRNT_PK_Pattern = Pattern.compile("\\s*primary\\s+key\\s*\\(\\s*((\\w+)\\s*(,\\s*(\\w+)\\s*)*)\\)",  Pattern.CASE_INSENSITIVE);
	public final static Pattern FK_Pattern = Pattern.compile("FOREIGN\\s+KEY\\s+REFERENCES\\s+(\\w+)\\(\\s*(\\w+)\\s*\\)",  Pattern.CASE_INSENSITIVE);
	public final static Pattern CNSTRNT_FK_Pattern = Pattern.compile("FOREIGN\\s+KEY\\s*\\((\\s*\\w+\\s*(,\\s*\\w+\\s*)*)\\)\\s*REFERENCES\\s+(\\w+)\\((\\s*\\w+\\s*(,\\s*\\w+\\s*)*)\\)",  Pattern.CASE_INSENSITIVE);
		
	
	
	
	public static void extractType(FieldDef def, String typedef)
	{
		Matcher m = TYPE_Pattern.matcher(typedef);
		if(m.find())
		{
			
			def.setTypename(m.group(1));
			String l = m.group(3);
			if(l!=null){
				def.setLength(Integer.valueOf(l));
			}	
			if(m.group(5)!=null  && Integer.valueOf(m.group(5))>0){
				def.setDecimal(true);
			}
		}
	}
	
	public static void extractNullable(FieldDef def, String typedef)
	{
		Matcher m = NULLABLE_Pattern.matcher(typedef);
		if(m.find())
		{
			def.setNullable(Boolean.FALSE);		
		}else{
			def.setNullable(Boolean.TRUE);	
		}
	}
	
	
	public static void isPrimaryKey(FieldDef def, String typedef)
	{
		Matcher m = PK_Pattern.matcher(typedef);
		if(m.find())
		{
			def.setPrimaryKey(Boolean.TRUE);		
		}else{
			def.setPrimaryKey(Boolean.FALSE);	
		}
	}
	
	public static void isForeignKey(FieldDef def, String typedef)
	{
		Matcher m = FK_Pattern.matcher(typedef);
		if(m.find())
		{
			String ftbl = m.group(1);
			String fcolName = m.group(2);
			def.setForeignKey(Boolean.TRUE);
			def.setForeignTable(ftbl);
			def.setForeignColumnName(fcolName);		
		}else{
			def.setPrimaryKey(Boolean.FALSE);	
		}
	}
	
	public static void setPrimaryKey(EntityDef def, String constraintPk)
	{
		Matcher m = CNSTRNT_PK_Pattern.matcher(constraintPk);
		if(m.find())
		{
			String cols = m.group(1);			
			String[] cs = cols.split(",");	
			for (String string : cs) {
				String colName = string.trim();
				List<FieldDef> coldefs  = def.getFields();
				for (FieldDef fieldDef : coldefs) {
					String name = fieldDef.getName();
					if(name.equalsIgnoreCase(colName)){
						fieldDef.setPrimaryKey(Boolean.TRUE);
					}
				}				
			}			
		}
	}
	
	
	public static void setForeignKey(EntityDef def, String constraintFk)
	{
		Matcher m = CNSTRNT_FK_Pattern.matcher(constraintFk);
		if(m.find())
		{
			String cols = m.group(1);
			String ftbl = m.group(3);
			String fcols = m.group(4);
			String[] cs = cols.split(",");	
			String[] fcs = fcols.split(",");
			for (int i=0; i<cs.length; i++) {
				String colName = cs[i].trim();
				String fcolName = fcs[i].trim();
				List<FieldDef> coldefs  = def.getFields();
				for (FieldDef fieldDef : coldefs) {
					String name = fieldDef.getName();
					if(name.equalsIgnoreCase(colName)){
						fieldDef.setForeignKey(Boolean.TRUE);
						fieldDef.setForeignTable(ftbl);
						fieldDef.setForeignColumnName(fcolName);						
					}
				}				
			}			
		}
	}
	
}
