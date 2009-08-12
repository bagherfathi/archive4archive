package com.renhenet.fw.sms;

import com.renhenet.fw.exception.BaseRuntimeException;

public class SmsServiceException extends BaseRuntimeException {
	private static final long serialVersionUID = 4536066481814319371L;

	public SmsServiceException() {
		super();
	}

	public SmsServiceException(String arg0) {
		super(arg0);
	}

	public SmsServiceException(Throwable arg0) {
		super(arg0);
	}

	public SmsServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}