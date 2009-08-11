package com.renhenet.util;

import com.renhenet.fw.exception.BaseException;

public class XMLParseException extends BaseException {
	private static final long serialVersionUID = -5528892821697089930L;

	public XMLParseException(Throwable e) {
		super("", e);
	}
}
