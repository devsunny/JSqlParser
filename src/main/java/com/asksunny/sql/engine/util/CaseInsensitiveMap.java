package com.asksunny.sql.engine.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class CaseInsensitiveMap<V> extends HashMap<String, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public V get(Object key) {
		if(key==null) throw new NullPointerException("Hash key cann't be null");
		return super.get(key.toString().toLowerCase());
	}

	@Override
	public boolean containsKey(Object key) {
		if(key==null) throw new NullPointerException("Hash key cann't be null");
		return super.containsKey(key.toString().toLowerCase());
	}

	@Override
	public V put(String key, V value) {
		if(key==null) throw new NullPointerException("Hash key cann't be null");
		return super.put(key.toLowerCase(), value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends V> m) {
		Set<? extends String> keys = m.keySet();
		for(String key: keys){
			put(key, m.get(key));
		}		
	}

	@Override
	public V remove(Object key) {
		if(key==null) throw new NullPointerException("Hash key cann't be null");
		return super.remove(key.toString().toLowerCase());
	}
	
}
