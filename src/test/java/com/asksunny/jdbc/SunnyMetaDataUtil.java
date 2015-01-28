package com.asksunny.jdbc;
import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.sql.ParameterMetaData;


public final class SunnyMetaDataUtil implements  SunnyJdbcDriverConstants {
    private final static java.util.HashMap<Integer, String> rs_int2cmdMap = SunnyJDBCVTableMaps.RSMD_I2FN_MAP;
    private final static java.util.HashMap<String, Integer> rs_cmd2intMap = SunnyJDBCVTableMaps.RSMD_FN2I_MAP;
    private final static java.util.HashMap<Integer, String> db_int2cmdMap = SunnyJDBCVTableMaps.DBMD_I2FN_MAP;
    private final static java.util.HashMap<String, Integer> db_cmd2intMap = SunnyJDBCVTableMaps.DBMD_FN2I_MAP;
    private final static java.util.HashMap<Integer, String> prm_int2cmdMap = SunnyJDBCVTableMaps.PARAMMD_I2FN_MAP;
    private final static java.util.HashMap<String, Integer> prm_cmd2intMap = SunnyJDBCVTableMaps.PARAMMD_FN2I_MAP;




    protected static Stat readField(StringBuilder buf, String dbmd, int offset,int len, char delimiter, char delimiter2) {

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
    		if(i==len-1) stat.endOfRecord = true;
    	}
    	stat.offset = i;
    	return stat;
    }
    
    protected static class Stat
    {
    	public int offset;
    	public boolean endOfRecord = false;
    }
    public static HashMap<String, Class<?>> convert2TypeMap(String dbmd) throws SQLException {
    	HashMap<String, Class<?>> dbmdmap = new HashMap<>();
    	int len = dbmd.length();
    	StringBuilder buf = new StringBuilder();
    	for (int i = 0; i < len; i++) {
    		Stat stat = readField(buf, dbmd, i, len, FIELD_DELIMITER, RECORD_DELIMITER);
    		if(stat.endOfRecord) continue;
    		i = stat.offset;
    		String key = buf.toString();	
    		buf.setLength(0);			
    		i++;
    		stat = readField(buf, dbmd, i, len, RECORD_DELIMITER, (char) -1);
    		String val = buf.toString();
    		buf.setLength(0);
    		i = stat.offset;
    		try{
    			Class<?> cl = Class.forName(val);
    			dbmdmap.put(key, cl);	
    		}catch(Exception ex){
    			throw new SQLException(String.format("Class not found exception [%s]", val));
    		}
    	}
    	return dbmdmap;
    }
    public static String convert2String(ResultSetMetaData rsmd)  throws SQLException {

        StringBuilder dbmsbuf = new StringBuilder();
        int colcount = rsmd.getColumnCount();
        for (int i = 0; i < colcount; i++) {
            int colidx = i+1;
            dbmsbuf.append(rs_cmd2intMap.get("isReadOnly"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.isReadOnly(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getPrecision"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getPrecision(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getScale"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getScale(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getTableName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getTableName(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("isCurrency"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.isCurrency(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("isNullable"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.isNullable(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("isSearchable"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.isSearchable(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("isSigned"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.isSigned(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("isWritable"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.isWritable(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getCatalogName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getCatalogName(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getColumnClassName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getColumnClassName(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getColumnDisplaySize"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getColumnDisplaySize(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getColumnLabel"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getColumnLabel(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getColumnName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getColumnName(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getColumnType"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getColumnType(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getColumnTypeName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getColumnTypeName(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("getSchemaName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.getSchemaName(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("isAutoIncrement"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.isAutoIncrement(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("isCaseSensitive"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.isCaseSensitive(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(rs_cmd2intMap.get("isDefinitelyWritable"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(rsmd.isDefinitelyWritable(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
             if (colidx<colcount) dbmsbuf.append(RECORD_DELIMITER) ;
        }

        return dbmsbuf.toString();
    }

    public static List<HashMap<String, String>> convert2ResultSetMetaData(String metadata)  throws SQLException {

        List<HashMap<String, String>> rsMds = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> rsmdmap = new HashMap<String, String>();
        int len = metadata.length();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < len; i++) {
        	Stat stat = readField(buf, metadata, i, len, FIELD_DELIMITER, RECORD_DELIMITER);
        	i = stat.offset;
        	String key = buf.toString();
        	if(key.length()>0){
        		buf.setLength(0);
        		key = rs_int2cmdMap.get(Integer.valueOf(key));
        		i++;
        		stat = readField(buf, metadata, i, len,  FIELD_DELIMITER, RECORD_DELIMITER);
        		i = stat.offset;
        		String val = buf.toString();
        		buf.setLength(0);
        		rsmdmap.put(key, val);
        	}			
        	if(stat.endOfRecord || key.length()==0){
        		rsMds.add(rsmdmap);
        		rsmdmap = new HashMap<String, String>();
        	}
        }		
        return rsMds;
    }

    public static String convert2String(DatabaseMetaData dbmd) throws SQLException {

        StringBuilder dbmsbuf = new StringBuilder();
            dbmsbuf.append(db_cmd2intMap.get("getURL"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getURL());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("isReadOnly"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.isReadOnly());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsDataDefinitionAndDataManipulationTransactions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsDataDefinitionAndDataManipulationTransactions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("allProceduresAreCallable"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.allProceduresAreCallable());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("allTablesAreSelectable"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.allTablesAreSelectable());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("doesMaxRowSizeIncludeBlobs"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.doesMaxRowSizeIncludeBlobs());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("generatedKeyAlwaysReturned"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.generatedKeyAlwaysReturned());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getCatalogSeparator"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getCatalogSeparator());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getCatalogTerm"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getCatalogTerm());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getConnection"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getConnection());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getDatabaseMajorVersion"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getDatabaseMajorVersion());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getDatabaseMinorVersion"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getDatabaseMinorVersion());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getDatabaseProductName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getDatabaseProductName());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getDatabaseProductVersion"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getDatabaseProductVersion());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getDriverMajorVersion"));
            dbmsbuf.append(FIELD_DELIMITER);
                dbmsbuf.append(dbmd.getDriverMajorVersion());
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getDriverMinorVersion"));
            dbmsbuf.append(FIELD_DELIMITER);
                dbmsbuf.append(dbmd.getDriverMinorVersion());
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getDriverName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getDriverName());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getDriverVersion"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getDriverVersion());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getExtraNameCharacters"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getExtraNameCharacters());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getIdentifierQuoteString"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getIdentifierQuoteString());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getJDBCMajorVersion"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getJDBCMajorVersion());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getJDBCMinorVersion"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getJDBCMinorVersion());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxBinaryLiteralLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxBinaryLiteralLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxCatalogNameLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxCatalogNameLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxCharLiteralLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxCharLiteralLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxColumnNameLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxColumnNameLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxColumnsInGroupBy"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxColumnsInGroupBy());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxColumnsInIndex"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxColumnsInIndex());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxColumnsInOrderBy"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxColumnsInOrderBy());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxColumnsInSelect"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxColumnsInSelect());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxColumnsInTable"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxColumnsInTable());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxConnections"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxConnections());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxCursorNameLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxCursorNameLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxIndexLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxIndexLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxProcedureNameLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxProcedureNameLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxRowSize"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxRowSize());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxSchemaNameLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxSchemaNameLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxStatementLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxStatementLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxStatements"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxStatements());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxTableNameLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxTableNameLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxTablesInSelect"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxTablesInSelect());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getMaxUserNameLength"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getMaxUserNameLength());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getNumericFunctions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getNumericFunctions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getProcedureTerm"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getProcedureTerm());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getResultSetHoldability"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getResultSetHoldability());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getRowIdLifetime"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getRowIdLifetime());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getSQLKeywords"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getSQLKeywords());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getSQLStateType"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getSQLStateType());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getSchemaTerm"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getSchemaTerm());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getSearchStringEscape"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getSearchStringEscape());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getStringFunctions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getStringFunctions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getSystemFunctions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getSystemFunctions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getTimeDateFunctions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getTimeDateFunctions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("isCatalogAtStart"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.isCatalogAtStart());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("locatorsUpdateCopy"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.locatorsUpdateCopy());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("nullPlusNonNullIsNull"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.nullPlusNonNullIsNull());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("nullsAreSortedAtEnd"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.nullsAreSortedAtEnd());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("nullsAreSortedAtStart"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.nullsAreSortedAtStart());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("nullsAreSortedHigh"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.nullsAreSortedHigh());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("nullsAreSortedLow"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.nullsAreSortedLow());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("storesLowerCaseIdentifiers"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.storesLowerCaseIdentifiers());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("storesMixedCaseIdentifiers"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.storesMixedCaseIdentifiers());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("storesUpperCaseIdentifiers"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.storesUpperCaseIdentifiers());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsANSI92EntryLevelSQL"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsANSI92EntryLevelSQL());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsANSI92FullSQL"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsANSI92FullSQL());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsBatchUpdates"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsBatchUpdates());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsColumnAliasing"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsColumnAliasing());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsConvert"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsConvert());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsCoreSQLGrammar"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsCoreSQLGrammar());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsCorrelatedSubqueries"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsCorrelatedSubqueries());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsExpressionsInOrderBy"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsExpressionsInOrderBy());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsExtendedSQLGrammar"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsExtendedSQLGrammar());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsFullOuterJoins"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsFullOuterJoins());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsGetGeneratedKeys"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsGetGeneratedKeys());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsGroupBy"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsGroupBy());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsGroupByBeyondSelect"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsGroupByBeyondSelect());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsGroupByUnrelated"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsGroupByUnrelated());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsLikeEscapeClause"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsLikeEscapeClause());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsLimitedOuterJoins"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsLimitedOuterJoins());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsMinimumSQLGrammar"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsMinimumSQLGrammar());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsMixedCaseIdentifiers"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsMixedCaseIdentifiers());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsMultipleOpenResults"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsMultipleOpenResults());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsMultipleResultSets"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsMultipleResultSets());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsMultipleTransactions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsMultipleTransactions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getUserName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getUserName());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsNamedParameters"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsNamedParameters());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsNonNullableColumns"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsNonNullableColumns());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsOrderByUnrelated"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsOrderByUnrelated());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsOuterJoins"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsOuterJoins());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsPositionedDelete"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsPositionedDelete());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsPositionedUpdate"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsPositionedUpdate());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSavepoints"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSavepoints());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSelectForUpdate"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSelectForUpdate());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsStatementPooling"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsStatementPooling());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsStoredProcedures"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsStoredProcedures());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSubqueriesInExists"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSubqueriesInExists());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSubqueriesInIns"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSubqueriesInIns());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsTransactions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsTransactions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsUnion"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsUnion());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsUnionAll"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsUnionAll());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("usesLocalFilePerTable"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.usesLocalFilePerTable());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("usesLocalFiles"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.usesLocalFiles());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("autoCommitFailureClosesAllResultSets"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.autoCommitFailureClosesAllResultSets());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("dataDefinitionCausesTransactionCommit"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.dataDefinitionCausesTransactionCommit());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("dataDefinitionIgnoredInTransactions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.dataDefinitionIgnoredInTransactions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("getDefaultTransactionIsolation"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.getDefaultTransactionIsolation());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("storesLowerCaseQuotedIdentifiers"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.storesLowerCaseQuotedIdentifiers());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("storesMixedCaseQuotedIdentifiers"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.storesMixedCaseQuotedIdentifiers());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("storesUpperCaseQuotedIdentifiers"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.storesUpperCaseQuotedIdentifiers());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsANSI92IntermediateSQL"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsANSI92IntermediateSQL());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsAlterTableWithAddColumn"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsAlterTableWithAddColumn());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsAlterTableWithDropColumn"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsAlterTableWithDropColumn());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsCatalogsInDataManipulation"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsCatalogsInDataManipulation());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsCatalogsInIndexDefinitions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsCatalogsInIndexDefinitions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsCatalogsInPrivilegeDefinitions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsCatalogsInPrivilegeDefinitions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsCatalogsInProcedureCalls"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsCatalogsInProcedureCalls());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsCatalogsInTableDefinitions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsCatalogsInTableDefinitions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsDataManipulationTransactionsOnly"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsDataManipulationTransactionsOnly());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsDifferentTableCorrelationNames"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsDifferentTableCorrelationNames());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsIntegrityEnhancementFacility"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsIntegrityEnhancementFacility());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsMixedCaseQuotedIdentifiers"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsMixedCaseQuotedIdentifiers());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsOpenCursorsAcrossCommit"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsOpenCursorsAcrossCommit());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsOpenCursorsAcrossRollback"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsOpenCursorsAcrossRollback());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsOpenStatementsAcrossCommit"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsOpenStatementsAcrossCommit());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsOpenStatementsAcrossRollback"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsOpenStatementsAcrossRollback());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSchemasInDataManipulation"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSchemasInDataManipulation());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSchemasInIndexDefinitions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSchemasInIndexDefinitions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSchemasInPrivilegeDefinitions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSchemasInPrivilegeDefinitions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSchemasInProcedureCalls"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSchemasInProcedureCalls());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSchemasInTableDefinitions"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSchemasInTableDefinitions());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsStoredFunctionsUsingCallSyntax"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsStoredFunctionsUsingCallSyntax());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSubqueriesInComparisons"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSubqueriesInComparisons());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsSubqueriesInQuantifieds"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsSubqueriesInQuantifieds());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
            dbmsbuf.append(db_cmd2intMap.get("supportsTableCorrelationNames"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(dbmd.supportsTableCorrelationNames());
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(RECORD_DELIMITER);
        return dbmsbuf.toString();
    }

    public static HashMap<String, String> convert2DatabaseMetaData(String dbmd) throws SQLException {
    	HashMap<String, String> dbmdmap = new HashMap<>();
    	int len = dbmd.length();
    	StringBuilder buf = new StringBuilder();
    	for (int i = 0; i < len; i++) {
    		Stat stat = readField(buf, dbmd, i, len, FIELD_DELIMITER, RECORD_DELIMITER);
    		if(stat.endOfRecord) continue;
    		i = stat.offset;
    		String key = buf.toString();			
        		key = db_int2cmdMap.get(Integer.valueOf(key));
    		buf.setLength(0);			
    		i++;
    		stat = readField(buf, dbmd, i, len, RECORD_DELIMITER, (char) -1);
    		String val = buf.toString();
    		buf.setLength(0);
    		i = stat.offset;
    		dbmdmap.put(key, val);			
    	}
    	return dbmdmap;
    }
    public static String convert2String(ParameterMetaData prmmd)  throws SQLException {

        StringBuilder dbmsbuf = new StringBuilder();
        int pcount = prmmd.getParameterCount();
        for (int i = 0; i <pcount ; i++) {
            int colidx = i+1;
            dbmsbuf.append(prm_cmd2intMap.get("getPrecision"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(prmmd.getPrecision(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(prm_cmd2intMap.get("getScale"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(prmmd.getScale(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(prm_cmd2intMap.get("isNullable"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(prmmd.isNullable(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(prm_cmd2intMap.get("isSigned"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(prmmd.isSigned(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(prm_cmd2intMap.get("getParameterClassName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(prmmd.getParameterClassName(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(prm_cmd2intMap.get("getParameterMode"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(prmmd.getParameterMode(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(prm_cmd2intMap.get("getParameterType"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(prmmd.getParameterType(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
            dbmsbuf.append(FIELD_DELIMITER);
            dbmsbuf.append(prm_cmd2intMap.get("getParameterTypeName"));
            dbmsbuf.append(FIELD_DELIMITER);
            try{
                dbmsbuf.append(prmmd.getParameterTypeName(colidx));
            } catch(SQLException sex) {
                ;//ignore it 
            } 
             if (colidx<pcount) dbmsbuf.append(RECORD_DELIMITER) ;
        }

        return dbmsbuf.toString();
    }

    public static List<HashMap<String, String>> convert2ParameterMetaData(String metadata)  throws SQLException {

        List<HashMap<String, String>> rsMds = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> rsmdmap = new HashMap<String, String>();
        int len = metadata.length();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < len; i++) {
        	Stat stat = readField(buf, metadata, i, len, FIELD_DELIMITER, RECORD_DELIMITER);
        	i = stat.offset;
        	String key = buf.toString();
        	if(key.length()>0){
        		buf.setLength(0);
        		key = prm_int2cmdMap.get(Integer.valueOf(key));
        		i++;
        		stat = readField(buf, metadata, i, len,  FIELD_DELIMITER, RECORD_DELIMITER);
        		i = stat.offset;
        		String val = buf.toString();
        		buf.setLength(0);
        		rsmdmap.put(key, val);
        	}			
        	if(stat.endOfRecord || key.length()==0){
        		rsMds.add(rsmdmap);
        		rsmdmap = new HashMap<String, String>();
        	}
        }		
        return rsMds;
    }

}


