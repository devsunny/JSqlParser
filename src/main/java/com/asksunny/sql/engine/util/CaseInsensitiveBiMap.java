package com.asksunny.sql.engine.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CaseInsensitiveBiMap<V> extends CaseInsensitiveMap<V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<V, String> invertMap = new HashMap<V, String>();

	@Override
	public V put(String key, V value) {
		invertMap.put(value, key);
		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends V> m) {
		super.putAll(m);
		Set<? extends String> keys = m.keySet();
		for (String key : keys) {
			invertMap.put(m.get(key), key);
		}
	}
	
	public String getKey(V val)
	{
		return invertMap.get(val);
	}
	
	

}
