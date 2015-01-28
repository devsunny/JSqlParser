package com.asksunny.jdbc;
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


public class SunnyResultSet implements ResultSet, SunnyJdbcDriverConstants {
    private final static HashMap<Integer, String> rs_int2cmdMap = SunnyJDBCVTableMaps.RS_I2FN_MAP;
    private final static HashMap<String, Integer> rs_cmd2intMap = SunnyJDBCVTableMaps.RS_FN2I_MAP;


    private SunnyConnection connection=null;
    private Statement statement=null;
    private SunnyResultSetMetaData rsMetaData=null;
    private String metaString=null;
    private String[] rawrow=null;
    private boolean wasNullValue = false;
    private HashMap<String, Integer> col_idx_map = new HashMap<String, Integer>();


    public  SunnyResultSet(SunnyConnection conn, Statement stmt, String metadata) throws SQLException {
        this.connection=conn;
        this.statement=stmt;
        this.metaString=metadata;
        this.rsMetaData = new SunnyResultSetMetaData(this.metaString);
        int colcount = this.rsMetaData.getColumnCount();
        for (int i=0; i<colcount; i++) {
        	String colname = this.rsMetaData.getColumnName(i+1);
        	col_idx_map.put(colname.toUpperCase(), (i+1));
        }
    }

    public  SunnyResultSet(SunnyConnection conn, String metadata) throws SQLException {
        this(conn, null, metadata);
    }

