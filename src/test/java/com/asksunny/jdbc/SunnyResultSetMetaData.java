package com.asksunny.jdbc;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class SunnyResultSetMetaData implements ResultSetMetaData, SunnyJdbcDriverConstants {
    private List<HashMap<String, String>> rsMetaDatas = null;


    public  SunnyResultSetMetaData(String metadata) throws SQLException {
        this.rsMetaDatas=SunnyMetaDataUtil.convert2ResultSetMetaData(metadata);
    }

    public boolean isReadOnly(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isReadOnly");
        return Boolean.valueOf(val);
    }

    public boolean isCurrency(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isCurrency");
        return Boolean.valueOf(val);
    }

    public int isNullable(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isNullable");
        return Integer.valueOf(val);
    }

    public boolean isSearchable(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isSearchable");
        return Boolean.valueOf(val);
    }

    public boolean isSigned(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isSigned");
        return Boolean.valueOf(val);
    }

    public boolean isWritable(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isWritable");
        return Boolean.valueOf(val);
    }

    public int getPrecision(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getPrecision");
        return Integer.valueOf(val);
    }

    public int getScale(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getScale");
        return Integer.valueOf(val);
    }

    public String getTableName(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getTableName");
        return val;
    }

    public String getCatalogName(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getCatalogName");
        return val;
    }

    public String getColumnClassName(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getColumnClassName");
        return val;
    }

    public int getColumnCount() throws SQLException {
        return rsMetaDatas.size();
    }

    public int getColumnDisplaySize(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getColumnDisplaySize");
        return Integer.valueOf(val);
    }

    public String getColumnLabel(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getColumnLabel");
        return val;
    }

    public String getColumnName(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getColumnName");
        return val;
    }

    public int getColumnType(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getColumnType");
        return Integer.valueOf(val);
    }

    public String getColumnTypeName(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getColumnTypeName");
        return val;
    }

    public String getSchemaName(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getSchemaName");
        return val;
    }

    public boolean isAutoIncrement(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isAutoIncrement");
        return Boolean.valueOf(val);
    }

    public boolean isCaseSensitive(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isCaseSensitive");
        return Boolean.valueOf(val);
    }

    public boolean isDefinitelyWritable(int colidx) throws SQLException {
        if(colidx>rsMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Column index out of bound. Max=" + rsMetaDatas.size());
        HashMap<String, String> rsMetaData = rsMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isDefinitelyWritable");
        return Boolean.valueOf(val);
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


