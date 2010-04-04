package com.ft.busi.sm.task;

import com.ft.exception.MessageException;

/**
 * 执行任务时的异常。
 * 
 */

public class TaskJobException extends MessageException {
    private static final long serialVersionUID = 1L;

    public TaskJobException(String arg0, Object[] params, Throwable arg1) {
        super(arg0, params, arg1);

        // TODO Auto-generated constructor stub
    }

    public TaskJobException(String arg0, Object[] params) {
        super(arg0, params);

        // TODO Auto-generated constructor stub
    }

    public TaskJobException(String arg0, Throwable arg1) {
        super(arg0, arg1);

        // TODO Auto-generated constructor stub
    }

    public TaskJobException(String arg0) {
        super(arg0);

        // TODO Auto-generated constructor stub
    }
}