    public Object getObject(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            String nclazzname = this.rsMetaData.getColumnClassName(colidx);
            return castObject(rawcol, nclazzname);
        }
    }

    public Object getObject(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            String nclazzname = this.rsMetaData.getColumnClassName(colidx);
            return castObject(rawcol, nclazzname);
        }
    }

    public Object getObject(String param0, Map<String, Class<?>> map) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
         String sqlTypeName = this.rsMetaData.getColumnTypeName(colidx);
         Class<?> cls = map.get(sqlTypeName);
        return castObject(rawcol, cls);
    }

    public <T> T getObject(String param0, Class<T> type) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        String clazzName = type.getName();
        Object obj =  castObject(rawcol, clazzName);
        return type.cast(obj);
    }

    public <T> T getObject(int param0, Class<T> type) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        String clazzName = type.getName();
        Object obj =  castObject(rawcol, clazzName);
        return type.cast(obj);
    }

    public Object getObject(int param0, Map<String, Class<?>> map) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
         String sqlTypeName = this.rsMetaData.getColumnTypeName(colidx);
         Class<?> cls = map.get(sqlTypeName);
        return castObject(rawcol, cls);
    }

    public boolean getBoolean(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return false;
        }else{ 
            return Boolean.valueOf(rawcol);
        }
    }

    public boolean getBoolean(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return false;
        }else{ 
            return Boolean.valueOf(rawcol);
        }
    }

    public byte getByte(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return ((byte)Integer.valueOf(rawcol).intValue());
        }
    }

    public byte getByte(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return ((byte)Integer.valueOf(rawcol).intValue());
        }
    }

    public short getShort(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Short.valueOf(rawcol);
        }
    }

    public short getShort(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Short.valueOf(rawcol);
        }
    }

    public int getInt(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Integer.valueOf(rawcol);
        }
    }

    public int getInt(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Integer.valueOf(rawcol);
        }
    }

    public long getLong(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Long.valueOf(rawcol);
        }
    }

    public long getLong(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Long.valueOf(rawcol);
        }
    }

    public float getFloat(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Float.valueOf(rawcol);
        }
    }

    public float getFloat(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Float.valueOf(rawcol);
        }
    }

    public double getDouble(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Double.valueOf(rawcol);
        }
    }

    public double getDouble(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return 0;
        }else{ 
            return Double.valueOf(rawcol);
        }
    }

    public Array getArray(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getArray([class java.lang.String])");
    }

    public Array getArray(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getArray([int])");
    }

    public boolean next() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("next"));
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

    public URL getURL(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            URL retUrl = null;
            try{ retUrl = new URL(rawcol); }catch(Exception ex){ throw new SQLException("Invalid URL", ex);};
            return retUrl;
        }
    }

    public URL getURL(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            URL retUrl = null;
            try{ retUrl = new URL(rawcol); }catch(Exception ex){ throw new SQLException("Invalid URL", ex);};
            return retUrl;
        }
    }

    public void close() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("close"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public int getType() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("getType"));
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

    public boolean previous() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("previous"));
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

    public byte[] getBytes(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return Base64.decodeBase64(rawcol);
        }
    }

    public byte[] getBytes(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return Base64.decodeBase64(rawcol);
        }
    }

    public String getString(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return rawcol;
        }
    }

    public String getString(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return rawcol;
        }
    }

    public java.sql.Ref getRef(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getRef([class java.lang.String])");
    }

    public java.sql.Ref getRef(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getRef([int])");
    }

    public void moveToCurrentRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("moveToCurrentRow([])");
    }

    public void moveToInsertRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("moveToInsertRow([])");
    }

    public void cancelRowUpdates() throws SQLException {
        throw new SQLFeatureNotSupportedException("cancelRowUpdates([])");
    }

    public BigDecimal getBigDecimal(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return BigDecimal.valueOf(Long.valueOf(rawcol).longValue());
        }
    }

    public BigDecimal getBigDecimal(int param0,int param1) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return BigDecimal.valueOf(Long.valueOf(rawcol).longValue());
        }
    }

    public BigDecimal getBigDecimal(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return BigDecimal.valueOf(Long.valueOf(rawcol).longValue());
        }
    }

    public BigDecimal getBigDecimal(String param0,int param1) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return BigDecimal.valueOf(Long.valueOf(rawcol).longValue());
        }
    }

    public Date getDate(int param0,Calendar param1) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            Calendar cal = Calendar.getInstance();
             cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
             if(param1!=null)cal.setTimeZone(param1.getTimeZone());
            return new Date(cal.getTimeInMillis());
        }
    }

    public Date getDate(String param0,Calendar param1) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            Calendar cal = Calendar.getInstance();
             cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
             if(param1!=null)cal.setTimeZone(param1.getTimeZone());
            return new Date(cal.getTimeInMillis());
        }
    }

    public Date getDate(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new Date(Long.valueOf(rawcol).longValue());
        }
    }

    public Date getDate(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new Date(Long.valueOf(rawcol).longValue());
        }
    }

    public boolean rowUpdated() throws SQLException {
        throw new SQLFeatureNotSupportedException("rowUpdated([])");
    }

    public boolean rowDeleted() throws SQLException {
        throw new SQLFeatureNotSupportedException("rowDeleted([])");
    }

    public boolean rowInserted() throws SQLException {
        throw new SQLFeatureNotSupportedException("rowInserted([])");
    }

    public void refreshRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("refreshRow([])");
    }

    public java.sql.Blob getBlob(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getBlob([int])");
    }

    public java.sql.Blob getBlob(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getBlob([class java.lang.String])");
    }

    public java.sql.Clob getClob(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getClob([int])");
    }

    public java.sql.Clob getClob(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("getClob([class java.lang.String])");
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

    public SQLWarning getWarnings() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("getWarnings"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            return new SQLWarning(response.substring(1));        }
    }

    public boolean first() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("first"));
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

    public boolean last() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("last"));
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

    public void clearWarnings() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("clearWarnings"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public InputStream getAsciiStream(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new ByteArrayInputStream(Base64.decodeBase64(rawcol));
        }
    }

    public InputStream getAsciiStream(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new ByteArrayInputStream(Base64.decodeBase64(rawcol));
        }
    }

    public InputStream getBinaryStream(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new ByteArrayInputStream(Base64.decodeBase64(rawcol));
        }
    }

    public InputStream getBinaryStream(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new ByteArrayInputStream(Base64.decodeBase64(rawcol));
        }
    }

    public Reader getCharacterStream(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new StringReader(rawcol);
        }
    }

    public Reader getCharacterStream(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new StringReader(rawcol);
        }
    }

    public int getConcurrency() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("getConcurrency"));
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

    public String getCursorName() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("getCursorName"));
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

    public int getFetchDirection() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("getFetchDirection"));
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

    public int getHoldability() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("getHoldability"));
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

    public Reader getNCharacterStream(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new StringReader(rawcol);
        }
    }

    public Reader getNCharacterStream(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new StringReader(rawcol);
        }
    }

    public InputStream getUnicodeStream(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new ByteArrayInputStream(Base64.decodeBase64(rawcol));
        }
    }

    public InputStream getUnicodeStream(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new ByteArrayInputStream(Base64.decodeBase64(rawcol));
        }
    }

    public boolean isBeforeFirst() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("isBeforeFirst"));
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

    public void setFetchDirection(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("setFetchDirection"));
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

    public void updateAsciiStream(String param0,InputStream param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateAsciiStream([class java.lang.String, class java.io.InputStream, long])");
    }

    public void updateAsciiStream(String param0,InputStream param1,int param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateAsciiStream([class java.lang.String, class java.io.InputStream, int])");
    }

    public void updateAsciiStream(int param0,InputStream param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateAsciiStream([int, class java.io.InputStream])");
    }

    public void updateAsciiStream(String param0,InputStream param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateAsciiStream([class java.lang.String, class java.io.InputStream])");
    }

    public void updateAsciiStream(int param0,InputStream param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateAsciiStream([int, class java.io.InputStream, long])");
    }

    public void updateAsciiStream(int param0,InputStream param1,int param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateAsciiStream([int, class java.io.InputStream, int])");
    }

    public void updateBigDecimal(String param0,BigDecimal param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBigDecimal([class java.lang.String, class java.math.BigDecimal])");
    }

    public void updateBigDecimal(int param0,BigDecimal param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBigDecimal([int, class java.math.BigDecimal])");
    }

    public void updateBinaryStream(int param0,InputStream param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBinaryStream([int, class java.io.InputStream, long])");
    }

    public void updateBinaryStream(String param0,InputStream param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBinaryStream([class java.lang.String, class java.io.InputStream])");
    }

    public void updateBinaryStream(String param0,InputStream param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBinaryStream([class java.lang.String, class java.io.InputStream, long])");
    }

    public void updateBinaryStream(int param0,InputStream param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBinaryStream([int, class java.io.InputStream])");
    }

    public void updateBinaryStream(String param0,InputStream param1,int param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBinaryStream([class java.lang.String, class java.io.InputStream, int])");
    }

    public void updateBinaryStream(int param0,InputStream param1,int param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBinaryStream([int, class java.io.InputStream, int])");
    }

    public void updateBoolean(int param0,boolean param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBoolean([int, boolean])");
    }

    public void updateBoolean(String param0,boolean param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBoolean([class java.lang.String, boolean])");
    }

    public void updateCharacterStream(int param0,Reader param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateCharacterStream([int, class java.io.Reader])");
    }

    public void updateCharacterStream(int param0,Reader param1,int param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateCharacterStream([int, class java.io.Reader, int])");
    }

    public void updateCharacterStream(String param0,Reader param1,int param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateCharacterStream([class java.lang.String, class java.io.Reader, int])");
    }

    public void updateCharacterStream(String param0,Reader param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateCharacterStream([class java.lang.String, class java.io.Reader, long])");
    }

    public void updateCharacterStream(int param0,Reader param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateCharacterStream([int, class java.io.Reader, long])");
    }

    public void updateCharacterStream(String param0,Reader param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateCharacterStream([class java.lang.String, class java.io.Reader])");
    }

    public void updateNCharacterStream(String param0,Reader param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNCharacterStream([class java.lang.String, class java.io.Reader, long])");
    }

    public void updateNCharacterStream(int param0,Reader param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNCharacterStream([int, class java.io.Reader, long])");
    }

    public void updateNCharacterStream(String param0,Reader param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNCharacterStream([class java.lang.String, class java.io.Reader])");
    }

    public void updateNCharacterStream(int param0,Reader param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNCharacterStream([int, class java.io.Reader])");
    }

    public void updateNString(String param0,String param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNString([class java.lang.String, class java.lang.String])");
    }

    public void updateNString(int param0,String param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNString([int, class java.lang.String])");
    }

    public void updateTimestamp(int param0,Timestamp param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateTimestamp([int, class java.sql.Timestamp])");
    }

    public void updateTimestamp(String param0,Timestamp param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateTimestamp([class java.lang.String, class java.sql.Timestamp])");
    }

    public boolean absolute(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("absolute"));
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

    public void afterLast() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("afterLast"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void beforeFirst() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("beforeFirst"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public void deleteRow() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("deleteRow"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public int findColumn(String param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("findColumn"));
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

    public int getFetchSize() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("getFetchSize"));
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

    public String getNString(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return rawcol;
        }
    }

    public String getNString(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return rawcol;
        }
    }

    public int getRow() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("getRow"));
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

    public Time getTime(String param0,Calendar param1) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            Calendar cal = Calendar.getInstance();
             cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
             if(param1!=null)cal.setTimeZone(param1.getTimeZone());
            return new Time(cal.getTimeInMillis());
        }
    }

    public Time getTime(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new Time(Long.valueOf(rawcol).longValue());
        }
    }

    public Time getTime(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new Time(Long.valueOf(rawcol).longValue());
        }
    }

    public Time getTime(int param0,Calendar param1) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            Calendar cal = Calendar.getInstance();
             cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
             if(param1!=null)cal.setTimeZone(param1.getTimeZone());
            return new Time(cal.getTimeInMillis());
        }
    }

    public Timestamp getTimestamp(int param0,Calendar param1) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            Calendar cal = Calendar.getInstance();
             cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
             if(param1!=null)cal.setTimeZone(param1.getTimeZone());
            return new Timestamp(cal.getTimeInMillis());
        }
    }

    public Timestamp getTimestamp(int param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = param0;
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new Timestamp(Long.valueOf(rawcol).longValue());
        }
    }

    public Timestamp getTimestamp(String param0,Calendar param1) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            Calendar cal = Calendar.getInstance();
             cal.setTimeInMillis(Long.valueOf(rawcol).longValue());
             if(param1!=null)cal.setTimeZone(param1.getTimeZone());
            return new Timestamp(cal.getTimeInMillis());
        }
    }

    public Timestamp getTimestamp(String param0) throws SQLException {
        this.wasNullValue = false;
        int colidx = col_idx_map.get(param0.toUpperCase());
        String rawcol = rawrow[colidx-1];
        if( rawcol==null || rawcol.length()==0){ 
            wasNullValue = true; 
            return null;
        }else{ 
            return new Timestamp(Long.valueOf(rawcol).longValue());
        }
    }

    public void insertRow() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("insertRow"));
        cmdBuf.append(FIELD_DELIMITER);
        String response = sendRemoteRequest(cmdBuf.toString());
        char c = response.charAt(0);
        if (c==SQL_ERROR){
            throw new SQLException(response.substring(1));
        }else{
            ;//No op need.
        }
    }

    public boolean isAfterLast() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("isAfterLast"));
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

    public boolean isClosed() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("isClosed"));
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

    public boolean isFirst() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("isFirst"));
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

    public boolean isLast() throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("isLast"));
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

    public boolean relative(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("relative"));
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

    public void setFetchSize(int param0) throws SQLException {
        StringBuilder cmdBuf = new StringBuilder();
        cmdBuf.append(DBMETA_CMD);
        cmdBuf.append(FIELD_DELIMITER);
        cmdBuf.append(rs_cmd2intMap.get("setFetchSize"));
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

    public void updateArray(String param0,Array param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateArray([class java.lang.String, interface java.sql.Array])");
    }

    public void updateArray(int param0,Array param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateArray([int, interface java.sql.Array])");
    }

    public void updateBlob(int param0,InputStream param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBlob([int, class java.io.InputStream])");
    }

    public void updateBlob(int param0,Blob param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBlob([int, interface java.sql.Blob])");
    }

    public void updateBlob(String param0,InputStream param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBlob([class java.lang.String, class java.io.InputStream, long])");
    }

    public void updateBlob(int param0,InputStream param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBlob([int, class java.io.InputStream, long])");
    }

    public void updateBlob(String param0,InputStream param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBlob([class java.lang.String, class java.io.InputStream])");
    }

    public void updateBlob(String param0,Blob param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBlob([class java.lang.String, interface java.sql.Blob])");
    }

    public void updateByte(String param0,byte param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateByte([class java.lang.String, byte])");
    }

    public void updateByte(int param0,byte param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateByte([int, byte])");
    }

    public void updateBytes(String param0,byte[] param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBytes([class java.lang.String, class [B])");
    }

    public void updateBytes(int param0,byte[] param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateBytes([int, class [B])");
    }

    public void updateClob(int param0,Clob param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateClob([int, interface java.sql.Clob])");
    }

    public void updateClob(String param0,Reader param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateClob([class java.lang.String, class java.io.Reader])");
    }

    public void updateClob(int param0,Reader param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateClob([int, class java.io.Reader, long])");
    }

    public void updateClob(String param0,Reader param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateClob([class java.lang.String, class java.io.Reader, long])");
    }

    public void updateClob(String param0,Clob param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateClob([class java.lang.String, interface java.sql.Clob])");
    }

    public void updateClob(int param0,Reader param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateClob([int, class java.io.Reader])");
    }

    public void updateDate(String param0,Date param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateDate([class java.lang.String, class java.sql.Date])");
    }

    public void updateDate(int param0,Date param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateDate([int, class java.sql.Date])");
    }

    public void updateDouble(int param0,double param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateDouble([int, double])");
    }

    public void updateDouble(String param0,double param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateDouble([class java.lang.String, double])");
    }

    public void updateFloat(String param0,float param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateFloat([class java.lang.String, float])");
    }

    public void updateFloat(int param0,float param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateFloat([int, float])");
    }

    public void updateInt(int param0,int param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateInt([int, int])");
    }

    public void updateInt(String param0,int param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateInt([class java.lang.String, int])");
    }

    public void updateLong(int param0,long param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateLong([int, long])");
    }

    public void updateLong(String param0,long param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateLong([class java.lang.String, long])");
    }

    public void updateNClob(int param0,Reader param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNClob([int, class java.io.Reader, long])");
    }

    public void updateNClob(String param0,Reader param1,long param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNClob([class java.lang.String, class java.io.Reader, long])");
    }

    public void updateNClob(int param0,Reader param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNClob([int, class java.io.Reader])");
    }

    public void updateNClob(int param0,NClob param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNClob([int, interface java.sql.NClob])");
    }

    public void updateNClob(String param0,NClob param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNClob([class java.lang.String, interface java.sql.NClob])");
    }

    public void updateNClob(String param0,Reader param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNClob([class java.lang.String, class java.io.Reader])");
    }

    public void updateNull(int param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNull([int])");
    }

    public void updateNull(String param0) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateNull([class java.lang.String])");
    }

    public void updateObject(int param0,Object param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateObject([int, class java.lang.Object])");
    }

    public void updateObject(String param0,Object param1,int param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateObject([class java.lang.String, class java.lang.Object, int])");
    }

    public void updateObject(String param0,Object param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateObject([class java.lang.String, class java.lang.Object])");
    }

    public void updateObject(int param0,Object param1,int param2) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateObject([int, class java.lang.Object, int])");
    }

    public void updateRef(int param0,Ref param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateRef([int, interface java.sql.Ref])");
    }

    public void updateRef(String param0,Ref param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateRef([class java.lang.String, interface java.sql.Ref])");
    }

    public void updateRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("updateRow([])");
    }

    public void updateRowId(String param0,RowId param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateRowId([class java.lang.String, interface java.sql.RowId])");
    }

    public void updateRowId(int param0,RowId param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateRowId([int, interface java.sql.RowId])");
    }

    public void updateSQLXML(String param0,SQLXML param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateSQLXML([class java.lang.String, interface java.sql.SQLXML])");
    }

    public void updateSQLXML(int param0,SQLXML param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateSQLXML([int, interface java.sql.SQLXML])");
    }

    public void updateShort(String param0,short param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateShort([class java.lang.String, short])");
    }

    public void updateShort(int param0,short param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateShort([int, short])");
    }

    public void updateString(String param0,String param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateString([class java.lang.String, class java.lang.String])");
    }

    public void updateString(int param0,String param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateString([int, class java.lang.String])");
    }

    public void updateTime(int param0,Time param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateTime([int, class java.sql.Time])");
    }

    public void updateTime(String param0,Time param1) throws SQLException {
        throw new SQLFeatureNotSupportedException("updateTime([class java.lang.String, class java.sql.Time])");
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
    public ResultSetMetaData getMetaData() throws SQLException {		
    	return rsMetaData;
    }
    
    @Override
    public Statement getStatement() throws SQLException {		
    	return this.statement;
    }
    @Override
    public boolean wasNull() throws SQLException {
    	return this.wasNullValue;
    }

    public Object castObject(String rawcol, String clazzName) throws SQLException
    {
    	if(clazzName.equals(String.class.getName())){
    		return rawcol;
    	}else if(clazzName.equals(Integer.class.getName())){
    		return Integer.valueOf(rawcol);
    	}else if(clazzName.equals(Long.class.getName())){
    		return Integer.valueOf(rawcol);
    	}else if(clazzName.equals(Double.class.getName())){
    		return Integer.valueOf(rawcol);
    	}else if(clazzName.equals(BigDecimal.class.getName())){
    		return BigDecimal.valueOf(Double.valueOf(rawcol));
    	}else if(clazzName.equals(Date.class.getName())){
    		return new Date(Long.valueOf(rawcol));
    	}else if(clazzName.equals(java.util.Date.class.getName())){
    		java.util.Date d = new java.util.Date();
    		d.setTime(Long.valueOf(rawcol));
    		return d;
    	}else if(clazzName.equals(Time.class.getName())){
    		return new Time(Long.valueOf(rawcol));
    	}else if(clazzName.equals(java.sql.Timestamp.class.getName())){
    		return new java.sql.Timestamp(Long.valueOf(rawcol));
    	}else if(clazzName.equals(Calendar.class.getName())){
    		Calendar cal = Calendar.getInstance();
    		cal.setTimeInMillis(Long.valueOf(rawcol));
    		return cal;
    	}else if(clazzName.equals(Byte.class.getName())){
    		return Byte.valueOf(((byte)Integer.valueOf(rawcol).intValue()));
    	}else if(clazzName.equals(Float.class.getName())){
    		return Float.valueOf(rawcol);
    	}else if(clazzName.equals(Short.class.getName())){
    		return Short.valueOf(rawcol);
    	}else{
    		throw new SQLException("Failed to cast value to " + clazzName);
    	}		
    }

     public <T> T castObject(String rawcol, Class<T> type) throws SQLException
    { 
    	if(type==null) throw new SQLException("Java type must be specified");
    	String clazzName = type.getName();
    	Object obj =  castObject(rawcol, clazzName);
    	return type.cast(obj);
    }
}


