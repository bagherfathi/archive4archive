package com.renhenet.fw.dao;

import com.renhenet.fw.exception.BaseRuntimeException;

public class DAOException extends BaseRuntimeException {
	private static final long serialVersionUID = -308028819606818562L;

	public DAOException() {
		super();
	}
	public DAOException(String arg0) {
		super(arg0);
	}

	public DAOException(Throwable arg0) {
		super(arg0);
	}

	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
