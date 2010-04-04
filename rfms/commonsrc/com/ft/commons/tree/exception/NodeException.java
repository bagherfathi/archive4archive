package com.ft.commons.tree.exception;

public class NodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NodeException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public NodeException(String message) {
        super(message);
    }

    public NodeException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public NodeException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
