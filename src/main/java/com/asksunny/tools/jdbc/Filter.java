package com.asksunny.tools.jdbc;

public interface Filter<T> 
{
	boolean match(T param);
}
