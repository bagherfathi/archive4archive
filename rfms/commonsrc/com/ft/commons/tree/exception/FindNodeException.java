package com.ft.commons.tree.exception;

public class FindNodeException extends NodeException {

	private static final long serialVersionUID = 1L;

	public FindNodeException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public FindNodeException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public FindNodeException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public FindNodeException(String message) {
        super(message);
    }
}
