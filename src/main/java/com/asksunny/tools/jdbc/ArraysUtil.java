package com.asksunny.tools.jdbc;

import java.util.ArrayList;
import java.util.List;

public final class ArraysUtil {

	
	public static <T> List<T> filterFunction(T[] types, Filter<T> filter){
		List<T>  mts = new ArrayList<T>();
		
		for (int i = 0; i < types.length; i++) {
			if(filter.match(types[i])){
				mts.add(types[i]);
			}
		}		
		return mts;
	}

}
