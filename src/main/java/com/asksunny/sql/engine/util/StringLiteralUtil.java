package com.asksunny.sql.engine.util;

import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorConverter.DateConverter;

import com.asksunny.sql.date.DateFormatConverter;

public final class StringLiteralUtil {

	public static final String RUBY_TEXT_QUOTE = "\"\"\"";
	public static final String SCRIPT_SINGLE_QUOTE = "'";
	public static final String STRING_DOUBLE_QUOTE = "\"";
	public static final int QUOTE_LEN_1 = 1;
	public static final int QUOTE_LEN_3 = 3;

	private StringLiteralUtil() {
	}

	public static  String unquote(String value) {
		if (value == null) {
			return null;
		} else if (value.startsWith(STRING_DOUBLE_QUOTE)
				&& value.endsWith(STRING_DOUBLE_QUOTE)) {
			return value.substring(QUOTE_LEN_1, value.length() - QUOTE_LEN_1);
		} else if (value.startsWith(SCRIPT_SINGLE_QUOTE)
				&& value.endsWith(SCRIPT_SINGLE_QUOTE)) {
			return value.substring(QUOTE_LEN_1, value.length() - QUOTE_LEN_1);
		} else if (value.startsWith(RUBY_TEXT_QUOTE)
				&& value.endsWith(RUBY_TEXT_QUOTE)) {
			return value.substring(QUOTE_LEN_3, value.length() - QUOTE_LEN_3);
		} else {
			return value;
		}
	}

	public static  String unquote(String value, String quote) {
		if (value == null) {
			return null;
		} else if (value.startsWith(quote) && value.endsWith(quote)) {
			return value.substring(quote.length(),
					value.length() - quote.length());
		} else {
			return value;
		}
	}
	
	public static  String unquote(String value, String startQuote, String endQuote) {
		if (value == null) {
			return null;
		} else if (value.startsWith(startQuote) && value.endsWith(endQuote)) {
			return value.substring(startQuote.length(),
					value.length() - endQuote.length());
		} else {
			return value;
		}
	}
	
	
	public static String dbDateFormatToJavaFormat(String dbDateFormat)
	{
		return DateFormatConverter.toJavaDateFormat(dbDateFormat);
	}
	

}
