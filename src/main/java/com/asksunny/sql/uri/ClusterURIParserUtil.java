package com.asksunny.sql.uri;

import java.io.StringReader;

public class ClusterURIParserUtil {

	public ClusterURI parse(String utiString) throws InvalidURIStringException {
		ClusterURI ret = null;
		try {
			ClusterURIParser parser = new ClusterURIParser(new StringReader(
					utiString));
			ret = parser.fullURL();
		} catch (ParseException e) {
			throw new InvalidURIStringException(String.format(
					"Invalid URI %s, \n%s", utiString, e.toString()));
		}
		return ret;
	}

	private ClusterURIParserUtil() {
		
	}
	
	

}
