package com.ft.busi.sm.task;

import java.io.Serializable;

/**
 * 执行任务时的上下文接口。
 * 
 * 
 */

public interface TaskJobContext extends Serializable {
    public Object getParameter(String name);

    public void setParameter(String name, Object value);
}
