package com.asksunny.sql.engine.plan;

public class ExpressionEvaluationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpressionEvaluationException() {
		
	}

	public ExpressionEvaluationException(String message) {
		super(message);		
	}

	public ExpressionEvaluationException(Throwable cause) {
		super(cause);		
	}

	public ExpressionEvaluationException(String message, Throwable cause) {
		super(message, cause);		
	}

	public ExpressionEvaluationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
