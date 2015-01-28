package com.asksunny.jdbc;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.SQLWarning;


public class SunnyStatement implements Statement, SunnyJdbcDriverConstants {
    private final static HashMap<Integer, String> stmt_int2cmdMap = SunnyJDBCVTableMaps.STMT_I2FN_MAP;
    private final static HashMap<String, Integer> stmt_cmd2intMap = SunnyJDBCVTableMaps.STMT_FN2I_MAP;


    private SunnyConnection connection=null;


    public  SunnyStatement(SunnyConnection conn) throws SQLException {
        this.connection=conn;
    }

    public void close() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("close"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public boolean execute(String param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("execute"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal.equals("1") || respVal.equals("true");
        }
    }

    public boolean execute(String param0,int[] param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("execute"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal.equals("1") || respVal.equals("true");
        }
    }

    public boolean execute(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("execute"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal.equals("1") || respVal.equals("true");
        }
    }

    public boolean execute(String param0,String[] param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("execute"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal.equals("1") || respVal.equals("true");
        }
    }

    public SQLWarning getWarnings() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getWarnings"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SQLWarning(response.substring(1));        }
    }

    public int getFetchSize() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getFetchSize"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public boolean isClosed() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("isClosed"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal.equals("1") || respVal.equals("true");
        }
    }

    public void setFetchSize(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setFetchSize"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void addBatch(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("addBatch"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void cancel() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("cancel"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void clearBatch() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("clearBatch"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public int[] executeBatch() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("executeBatch"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String[] retstrs = response.substring(1).split(",");
            int[] ret =new int[retstrs.length];
            for(int i=0; i<retstrs.length; i++){
                ret[i]=Integer.valueOf(retstrs[i]).intValue(); 
            }
            return ret;
        }
    }

    public ResultSet executeQuery(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("executeQuery"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyResultSet(this.connection, this, response.substring(1));        }
    }

    public int getMaxRows() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getMaxRows"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public ResultSet getResultSet() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getResultSet"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyResultSet(this.connection, this, response.substring(1));        }
    }

    public boolean isPoolable() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("isPoolable"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal.equals("1") || respVal.equals("true");
        }
    }

    public void setMaxRows(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setMaxRows"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void setPoolable(boolean param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setPoolable"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void clearWarnings() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("clearWarnings"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public int getFetchDirection() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getFetchDirection"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public void setFetchDirection(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setFetchDirection"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void closeOnCompletion() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("closeOnCompletion"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public int executeUpdate(String param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("executeUpdate"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public int executeUpdate(String param0,int[] param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("executeUpdate"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public int executeUpdate(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("executeUpdate"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public int executeUpdate(String param0,String[] param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("executeUpdate"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getGeneratedKeys"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyResultSet(this.connection, this, response.substring(1));        }
    }

    public int getMaxFieldSize() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getMaxFieldSize"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public boolean getMoreResults(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getMoreResults"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal.equals("1") || respVal.equals("true");
        }
    }

    public boolean getMoreResults() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getMoreResults"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal.equals("1") || respVal.equals("true");
        }
    }

    public int getQueryTimeout() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getQueryTimeout"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public int getResultSetConcurrency() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getResultSetConcurrency"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public int getResultSetHoldability() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getResultSetHoldability"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public int getResultSetType() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getResultSetType"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public int getUpdateCount() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getUpdateCount"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return Integer.valueOf(respVal);
        }
    }

    public boolean isCloseOnCompletion() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("isCloseOnCompletion"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal.equals("1") || respVal.equals("true");
        }
    }

    public void setCursorName(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setCursorName"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void setEscapeProcessing(boolean param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setEscapeProcessing"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void setMaxFieldSize(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setMaxFieldSize"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void setQueryTimeout(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setQueryTimeout"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
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
    
    @Override
    public Connection getConnection() throws SQLException {		
    	return this.connection;
    }
}


