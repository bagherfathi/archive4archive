package com.renhenet.modules.member;

import com.renhenet.fw.exception.BaseException;

@SuppressWarnings("serial")
public class RegException extends BaseException {
	public RegException() {
		super();
	}

	public RegException(String s) {
		super(s);
	}

	public RegException(String s, Throwable e) {
		super(s, e);
	}

	public RegException(Throwable e) {
		super(e);
	}

}
