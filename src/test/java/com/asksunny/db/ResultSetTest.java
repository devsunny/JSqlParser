package com.asksunny.db;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Properties;

import net.sf.jsqlparser.expression.CastExpression;

import org.junit.Test;

import com.asksunny.jdbc.SunnyJdbcDriverConstants;
import com.asksunny.jdbc.SunnyMetaDataUtil;

public class ResultSetTest implements SunnyJdbcDriverConstants {

	@Test
	public void test() throws Exception {
		Class.forName("org.postgresql.Driver");
		Properties props = new Properties();
		// props.setProperty("user", "fred");
		// props.setProperty("password", "secret");
		props.setProperty("ssl", "true");
		String url = "jdbc:postgresql://localhost/postgres";
		Connection conn = DriverManager.getConnection(url);
		String sql = "select * from dev_test  where name=? and quantity=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ParameterMetaData pmmd = pstmt.getParameterMetaData();
		String str = SunnyMetaDataUtil.convert2String(pmmd);
		System.out.println(str);
		System.out.println("-----------------------------------");

		pstmt.setString(1, "TEST");
		pstmt.setInt(2, 123);
		ResultSet rs = pstmt.executeQuery();
		int c = rs.getMetaData().getColumnCount();
		while (rs.next()) {
			for (int i = 0; i < c; i++) {
				System.out.println(rs.getMetaData().getColumnLabel(i + 1));
				System.out.println(rs.getString(i + 1));
			}
		}
		rs.close();
		conn.close();
	}

	
	
	
	
	
}
