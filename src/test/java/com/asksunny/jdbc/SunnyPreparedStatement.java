package com.asksunny.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.sql.Array;
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
import java.sql.SQLWarning;
import java.sql.ResultSetMetaData;
import org.apache.commons.codec.binary.Base64;
import java.io.Reader;
import java.io.StringReader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.sql.ParameterMetaData;


public class SunnyPreparedStatement implements PreparedStatement, SunnyJdbcDriverConstants {
    private final static HashMap<Integer, String> stmt_int2cmdMap = SunnyJDBCVTableMaps.STMT_I2FN_MAP;
    private final static HashMap<String, Integer> stmt_cmd2intMap = SunnyJDBCVTableMaps.STMT_FN2I_MAP;


    private SunnyConnection connection=null;


    public  SunnyPreparedStatement(SunnyConnection conn) throws SQLException {
        this.connection=conn;
    }

    public void setBoolean(int param0,boolean param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setBoolean"));
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

    public void setByte(int param0,byte param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setByte"));
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

    public void setDouble(int param0,double param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setDouble"));
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

    public void setFloat(int param0,float param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setFloat"));
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

    public void setInt(int param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setInt"));
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

    public void setLong(int param0,long param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setLong"));
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

    public void setShort(int param0,short param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setShort"));
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

    public void setTimestamp(int param0,Timestamp param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setTimestamp"));
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

    public void setTimestamp(int param0,Timestamp param1,Calendar param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setTimestamp"));
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
            ;//No op need.
        }
    }

    public void setURL(int param0,URL param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setURL"));
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

    public boolean execute() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("execute"));
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

    public void clearParameters() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("clearParameters"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public int executeUpdate() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("executeUpdate"));
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

    public ParameterMetaData getParameterMetaData() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getParameterMetaData"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyParameterMetaData(response.substring(1));        }
    }

    public void setAsciiStream(int param0,InputStream param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setAsciiStream"));
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

    public void setAsciiStream(int param0,InputStream param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setAsciiStream"));
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
            ;//No op need.
        }
    }

    public void setAsciiStream(int param0,InputStream param1,long param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setAsciiStream"));
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
            ;//No op need.
        }
    }

    public void setBigDecimal(int param0,BigDecimal param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setBigDecimal"));
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

    public void setBinaryStream(int param0,InputStream param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setBinaryStream"));
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

    public void setBinaryStream(int param0,InputStream param1,long param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setBinaryStream"));
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
            ;//No op need.
        }
    }

    public void setBinaryStream(int param0,InputStream param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setBinaryStream"));
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
            ;//No op need.
        }
    }

    public void setCharacterStream(int param0,Reader param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setCharacterStream"));
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
            ;//No op need.
        }
    }

    public void setCharacterStream(int param0,Reader param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setCharacterStream"));
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

    public void setCharacterStream(int param0,Reader param1,long param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setCharacterStream"));
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
            ;//No op need.
        }
    }

    public void setNCharacterStream(int param0,Reader param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setNCharacterStream"));
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

    public void setNCharacterStream(int param0,Reader param1,long param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setNCharacterStream"));
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
            ;//No op need.
        }
    }

    public void setUnicodeStream(int param0,InputStream param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setUnicodeStream"));
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
            ;//No op need.
        }
    }

    public void addBatch() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("addBatch"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public ResultSet executeQuery() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("executeQuery"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyResultSet(this.connection, this, response.substring(1));        }
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getMetaData"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SunnyResultSetMetaData(response.substring(1));        }
    }

    public void setArray(int param0,Array param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setArray"));
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

    public void setBlob(int param0,InputStream param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setBlob"));
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

    public void setBlob(int param0,Blob param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setBlob"));
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

    public void setBlob(int param0,InputStream param1,long param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setBlob"));
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
            ;//No op need.
        }
    }

    public void setBytes(int param0,byte[] param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setBytes"));
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

    public void setClob(int param0,Reader param1,long param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setClob"));
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
            ;//No op need.
        }
    }

    public void setClob(int param0,Reader param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setClob"));
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

    public void setClob(int param0,Clob param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setClob"));
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

    public void setDate(int param0,Date param1,Calendar param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setDate"));
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
            ;//No op need.
        }
    }

    public void setDate(int param0,Date param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setDate"));
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

    public void setNClob(int param0,Reader param1,long param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setNClob"));
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
            ;//No op need.
        }
    }

    public void setNClob(int param0,Reader param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setNClob"));
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

    public void setNClob(int param0,NClob param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setNClob"));
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

    public void setNString(int param0,String param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setNString"));
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

    public void setNull(int param0,int param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setNull"));
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
            ;//No op need.
        }
    }

    public void setNull(int param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setNull"));
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

    public void setObject(int param0,Object param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setObject"));
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
            ;//No op need.
        }
    }

    public void setObject(int param0,Object param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setObject"));
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

    public void setObject(int param0,Object param1,int param2,int param3) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setObject"));
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
            ;//No op need.
        }
    }

    public void setRef(int param0,Ref param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setRef"));
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

    public void setRowId(int param0,RowId param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setRowId"));
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

    public void setSQLXML(int param0,SQLXML param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setSQLXML"));
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

    public void setString(int param0,String param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setString"));
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

    public void setTime(int param0,Time param1,Calendar param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setTime"));
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
            ;//No op need.
        }
    }

    public void setTime(int param0,Time param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("setTime"));
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


