package com.asksunny.jdbc;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.RowId;
import java.sql.SQLXML;
import java.sql.Ref;
import java.sql.Struct;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Savepoint;
import java.sql.Array;
import java.util.Properties;
import java.util.Map;


public class SunnyConnection implements Connection, SunnyJdbcDriverConstants {
    private final static HashMap<Integer, String> conn_int2cmdMap = SunnyJDBCVTableMaps.CONN_I2FN_MAP;
    private final static HashMap<String, Integer> conn_cmd2intMap = SunnyJDBCVTableMaps.CONN_FN2I_MAP;
    private Properties clientInfo = new Properties();;
    private String conntionUrl = null;
    public  SunnyConnection(String url) throws SQLException {
        this.conntionUrl=url;
    }

    public void setReadOnly(boolean param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setReadOnly"));
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

    public void close() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("close"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public boolean isValid(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("isValid"));
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

    public boolean isReadOnly() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("isReadOnly"));
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

    public void abort(Executor param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("abort"));
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

    public java.sql.Array createArrayOf(String param0,Object[] param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("createArrayOf([class java.lang.String, class [Ljava.lang.Object;])");
    }

    public void setClientInfo(Properties param0) throws java.sql.SQLClientInfoException  {
        this.clientInfo.putAll(param0);
    }

    public void setClientInfo(String param0,String param1) throws java.sql.SQLClientInfoException  {
        this.clientInfo.setProperty(param0, param1);
    }

    public java.sql.Blob createBlob() throws SQLException {
        throw new SQLFeatureNotSupportedException("createBlob([])");
    }

    public java.sql.Clob createClob() throws SQLException {
        throw new SQLFeatureNotSupportedException("createClob([])");
    }

    public java.sql.NClob createNClob() throws SQLException {
        throw new SQLFeatureNotSupportedException("createNClob([])");
    }

    public java.sql.SQLXML createSQLXML() throws SQLException {
        throw new SQLFeatureNotSupportedException("createSQLXML([])");
    }

    public java.sql.Struct createStruct(String param0,Object[] param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("createStruct([class java.lang.String, class [Ljava.lang.Object;])");
    }

    public SQLWarning getWarnings() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getWarnings"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SQLWarning(response.substring(1));        }
    }

    public void clearWarnings() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("clearWarnings"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public Statement createStatement(int param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("createStatement"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyStatement(this);
        }
    }

    public Statement createStatement(int param0,int param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("createStatement"));
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
            return new SunnyStatement(this);
        }
    }

    public Statement createStatement() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("createStatement"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyStatement(this);
        }
    }

    public boolean getAutoCommit() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getAutoCommit"));
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

    public Properties getClientInfo() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getClientInfo"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return this.clientInfo;
        }
    }

    public String getClientInfo(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getClientInfo"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal;
        }
    }

    public int getHoldability() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getHoldability"));
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

    public int getNetworkTimeout() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getNetworkTimeout"));
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

    public int getTransactionIsolation() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getTransactionIsolation"));
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

    public PreparedStatement prepareStatement(String param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("prepareStatement"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyPreparedStatement(this);
        }
    }

    public PreparedStatement prepareStatement(String param0,int[] param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("prepareStatement"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyPreparedStatement(this);
        }
    }

    public PreparedStatement prepareStatement(String param0,int param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("prepareStatement"));
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
            return new SunnyPreparedStatement(this);
        }
    }

    public PreparedStatement prepareStatement(String param0,int param1,int param2,int param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("prepareStatement"));
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
            return new SunnyPreparedStatement(this);
        }
    }

    public PreparedStatement prepareStatement(String param0,String[] param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("prepareStatement"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyPreparedStatement(this);
        }
    }

    public PreparedStatement prepareStatement(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("prepareStatement"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyPreparedStatement(this);
        }
    }

    public void releaseSavepoint(Savepoint param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("releaseSavepoint"));
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

    public void setAutoCommit(boolean param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setAutoCommit"));
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

    public void setHoldability(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setHoldability"));
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

    public void setNetworkTimeout(Executor param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setNetworkTimeout"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void setTransactionIsolation(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setTransactionIsolation"));
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

    public DatabaseMetaData getMetaData() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getMetaData"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyDatabaseMetaData(this, response.substring(1));
        }
    }

    public boolean isClosed() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("isClosed"));
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

    public void commit() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("commit"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public String getCatalog() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getCatalog"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal;
        }
    }

    public String getSchema() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getSchema"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal;
        }
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("getTypeMap"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return SunnyMetaDataUtil.convert2TypeMap(response.substring(1));
        }
    }

    public String nativeSQL(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("nativeSQL"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String respVal = response.substring(1);
            return respVal;
        }
    }

    public CallableStatement prepareCall(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("prepareCall"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyCallableStatement(this);
        }
    }

    public CallableStatement prepareCall(String param0,int param1,int param2,int param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("prepareCall"));
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
            return new SunnyCallableStatement(this);
        }
    }

    public CallableStatement prepareCall(String param0,int param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("prepareCall"));
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
            return new SunnyCallableStatement(this);
        }
    }

    public void rollback(Savepoint param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("rollback"));
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

    public void rollback() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("rollback"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void setCatalog(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setCatalog"));
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

    public Savepoint setSavepoint(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setSavepoint"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnySavepoint(response.substring(1));
        }
    }

    public Savepoint setSavepoint() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setSavepoint"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnySavepoint(response.substring(1));
        }
    }

    public void setSchema(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setSchema"));
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

    public void setTypeMap(Map param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(conn_cmd2intMap.get("setTypeMap"));
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
    
}


