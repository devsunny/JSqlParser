package com.asksunny.sql.engine.util;

import java.util.HashSet;

public class CaseInsensitiveSet extends HashSet<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean contains(Object o) {
		if (o == null)
			return false;
		return super.contains(o.toString().toLowerCase());
	}

	@Override
	public boolean add(String e) {
		if (e == null)
			return false;
		return super.add(e.toLowerCase());
	}

	@Override
	public boolean remove(Object o) {
		if (o == null)
			return false;
		return super.remove(o.toString().toLowerCase());
	}

}
