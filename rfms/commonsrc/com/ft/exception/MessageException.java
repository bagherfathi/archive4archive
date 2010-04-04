package com.ft.exception;


/**
 * 有参数的异常处理
 *
 */
public class MessageException extends Exception {
    private static final long serialVersionUID = 1L;
    protected Object[] params;

    public MessageException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public MessageException(String arg0) {
        super(arg0);
    }

    public MessageException(String arg0, Object[] params, Throwable arg1) {
        super(arg0, arg1);
        this.params = params;
    }

    public MessageException(String arg0, Object[] params) {
        super(arg0);
        this.params = params;
    }

    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }
}
