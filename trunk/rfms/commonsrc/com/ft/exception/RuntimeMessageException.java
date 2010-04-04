package com.ft.exception;


/**
 * 带参数的RunTimeException
 *
 */
public class RuntimeMessageException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected Object[] params;

    public RuntimeMessageException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public RuntimeMessageException(String arg0) {
        super(arg0);
    }

    public RuntimeMessageException(
        String arg0, Object[] params, Throwable arg1) {
        super(arg0, arg1);
        this.params = params;
    }

    public RuntimeMessageException(String arg0, Object[] params) {
        super(arg0);
        this.params = params;
    }
}
