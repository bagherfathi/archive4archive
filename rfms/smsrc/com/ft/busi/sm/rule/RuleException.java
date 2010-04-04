package com.ft.busi.sm.rule;

/**
 * 规则执行异常类,代替规则引擎内部的异常。
 * 
 * @version 1.0
 */
public class RuleException extends Exception {

    private static final long serialVersionUID = 1L;

    public RuleException() {
        super();
    }

    public RuleException(String arg0, Throwable arg1) {
        super(arg0, arg1);

    }

    public RuleException(String arg0) {
        super(arg0);

    }

    public RuleException(Throwable arg0) {
        super(arg0);

    }

}
