package com.asksunny.sql.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClusterURI {

	private ProtocolScheme scheme;
	private List<TcpHost> hosts;
	private String path;
	private Map<String, String> queryParameters = new HashMap<String, String>();

	public ClusterURI() {

	}

	public ProtocolScheme getScheme() {
		return scheme;
	}

	public void setScheme(ProtocolScheme scheme) {
		this.scheme = scheme;
	}

	public List<TcpHost> getHosts() {
		return hosts;
	}

	public void setHosts(List<TcpHost> hosts) {
		this.hosts = hosts;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, String> getQueryParameters() {
		return queryParameters;
	}

	public void addQueryParameter(QueryParameter param) {
		try {
			String k = URLDecoder.decode(param.getName(), "UTF-8");
			String val = param.getValue() != null ? URLDecoder.decode(
					param.getValue(), "UTF-8") : null;
			this.queryParameters.put(k, val);
		} catch (UnsupportedEncodingException e) {
			this.queryParameters.put(param.getName(), param.getValue());
		}

	}

}
