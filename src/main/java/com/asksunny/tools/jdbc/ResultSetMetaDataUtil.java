package com.asksunny.tools.jdbc;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ResultSetMetaDataUtil {
	private final static char FIELD_DELIMITER = (char) 1;
	private final static char RECORD_DELIMITER = (char) 2;
	private final static java.util.HashMap<Integer, String> int2cmdMap = new java.util.HashMap<Integer, String>();
	private final static java.util.HashMap<String, Integer> cmd2intMap = new java.util.HashMap<String, Integer>();
	static {
		int2cmdMap.put(101, "isReadOnly");
		cmd2intMap.put("isReadOnly", 101);
		int2cmdMap.put(102, "getPrecision");
		cmd2intMap.put("getPrecision", 102);
		int2cmdMap.put(103, "getScale");
		cmd2intMap.put("getScale", 103);
		int2cmdMap.put(104, "getTableName");
		cmd2intMap.put("getTableName", 104);
		int2cmdMap.put(105, "isCurrency");
		cmd2intMap.put("isCurrency", 105);
		int2cmdMap.put(106, "isNullable");
		cmd2intMap.put("isNullable", 106);
		int2cmdMap.put(107, "isSearchable");
		cmd2intMap.put("isSearchable", 107);
		int2cmdMap.put(108, "isSigned");
		cmd2intMap.put("isSigned", 108);
		int2cmdMap.put(109, "isWritable");
		cmd2intMap.put("isWritable", 109);
		int2cmdMap.put(110, "getCatalogName");
		cmd2intMap.put("getCatalogName", 110);
		int2cmdMap.put(111, "getColumnClassName");
		cmd2intMap.put("getColumnClassName", 111);
		int2cmdMap.put(112, "getColumnCount");
		cmd2intMap.put("getColumnCount", 112);
		int2cmdMap.put(113, "getColumnDisplaySize");
		cmd2intMap.put("getColumnDisplaySize", 113);
		int2cmdMap.put(114, "getColumnLabel");
		cmd2intMap.put("getColumnLabel", 114);
		int2cmdMap.put(115, "getColumnName");
		cmd2intMap.put("getColumnName", 115);
		int2cmdMap.put(116, "getColumnType");
		cmd2intMap.put("getColumnType", 116);
		int2cmdMap.put(117, "getColumnTypeName");
		cmd2intMap.put("getColumnTypeName", 117);
		int2cmdMap.put(118, "getSchemaName");
		cmd2intMap.put("getSchemaName", 118);
		int2cmdMap.put(119, "isAutoIncrement");
		cmd2intMap.put("isAutoIncrement", 119);
		int2cmdMap.put(120, "isCaseSensitive");
		cmd2intMap.put("isCaseSensitive", 120);
		int2cmdMap.put(121, "isDefinitelyWritable");
		cmd2intMap.put("isDefinitelyWritable", 121);
		int2cmdMap.put(122, "unwrap");
		cmd2intMap.put("unwrap", 122);
		int2cmdMap.put(123, "isWrapperFor");
		cmd2intMap.put("isWrapperFor", 123);
	}

	public static List<HashMap<String, String>> convertToHashMap(String dbmd)
			throws SQLException {
		
		List<HashMap<String, String>> rsMds = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> dbmdmap = new HashMap<String, String>();
		int len = dbmd.length();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < len; i++) {
			Stat stat = readField(buf, dbmd, i, len, FIELD_DELIMITER, RECORD_DELIMITER);
			i = stat.offset;
			String key = buf.toString();
			if(key.length()>0){
				buf.setLength(0);
				key = int2cmdMap.get(Integer.valueOf(key));
				i++;
				stat = readField(buf, dbmd, i, len,  FIELD_DELIMITER, RECORD_DELIMITER);
				i = stat.offset;
				String val = buf.toString();
				buf.setLength(0);
				dbmdmap.put(key, val);
			}			
			if(stat.endOfRecord || key.length()==0){
				rsMds.add(dbmdmap);
				dbmdmap = new HashMap<String, String>();
			}
		}		
		return rsMds;
	}

	protected static Stat readField(StringBuilder buf, String dbmd, int offset,
			int len, char delimiter, char delimiter2) {
		int i = offset;
		Stat stat = new Stat();
		for (; i < len; i++) {
			char c = dbmd.charAt(i);
			if (c == delimiter ){
				break;
			} else if(delimiter2 > 0 && c == delimiter2){
				stat.endOfRecord = true;
				break;
			}else{
				buf.append(c);
			}				
		}
		stat.offset = i;
		return stat;
	}
	
	protected static class Stat
	{
		public int offset;
		public boolean endOfRecord = false;
	}

	public static String convertToString(ResultSetMetaData rsmd)
			throws SQLException {
		StringBuilder dbmsbuf = new StringBuilder();
		int colcount = rsmd.getColumnCount();
		for (int i = 0; i < colcount; i++) {
			int colidx = i + 1;
			dbmsbuf.append(cmd2intMap.get("isReadOnly"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.isReadOnly(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("isCaseSensitive"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.isCaseSensitive(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getPrecision"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getPrecision(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getScale"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getScale(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("isSigned"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.isSigned(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("isNullable"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.isNullable(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getTableName"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getTableName(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("isCurrency"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.isCurrency(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("isSearchable"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.isSearchable(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("isWritable"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.isWritable(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getColumnType"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getColumnType(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getColumnLabel"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getColumnLabel(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getColumnName"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getColumnName(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getSchemaName"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getSchemaName(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getCatalogName"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getCatalogName(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getColumnClassName"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getColumnClassName(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getColumnDisplaySize"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getColumnDisplaySize(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("getColumnTypeName"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.getColumnTypeName(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("isAutoIncrement"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.isAutoIncrement(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}

			dbmsbuf.append(FIELD_DELIMITER);
			dbmsbuf.append(cmd2intMap.get("isDefinitelyWritable"));
			dbmsbuf.append(FIELD_DELIMITER);
			try {
				dbmsbuf.append(rsmd.isDefinitelyWritable(colidx));
			} catch (SQLException sex) {
				;// Ignore it;
			}
			dbmsbuf.append(FIELD_DELIMITER);
			if(colidx!=colcount) dbmsbuf.append(RECORD_DELIMITER);

		}

		return dbmsbuf.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
