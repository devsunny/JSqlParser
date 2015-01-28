package com.asksunny.jdbc;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class SunnyParameterMetaData implements ParameterMetaData, SunnyJdbcDriverConstants {
    private List<HashMap<String, String>> paramMetaDatas = null;


    public  SunnyParameterMetaData(String metadata) throws SQLException {
        this.paramMetaDatas=SunnyMetaDataUtil.convert2ParameterMetaData(metadata);
    }

    public boolean isSigned(int colidx) throws SQLException {
        if(colidx>paramMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Parameter index out of bound. Max=" + paramMetaDatas.size());
        HashMap<String, String> rsMetaData = paramMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isSigned");
        return Boolean.valueOf(val);
    }

    public int getPrecision(int colidx) throws SQLException {
        if(colidx>paramMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Parameter index out of bound. Max=" + paramMetaDatas.size());
        HashMap<String, String> rsMetaData = paramMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getPrecision");
        return Integer.valueOf(val);
    }

    public int getScale(int colidx) throws SQLException {
        if(colidx>paramMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Parameter index out of bound. Max=" + paramMetaDatas.size());
        HashMap<String, String> rsMetaData = paramMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getScale");
        return Integer.valueOf(val);
    }

    public int isNullable(int colidx) throws SQLException {
        if(colidx>paramMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Parameter index out of bound. Max=" + paramMetaDatas.size());
        HashMap<String, String> rsMetaData = paramMetaDatas.get(colidx-1);
        String val = rsMetaData.get("isNullable");
        return Integer.valueOf(val);
    }

    public String getParameterClassName(int colidx) throws SQLException {
        if(colidx>paramMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Parameter index out of bound. Max=" + paramMetaDatas.size());
        HashMap<String, String> rsMetaData = paramMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getParameterClassName");
        return val;
    }

    public int getParameterCount() throws SQLException {
        return paramMetaDatas.size();
    }

    public int getParameterMode(int colidx) throws SQLException {
        if(colidx>paramMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Parameter index out of bound. Max=" + paramMetaDatas.size());
        HashMap<String, String> rsMetaData = paramMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getParameterMode");
        return Integer.valueOf(val);
    }

    public int getParameterType(int colidx) throws SQLException {
        if(colidx>paramMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Parameter index out of bound. Max=" + paramMetaDatas.size());
        HashMap<String, String> rsMetaData = paramMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getParameterType");
        return Integer.valueOf(val);
    }

    public String getParameterTypeName(int colidx) throws SQLException {
        if(colidx>paramMetaDatas.size() || colidx<1) throw new IndexOutOfBoundsException("Parameter index out of bound. Max=" + paramMetaDatas.size());
        HashMap<String, String> rsMetaData = paramMetaDatas.get(colidx-1);
        String val = rsMetaData.get("getParameterTypeName");
        return val;
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


