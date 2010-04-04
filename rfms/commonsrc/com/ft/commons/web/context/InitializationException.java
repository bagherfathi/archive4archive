package com.ft.commons.web.context;

/**
 * ��ʼ���쳣
 */
public class InitializationException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public InitializationException() {
		super();
	}

	public InitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitializationException(String message) {
		super(message);
	}

	public InitializationException(Throwable cause) {
		super(cause);
	}

}
