package com.ft.busi.sm.template;

/**
 * 模板引擎执行时产生的异常类。
 * 
 * @version 1.0
 */
public class TemplateException extends Exception {
    private static final long serialVersionUID = -2390981910681418116L;

    public TemplateException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public TemplateException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public TemplateException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public TemplateException(Throwable cause) {
        super(cause);
    }
}
