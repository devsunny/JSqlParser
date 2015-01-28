package com.asksunny.sql.engine;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public abstract class TextDataColumnQueryPlan<R> implements
		ColumnQueryPlan<String, R> {

	public static final String DECIMAL_POINT = ".";
	public static final String BIT_VALUE_ZERO = "0";
	public static final String BIT_VALUE_ONE = "1";
	public static final String BOOLEAN_VALUE_TRUE = "true";

	protected int jdbcType = Types.VARCHAR;
	protected String informat = "yyyy-MM-dd";
	protected  Class<R> returnType;
	
	public TextDataColumnQueryPlan(Class<R> returnType) {
		this.returnType = returnType;
	}

	protected R cast(String rawData, Class<R> returnType) throws SQLException {

		if (rawData == null || rawData.length() == 0) {
			return null;
		}
		R ret = null;
		if (returnType == String.class) {
			return returnType.cast(rawData);
		} else {
			Object obj = rawData;
			switch (this.jdbcType) {
			case Types.LONGNVARCHAR:
			case Types.NVARCHAR:
			case Types.VARCHAR:
			case Types.LONGVARCHAR:
			case Types.CLOB:
			case Types.NCLOB:
				break;
			case Types.CHAR:
			case Types.NCHAR:
				if (rawData.length() > 1) {
					throw new SQLException(String.format(
							"%s cannt cast to character", rawData));
				}
				obj = Character.valueOf(rawData.charAt(0));
				break;
			case Types.NUMERIC:
			case Types.DECIMAL:
			case Types.REAL:
			case Types.FLOAT:
			case Types.DOUBLE:
				int idx = rawData.indexOf(".");
				String p1 = rawData;
				String p2 = null;
				if (idx != -1) {
					p1 = rawData.substring(0, idx);
					p2 = rawData.substring(idx + 1);
				}
				try {
					long pl1 = Long.parseLong(p1);
					double pd2 = 0.0;
					if (p2 != null && p2.length() > 0) {
						double dec = Math.pow(10.0, p2.length());
						long pl2 = Long.parseLong(p2);
						pd2 = pl2 / dec;
					}
					obj = new Double(pl1 + pd2);
				} catch (NumberFormatException e) {
					throw new SQLException(String.format(
							"Invalid decimal data [%s]", rawData));
				}
				break;
			case Types.BIT:
				if (BIT_VALUE_ONE.equals(rawData)) {
					obj = Boolean.TRUE;
				} else {
					obj = Boolean.FALSE;
				}
				break;
			case Types.BOOLEAN:
				if (BOOLEAN_VALUE_TRUE.equalsIgnoreCase(rawData)) {
					obj = Boolean.TRUE;
				} else {
					obj = Boolean.FALSE;
				}
				break;
			case Types.TINYINT:
			case Types.SMALLINT:
			case Types.INTEGER:
			case Types.BIGINT:
				try {
					long lval = Long.parseLong(rawData);
					if (returnType == Integer.class) {
						obj = new Integer((int) lval);
					} else if (returnType == Long.class) {
						obj = new Long(lval);
					} else if (returnType == Short.class) {
						obj = new Short((short) lval);
					}
				} catch (NumberFormatException e) {
					throw new SQLException(String.format(
							"Invalid integral data [%s]", rawData));
				}
				break;
			case Types.BINARY:
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
			case Types.BLOB:
				byte[] rawBytes = null;
				try {
					rawBytes = Base64.decodeBase64(rawData);
				} catch (RuntimeException e) {
					rawBytes = rawData.getBytes();
				}
				obj = rawBytes;
				break;
			case Types.DATE:
				try {
					Date d = new SimpleDateFormat(this.informat).parse(rawData);
					obj = new java.sql.Date(d.getTime());
				} catch (ParseException e) {
					throw new SQLException(String.format(
							"Invalid date format [%s] expected format [%s]",
							rawData, this.informat));
				}
				break;
			case Types.TIME:
				try {
					Date d = new SimpleDateFormat(this.informat).parse(rawData);
					obj = new Time(d.getTime());
				} catch (ParseException e) {
					throw new SQLException(String.format(
							"Invalid time format [%s] expected format [%s]",
							rawData, this.informat));
				}
				break;

			case Types.TIMESTAMP:
				try {
					Date d = new SimpleDateFormat(this.informat).parse(rawData);
					obj = new Timestamp(d.getTime());
				} catch (ParseException e) {
					throw new SQLException(
							String.format(
									"Invalid timestamp format [%s] expected format [%s]",
									rawData, this.informat));
				}
				break;
			default:
				throw new SQLException(String.format(
						"Not supported data type %d", this.jdbcType));
			}
			ret = returnType.cast(obj);
			return ret;
		}

	}

	public int getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(int jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getInformat() {
		return informat;
	}

	public void setInformat(String informat) {
		this.informat = informat;
	}

	
}
