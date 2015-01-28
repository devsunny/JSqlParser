package com.asksunny.jdbc;
import java.sql.ResultSet;
import java.sql.CallableStatement;
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
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.sql.ParameterMetaData;


public class SunnyCallableStatement implements CallableStatement, SunnyJdbcDriverConstants {
    private final static HashMap<Integer, String> stmt_int2cmdMap = SunnyJDBCVTableMaps.STMT_I2FN_MAP;
    private final static HashMap<String, Integer> stmt_cmd2intMap = SunnyJDBCVTableMaps.STMT_FN2I_MAP;


    private SunnyConnection connection=null;


    public  SunnyCallableStatement(SunnyConnection conn) throws SQLException {
        this.connection=conn;
    }

    public Object getObject(String param0,Map param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getObject"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return null;
            }
        }
    }

    public Object getObject(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getObject"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return null;
            }
        }
    }

    public Object getObject(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getObject"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return null;
            }
        }
    }

    public Object getObject(int param0,Map param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getObject"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return null;
            }
        }
    }

    public Object getObject(int param0,Class param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getObject"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return null;
            }
        }
    }

    public Object getObject(String param0,Class param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getObject"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return null;
            }
        }
    }

    public boolean getBoolean(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getBoolean"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
        }
    }

    public boolean getBoolean(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getBoolean"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
        }
    }

    public byte getByte(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getByte"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return ((byte)Integer.valueOf(rawcol).intValue());
            }
        }
    }

    public byte getByte(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getByte"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return ((byte)Integer.valueOf(rawcol).intValue());
            }
        }
    }

    public short getShort(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getShort"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Short.valueOf(rawcol);
            }
        }
    }

    public short getShort(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getShort"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Short.valueOf(rawcol);
            }
        }
    }

    public int getInt(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getInt"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
        }
    }

    public int getInt(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getInt"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
        }
    }

    public long getLong(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getLong"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Long.valueOf(rawcol);
            }
        }
    }

    public long getLong(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getLong"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Long.valueOf(rawcol);
            }
        }
    }

    public float getFloat(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getFloat"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Float.valueOf(rawcol);
            }
        }
    }

    public float getFloat(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getFloat"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Float.valueOf(rawcol);
            }
        }
    }

    public double getDouble(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getDouble"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Double.valueOf(rawcol);
            }
        }
    }

    public double getDouble(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getDouble"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Double.valueOf(rawcol);
            }
        }
    }

    public java.sql.Array getArray(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getArray([int])");
    }

    public java.sql.Array getArray(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getArray([class java.lang.String])");
    }

    public URL getURL(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getURL"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                URL retUrl = null;
                try{ retUrl = new URL(rawcol); }catch(Exception ex){ throw new SQLException("Invalid URL", ex);};
                return retUrl;
            }
        }
    }

    public URL getURL(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getURL"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                URL retUrl = null;
                try{ retUrl = new URL(rawcol); }catch(Exception ex){ throw new SQLException("Invalid URL", ex);};
                return retUrl;
            }
        }
    }

    public void setBoolean(String param0,boolean param1) throws SQLException {
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

    public void setByte(String param0,byte param1) throws SQLException {
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

    public void setDouble(String param0,double param1) throws SQLException {
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

    public void setFloat(String param0,float param1) throws SQLException {
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

    public void setInt(String param0,int param1) throws SQLException {
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

    public void setLong(String param0,long param1) throws SQLException {
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

    public void setShort(String param0,short param1) throws SQLException {
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

    public byte[] getBytes(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getBytes"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return Base64.decodeBase64(rawcol);
            }
        }
    }

    public byte[] getBytes(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getBytes"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return Base64.decodeBase64(rawcol);
            }
        }
    }

    public void setTimestamp(String param0,Timestamp param1,Calendar param2) throws SQLException {
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

    public void setTimestamp(String param0,Timestamp param1) throws SQLException {
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

    public String getString(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getString"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return rawcol;
            }
        }
    }

    public String getString(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getString"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return rawcol;
            }
        }
    }

    public java.sql.Ref getRef(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getRef([class java.lang.String])");
    }

    public java.sql.Ref getRef(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getRef([int])");
    }

    public void setURL(String param0,URL param1) throws SQLException {
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

    public Date getDate(String param0,Calendar param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getDate"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                Calendar cal = Calendar.getInstance();
                 cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
                 if(param1!=null)cal.setTimeZone(param1.getTimeZone());
                return new Date(cal.getTimeInMillis());
            }
        }
    }

    public Date getDate(int param0,Calendar param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getDate"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                Calendar cal = Calendar.getInstance();
                 cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
                 if(param1!=null)cal.setTimeZone(param1.getTimeZone());
                return new Date(cal.getTimeInMillis());
            }
        }
    }

    public Date getDate(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getDate"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new Date(Long.valueOf(rawcol).longValue());
            }
        }
    }

    public Date getDate(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getDate"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new Date(Long.valueOf(rawcol).longValue());
            }
        }
    }

    public java.sql.Blob getBlob(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getBlob([int])");
    }

    public java.sql.Blob getBlob(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getBlob([class java.lang.String])");
    }

    public java.sql.Clob getClob(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getClob([class java.lang.String])");
    }

    public java.sql.Clob getClob(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getClob([int])");
    }

    public java.sql.NClob getNClob(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getNClob([class java.lang.String])");
    }

    public java.sql.NClob getNClob(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getNClob([int])");
    }

    public java.sql.RowId getRowId(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getRowId([class java.lang.String])");
    }

    public java.sql.RowId getRowId(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getRowId([int])");
    }

    public java.sql.SQLXML getSQLXML(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getSQLXML([int])");
    }

    public java.sql.SQLXML getSQLXML(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getSQLXML([class java.lang.String])");
    }

    public void setTime(String param0,Time param1) throws SQLException {
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

    public void setTime(String param0,Time param1,Calendar param2) throws SQLException {
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

    public BigDecimal getBigDecimal(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getBigDecimal"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return BigDecimal.valueOf(Long.valueOf(rawcol).longValue());
            }
        }
    }

    public BigDecimal getBigDecimal(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getBigDecimal"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return BigDecimal.valueOf(Long.valueOf(rawcol).longValue());
            }
        }
    }

    public BigDecimal getBigDecimal(int param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getBigDecimal"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return BigDecimal.valueOf(Long.valueOf(rawcol).longValue());
            }
        }
    }

    public Reader getCharacterStream(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getCharacterStream"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new StringReader(rawcol);
            }
        }
    }

    public Reader getCharacterStream(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getCharacterStream"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new StringReader(rawcol);
            }
        }
    }

    public Reader getNCharacterStream(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getNCharacterStream"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new StringReader(rawcol);
            }
        }
    }

    public Reader getNCharacterStream(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getNCharacterStream"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new StringReader(rawcol);
            }
        }
    }

    public void registerOutParameter(int param0,int param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("registerOutParameter"));
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

    public void registerOutParameter(int param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("registerOutParameter"));
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

    public void registerOutParameter(int param0,int param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("registerOutParameter"));
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

    public void registerOutParameter(String param0,int param1,int param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("registerOutParameter"));
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

    public void registerOutParameter(String param0,int param1,String param2) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("registerOutParameter"));
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

    public void registerOutParameter(String param0,int param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("registerOutParameter"));
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

    public void setAsciiStream(String param0,InputStream param1) throws SQLException {
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

    public void setAsciiStream(String param0,InputStream param1,long param2) throws SQLException {
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

    public void setAsciiStream(String param0,InputStream param1,int param2) throws SQLException {
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

    public void setBigDecimal(String param0,BigDecimal param1) throws SQLException {
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

    public void setBinaryStream(String param0,InputStream param1) throws SQLException {
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

    public void setBinaryStream(String param0,InputStream param1,int param2) throws SQLException {
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

    public void setBinaryStream(String param0,InputStream param1,long param2) throws SQLException {
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

    public void setCharacterStream(String param0,Reader param1) throws SQLException {
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

    public void setCharacterStream(String param0,Reader param1,long param2) throws SQLException {
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

    public void setCharacterStream(String param0,Reader param1,int param2) throws SQLException {
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

    public void setNCharacterStream(String param0,Reader param1,long param2) throws SQLException {
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

    public void setNCharacterStream(String param0,Reader param1) throws SQLException {
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

    public String getNString(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getNString"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return rawcol;
            }
        }
    }

    public String getNString(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getNString"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return rawcol;
            }
        }
    }

    public Time getTime(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getTime"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new Time(Long.valueOf(rawcol).longValue());
            }
        }
    }

    public Time getTime(int param0,Calendar param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getTime"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                Calendar cal = Calendar.getInstance();
                 cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
                 if(param1!=null)cal.setTimeZone(param1.getTimeZone());
                return new Time(cal.getTimeInMillis());
            }
        }
    }

    public Time getTime(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getTime"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new Time(Long.valueOf(rawcol).longValue());
            }
        }
    }

    public Time getTime(String param0,Calendar param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getTime"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                Calendar cal = Calendar.getInstance();
                 cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
                 if(param1!=null)cal.setTimeZone(param1.getTimeZone());
                return new Time(cal.getTimeInMillis());
            }
        }
    }

    public Timestamp getTimestamp(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getTimestamp"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new Timestamp(Long.valueOf(rawcol).longValue());
            }
        }
    }

    public Timestamp getTimestamp(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getTimestamp"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                return new Timestamp(Long.valueOf(rawcol).longValue());
            }
        }
    }

    public Timestamp getTimestamp(String param0,Calendar param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getTimestamp"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                Calendar cal = Calendar.getInstance();
                 cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
                 if(param1!=null)cal.setTimeZone(param1.getTimeZone());
                return new Timestamp(cal.getTimeInMillis());
            }
        }
    }

    public Timestamp getTimestamp(int param0,Calendar param1) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("getTimestamp"));
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param0);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(param1);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return null;
            }else{ 
                Calendar cal = Calendar.getInstance();
                 cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
                 if(param1!=null)cal.setTimeZone(param1.getTimeZone());
                return new Timestamp(cal.getTimeInMillis());
            }
        }
    }

    public boolean wasNull() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(stmt_cmd2intMap.get("wasNull"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
        }
    }

    public void setBlob(String param0,InputStream param1,long param2) throws SQLException {
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

    public void setBlob(String param0,Blob param1) throws SQLException {
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

    public void setBlob(String param0,InputStream param1) throws SQLException {
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

    public void setBytes(String param0,byte[] param1) throws SQLException {
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

    public void setClob(String param0,Reader param1,long param2) throws SQLException {
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

    public void setClob(String param0,Reader param1) throws SQLException {
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

    public void setClob(String param0,Clob param1) throws SQLException {
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

    public void setDate(String param0,Date param1,Calendar param2) throws SQLException {
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

    public void setDate(String param0,Date param1) throws SQLException {
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

    public void setNClob(String param0,Reader param1) throws SQLException {
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

    public void setNClob(String param0,NClob param1) throws SQLException {
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

    public void setNClob(String param0,Reader param1,long param2) throws SQLException {
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

    public void setNString(String param0,String param1) throws SQLException {
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

    public void setNull(String param0,int param1) throws SQLException {
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

    public void setNull(String param0,int param1,String param2) throws SQLException {
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

    public void setObject(String param0,Object param1) throws SQLException {
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

    public void setObject(String param0,Object param1,int param2,int param3) throws SQLException {
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

    public void setObject(String param0,Object param1,int param2) throws SQLException {
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

    public void setRowId(String param0,RowId param1) throws SQLException {
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

    public void setSQLXML(String param0,SQLXML param1) throws SQLException {
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

    public void setString(String param0,String param1) throws SQLException {
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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
                String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return 0;
            }else{ 
                return Integer.valueOf(rawcol);
            }
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
            String rawcol = response.substring(1); 
            if( rawcol==null || rawcol.length()==0){ 
                return false;
            }else{ 
                return rawcol.equals("1") || rawcol.equals("true") ;
            }
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

