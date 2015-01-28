package com.asksunny.jdbc;
import java.sql.Savepoint;
import java.sql.SQLException;


public class SunnySavepoint implements Savepoint, SunnyJdbcDriverConstants {
    private int savepointId = -1;
    private String savepointName = null;


    public  SunnySavepoint(String savepointstr) throws SQLException {
    }

    public int getSavepointId() throws SQLException {
        return this.savepointId;
    }

    public String getSavepointName() throws SQLException {
        return this.savepointName;
    }

}


