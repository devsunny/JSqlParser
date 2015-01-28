package com.asksunny.jdbc;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;


public class SunnyDatabaseMetaData implements DatabaseMetaData, SunnyJdbcDriverConstants {
    private final static java.util.HashMap<Integer, String> db_int2cmdMap = SunnyJDBCVTableMaps.DBMD_I2FN_MAP;
    private final static java.util.HashMap<String, Integer> db_cmd2intMap = SunnyJDBCVTableMaps.DBMD_FN2I_MAP;
    

    private java.util.HashMap<String, String> dbMetaData = new java.util.HashMap<String, String>();
    private SunnyConnection conn = null;


    public  SunnyDatabaseMetaData(SunnyConnection conn, String metadata) throws SQLException {
        this.conn=conn;
        this.dbMetaData=SunnyMetaDataUtil.convert2DatabaseMetaData(metadata);
    }

    public java.lang.String getURL()   throws SQLException {
        String val = dbMetaData.get("getURL");
        return val;
    }

    public boolean isReadOnly()   throws SQLException {
        String val = dbMetaData.get("isReadOnly");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public java.sql.ResultSet getAttributes(String param0,String param1,String param2,String param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getAttributes"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.RowIdLifetime getRowIdLifetime() throws SQLException {
        throw new SQLFeatureNotSupportedException("getRowIdLifetime([])");
    }

    public boolean supportsDataDefinitionAndDataManipulationTransactions()   throws SQLException {
        String val = dbMetaData.get("supportsDataDefinitionAndDataManipulationTransactions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public java.sql.ResultSet getCatalogs() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getCatalogs"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getColumns(String param0,String param1,String param2,String param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getColumns"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getFunctions(String param0,String param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getFunctions"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getIndexInfo(String param0,String param1,String param2,boolean param3,boolean param4) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getIndexInfo"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param4);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getSchemas() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getSchemas"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getSchemas(String param0,String param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getSchemas"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getTables(String param0,String param1,String param2,String[] param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getTables"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getTypeInfo() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getTypeInfo"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getUDTs(String param0,String param1,String param2,int[] param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getUDTs"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.lang.String getUserName()   throws SQLException {
        String val = dbMetaData.get("getUserName");
        return val;
    }

    public boolean updatesAreDetected(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("updatesAreDetected"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean usesLocalFilePerTable()   throws SQLException {
        String val = dbMetaData.get("usesLocalFilePerTable");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean usesLocalFiles()   throws SQLException {
        String val = dbMetaData.get("usesLocalFiles");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean allProceduresAreCallable()   throws SQLException {
        String val = dbMetaData.get("allProceduresAreCallable");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean allTablesAreSelectable()   throws SQLException {
        String val = dbMetaData.get("allTablesAreSelectable");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean deletesAreDetected(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("deletesAreDetected"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean doesMaxRowSizeIncludeBlobs()   throws SQLException {
        String val = dbMetaData.get("doesMaxRowSizeIncludeBlobs");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean generatedKeyAlwaysReturned()   throws SQLException {
        String val = dbMetaData.get("generatedKeyAlwaysReturned");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public java.sql.ResultSet getBestRowIdentifier(String param0,String param1,String param2,int param3,boolean param4) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getBestRowIdentifier"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param4);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.lang.String getCatalogSeparator()   throws SQLException {
        String val = dbMetaData.get("getCatalogSeparator");
        return val;
    }

    public java.lang.String getCatalogTerm()   throws SQLException {
        String val = dbMetaData.get("getCatalogTerm");
        return val;
    }

    public java.sql.ResultSet getClientInfoProperties() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getClientInfoProperties"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getColumnPrivileges(String param0,String param1,String param2,String param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getColumnPrivileges"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.Connection getConnection()   throws SQLException {
        String val = dbMetaData.get("getConnection");
            return this.conn;
    }

    public java.sql.ResultSet getCrossReference(String param0,String param1,String param2,String param3,String param4,String param5) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getCrossReference"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param4);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param5);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public int getDatabaseMajorVersion()   throws SQLException {
        String val = dbMetaData.get("getDatabaseMajorVersion");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getDatabaseMinorVersion()   throws SQLException {
        String val = dbMetaData.get("getDatabaseMinorVersion");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public java.lang.String getDatabaseProductName()   throws SQLException {
        String val = dbMetaData.get("getDatabaseProductName");
        return val;
    }

    public java.lang.String getDatabaseProductVersion()   throws SQLException {
        String val = dbMetaData.get("getDatabaseProductVersion");
        return val;
    }

    public int getDriverMajorVersion()   {
        String val = dbMetaData.get("getDriverMajorVersion");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getDriverMinorVersion()   {
        String val = dbMetaData.get("getDriverMinorVersion");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public java.lang.String getDriverName()   throws SQLException {
        String val = dbMetaData.get("getDriverName");
        return val;
    }

    public java.lang.String getDriverVersion()   throws SQLException {
        String val = dbMetaData.get("getDriverVersion");
        return val;
    }

    public java.sql.ResultSet getExportedKeys(String param0,String param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getExportedKeys"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.lang.String getExtraNameCharacters()   throws SQLException {
        String val = dbMetaData.get("getExtraNameCharacters");
        return val;
    }

    public java.sql.ResultSet getFunctionColumns(String param0,String param1,String param2,String param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getFunctionColumns"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.lang.String getIdentifierQuoteString()   throws SQLException {
        String val = dbMetaData.get("getIdentifierQuoteString");
        return val;
    }

    public java.sql.ResultSet getImportedKeys(String param0,String param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getImportedKeys"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public int getJDBCMajorVersion()   throws SQLException {
        String val = dbMetaData.get("getJDBCMajorVersion");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getJDBCMinorVersion()   throws SQLException {
        String val = dbMetaData.get("getJDBCMinorVersion");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxBinaryLiteralLength()   throws SQLException {
        String val = dbMetaData.get("getMaxBinaryLiteralLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxCatalogNameLength()   throws SQLException {
        String val = dbMetaData.get("getMaxCatalogNameLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxCharLiteralLength()   throws SQLException {
        String val = dbMetaData.get("getMaxCharLiteralLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxColumnNameLength()   throws SQLException {
        String val = dbMetaData.get("getMaxColumnNameLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxColumnsInGroupBy()   throws SQLException {
        String val = dbMetaData.get("getMaxColumnsInGroupBy");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxColumnsInIndex()   throws SQLException {
        String val = dbMetaData.get("getMaxColumnsInIndex");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxColumnsInOrderBy()   throws SQLException {
        String val = dbMetaData.get("getMaxColumnsInOrderBy");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxColumnsInSelect()   throws SQLException {
        String val = dbMetaData.get("getMaxColumnsInSelect");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxColumnsInTable()   throws SQLException {
        String val = dbMetaData.get("getMaxColumnsInTable");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxConnections()   throws SQLException {
        String val = dbMetaData.get("getMaxConnections");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxCursorNameLength()   throws SQLException {
        String val = dbMetaData.get("getMaxCursorNameLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxIndexLength()   throws SQLException {
        String val = dbMetaData.get("getMaxIndexLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxProcedureNameLength()   throws SQLException {
        String val = dbMetaData.get("getMaxProcedureNameLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxRowSize()   throws SQLException {
        String val = dbMetaData.get("getMaxRowSize");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxSchemaNameLength()   throws SQLException {
        String val = dbMetaData.get("getMaxSchemaNameLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxStatementLength()   throws SQLException {
        String val = dbMetaData.get("getMaxStatementLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxStatements()   throws SQLException {
        String val = dbMetaData.get("getMaxStatements");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxTableNameLength()   throws SQLException {
        String val = dbMetaData.get("getMaxTableNameLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxTablesInSelect()   throws SQLException {
        String val = dbMetaData.get("getMaxTablesInSelect");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public int getMaxUserNameLength()   throws SQLException {
        String val = dbMetaData.get("getMaxUserNameLength");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public java.lang.String getNumericFunctions()   throws SQLException {
        String val = dbMetaData.get("getNumericFunctions");
        return val;
    }

    public java.sql.ResultSet getPrimaryKeys(String param0,String param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getPrimaryKeys"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getProcedureColumns(String param0,String param1,String param2,String param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getProcedureColumns"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.lang.String getProcedureTerm()   throws SQLException {
        String val = dbMetaData.get("getProcedureTerm");
        return val;
    }

    public java.sql.ResultSet getProcedures(String param0,String param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getProcedures"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getPseudoColumns(String param0,String param1,String param2,String param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getPseudoColumns"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param3);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public int getResultSetHoldability()   throws SQLException {
        String val = dbMetaData.get("getResultSetHoldability");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public java.lang.String getSQLKeywords()   throws SQLException {
        String val = dbMetaData.get("getSQLKeywords");
        return val;
    }

    public int getSQLStateType()   throws SQLException {
        String val = dbMetaData.get("getSQLStateType");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public java.lang.String getSchemaTerm()   throws SQLException {
        String val = dbMetaData.get("getSchemaTerm");
        return val;
    }

    public java.lang.String getSearchStringEscape()   throws SQLException {
        String val = dbMetaData.get("getSearchStringEscape");
        return val;
    }

    public java.lang.String getStringFunctions()   throws SQLException {
        String val = dbMetaData.get("getStringFunctions");
        return val;
    }

    public java.sql.ResultSet getSuperTables(String param0,String param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getSuperTables"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getSuperTypes(String param0,String param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getSuperTypes"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.lang.String getSystemFunctions()   throws SQLException {
        String val = dbMetaData.get("getSystemFunctions");
        return val;
    }

    public java.sql.ResultSet getTablePrivileges(String param0,String param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getTablePrivileges"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.sql.ResultSet getTableTypes() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getTableTypes"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public java.lang.String getTimeDateFunctions()   throws SQLException {
        String val = dbMetaData.get("getTimeDateFunctions");
        return val;
    }

    public java.sql.ResultSet getVersionColumns(String param0,String param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("getVersionColumns"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param2);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            SunnyResultSet  rs = new  SunnyResultSet(conn, response.substring(1));
            return rs;
        }
    }

    public boolean insertsAreDetected(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("insertsAreDetected"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean isCatalogAtStart()   throws SQLException {
        String val = dbMetaData.get("isCatalogAtStart");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean locatorsUpdateCopy()   throws SQLException {
        String val = dbMetaData.get("locatorsUpdateCopy");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean nullPlusNonNullIsNull()   throws SQLException {
        String val = dbMetaData.get("nullPlusNonNullIsNull");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean nullsAreSortedAtEnd()   throws SQLException {
        String val = dbMetaData.get("nullsAreSortedAtEnd");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean nullsAreSortedAtStart()   throws SQLException {
        String val = dbMetaData.get("nullsAreSortedAtStart");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean nullsAreSortedHigh()   throws SQLException {
        String val = dbMetaData.get("nullsAreSortedHigh");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean nullsAreSortedLow()   throws SQLException {
        String val = dbMetaData.get("nullsAreSortedLow");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean othersDeletesAreVisible(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("othersDeletesAreVisible"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean othersInsertsAreVisible(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("othersInsertsAreVisible"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean othersUpdatesAreVisible(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("othersUpdatesAreVisible"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean ownDeletesAreVisible(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("ownDeletesAreVisible"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean ownInsertsAreVisible(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("ownInsertsAreVisible"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean ownUpdatesAreVisible(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("ownUpdatesAreVisible"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean storesLowerCaseIdentifiers()   throws SQLException {
        String val = dbMetaData.get("storesLowerCaseIdentifiers");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean storesMixedCaseIdentifiers()   throws SQLException {
        String val = dbMetaData.get("storesMixedCaseIdentifiers");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean storesUpperCaseIdentifiers()   throws SQLException {
        String val = dbMetaData.get("storesUpperCaseIdentifiers");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsANSI92EntryLevelSQL()   throws SQLException {
        String val = dbMetaData.get("supportsANSI92EntryLevelSQL");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsANSI92FullSQL()   throws SQLException {
        String val = dbMetaData.get("supportsANSI92FullSQL");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsBatchUpdates()   throws SQLException {
        String val = dbMetaData.get("supportsBatchUpdates");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsColumnAliasing()   throws SQLException {
        String val = dbMetaData.get("supportsColumnAliasing");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsConvert(int param0,int param1)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("supportsConvert"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean supportsConvert()   throws SQLException {
        String val = dbMetaData.get("supportsConvert");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsCoreSQLGrammar()   throws SQLException {
        String val = dbMetaData.get("supportsCoreSQLGrammar");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsCorrelatedSubqueries()   throws SQLException {
        String val = dbMetaData.get("supportsCorrelatedSubqueries");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsExpressionsInOrderBy()   throws SQLException {
        String val = dbMetaData.get("supportsExpressionsInOrderBy");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsExtendedSQLGrammar()   throws SQLException {
        String val = dbMetaData.get("supportsExtendedSQLGrammar");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsFullOuterJoins()   throws SQLException {
        String val = dbMetaData.get("supportsFullOuterJoins");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsGetGeneratedKeys()   throws SQLException {
        String val = dbMetaData.get("supportsGetGeneratedKeys");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsGroupBy()   throws SQLException {
        String val = dbMetaData.get("supportsGroupBy");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsGroupByBeyondSelect()   throws SQLException {
        String val = dbMetaData.get("supportsGroupByBeyondSelect");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsGroupByUnrelated()   throws SQLException {
        String val = dbMetaData.get("supportsGroupByUnrelated");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsLikeEscapeClause()   throws SQLException {
        String val = dbMetaData.get("supportsLikeEscapeClause");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsLimitedOuterJoins()   throws SQLException {
        String val = dbMetaData.get("supportsLimitedOuterJoins");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsMinimumSQLGrammar()   throws SQLException {
        String val = dbMetaData.get("supportsMinimumSQLGrammar");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsMixedCaseIdentifiers()   throws SQLException {
        String val = dbMetaData.get("supportsMixedCaseIdentifiers");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsMultipleOpenResults()   throws SQLException {
        String val = dbMetaData.get("supportsMultipleOpenResults");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsMultipleResultSets()   throws SQLException {
        String val = dbMetaData.get("supportsMultipleResultSets");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsMultipleTransactions()   throws SQLException {
        String val = dbMetaData.get("supportsMultipleTransactions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsNamedParameters()   throws SQLException {
        String val = dbMetaData.get("supportsNamedParameters");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsNonNullableColumns()   throws SQLException {
        String val = dbMetaData.get("supportsNonNullableColumns");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsOrderByUnrelated()   throws SQLException {
        String val = dbMetaData.get("supportsOrderByUnrelated");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsOuterJoins()   throws SQLException {
        String val = dbMetaData.get("supportsOuterJoins");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsPositionedDelete()   throws SQLException {
        String val = dbMetaData.get("supportsPositionedDelete");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsPositionedUpdate()   throws SQLException {
        String val = dbMetaData.get("supportsPositionedUpdate");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsResultSetConcurrency(int param0,int param1)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("supportsResultSetConcurrency"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean supportsResultSetHoldability(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("supportsResultSetHoldability"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean supportsResultSetType(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("supportsResultSetType"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    public boolean supportsSavepoints()   throws SQLException {
        String val = dbMetaData.get("supportsSavepoints");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSelectForUpdate()   throws SQLException {
        String val = dbMetaData.get("supportsSelectForUpdate");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsStatementPooling()   throws SQLException {
        String val = dbMetaData.get("supportsStatementPooling");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsStoredProcedures()   throws SQLException {
        String val = dbMetaData.get("supportsStoredProcedures");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSubqueriesInExists()   throws SQLException {
        String val = dbMetaData.get("supportsSubqueriesInExists");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSubqueriesInIns()   throws SQLException {
        String val = dbMetaData.get("supportsSubqueriesInIns");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsTransactions()   throws SQLException {
        String val = dbMetaData.get("supportsTransactions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsUnion()   throws SQLException {
        String val = dbMetaData.get("supportsUnion");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsUnionAll()   throws SQLException {
        String val = dbMetaData.get("supportsUnionAll");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean autoCommitFailureClosesAllResultSets()   throws SQLException {
        String val = dbMetaData.get("autoCommitFailureClosesAllResultSets");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean dataDefinitionCausesTransactionCommit()   throws SQLException {
        String val = dbMetaData.get("dataDefinitionCausesTransactionCommit");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean dataDefinitionIgnoredInTransactions()   throws SQLException {
        String val = dbMetaData.get("dataDefinitionIgnoredInTransactions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public int getDefaultTransactionIsolation()   throws SQLException {
        String val = dbMetaData.get("getDefaultTransactionIsolation");
        if(val==null) return 0;
        return Integer.valueOf(val);
    }

    public boolean storesLowerCaseQuotedIdentifiers()   throws SQLException {
        String val = dbMetaData.get("storesLowerCaseQuotedIdentifiers");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean storesMixedCaseQuotedIdentifiers()   throws SQLException {
        String val = dbMetaData.get("storesMixedCaseQuotedIdentifiers");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean storesUpperCaseQuotedIdentifiers()   throws SQLException {
        String val = dbMetaData.get("storesUpperCaseQuotedIdentifiers");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsANSI92IntermediateSQL()   throws SQLException {
        String val = dbMetaData.get("supportsANSI92IntermediateSQL");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsAlterTableWithAddColumn()   throws SQLException {
        String val = dbMetaData.get("supportsAlterTableWithAddColumn");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsAlterTableWithDropColumn()   throws SQLException {
        String val = dbMetaData.get("supportsAlterTableWithDropColumn");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsCatalogsInDataManipulation()   throws SQLException {
        String val = dbMetaData.get("supportsCatalogsInDataManipulation");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsCatalogsInIndexDefinitions()   throws SQLException {
        String val = dbMetaData.get("supportsCatalogsInIndexDefinitions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsCatalogsInPrivilegeDefinitions()   throws SQLException {
        String val = dbMetaData.get("supportsCatalogsInPrivilegeDefinitions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsCatalogsInProcedureCalls()   throws SQLException {
        String val = dbMetaData.get("supportsCatalogsInProcedureCalls");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsCatalogsInTableDefinitions()   throws SQLException {
        String val = dbMetaData.get("supportsCatalogsInTableDefinitions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsDataManipulationTransactionsOnly()   throws SQLException {
        String val = dbMetaData.get("supportsDataManipulationTransactionsOnly");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsDifferentTableCorrelationNames()   throws SQLException {
        String val = dbMetaData.get("supportsDifferentTableCorrelationNames");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsIntegrityEnhancementFacility()   throws SQLException {
        String val = dbMetaData.get("supportsIntegrityEnhancementFacility");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsMixedCaseQuotedIdentifiers()   throws SQLException {
        String val = dbMetaData.get("supportsMixedCaseQuotedIdentifiers");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsOpenCursorsAcrossCommit()   throws SQLException {
        String val = dbMetaData.get("supportsOpenCursorsAcrossCommit");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsOpenCursorsAcrossRollback()   throws SQLException {
        String val = dbMetaData.get("supportsOpenCursorsAcrossRollback");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsOpenStatementsAcrossCommit()   throws SQLException {
        String val = dbMetaData.get("supportsOpenStatementsAcrossCommit");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsOpenStatementsAcrossRollback()   throws SQLException {
        String val = dbMetaData.get("supportsOpenStatementsAcrossRollback");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSchemasInDataManipulation()   throws SQLException {
        String val = dbMetaData.get("supportsSchemasInDataManipulation");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSchemasInIndexDefinitions()   throws SQLException {
        String val = dbMetaData.get("supportsSchemasInIndexDefinitions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSchemasInPrivilegeDefinitions()   throws SQLException {
        String val = dbMetaData.get("supportsSchemasInPrivilegeDefinitions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSchemasInProcedureCalls()   throws SQLException {
        String val = dbMetaData.get("supportsSchemasInProcedureCalls");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSchemasInTableDefinitions()   throws SQLException {
        String val = dbMetaData.get("supportsSchemasInTableDefinitions");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsStoredFunctionsUsingCallSyntax()   throws SQLException {
        String val = dbMetaData.get("supportsStoredFunctionsUsingCallSyntax");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSubqueriesInComparisons()   throws SQLException {
        String val = dbMetaData.get("supportsSubqueriesInComparisons");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsSubqueriesInQuantifieds()   throws SQLException {
        String val = dbMetaData.get("supportsSubqueriesInQuantifieds");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsTableCorrelationNames()   throws SQLException {
        String val = dbMetaData.get("supportsTableCorrelationNames");
        if(val==null) return false;
        return (val.equals("1") || val.equals("true"));
    }

    public boolean supportsTransactionIsolationLevel(int param0)   throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(db_cmd2intMap.get("supportsTransactionIsolationLevel"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String val = response.substring(1);
            if(val==null) return false;
            return (val.equals("1") || val.equals("true"));
        }
    }

    protected String sendRemoteRequest(String command) throws SQLException
    {    	
    	return "0";
    }
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {		
    	return null;
    }
    
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {	
    	return false;
    }
}


