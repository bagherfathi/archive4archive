package com.ft.busi.sm.task;

import java.io.Serializable;

/**
 * ִ������ʱ�������Ľӿڡ�
 * 
 * 
 */

public interface TaskJobContext extends Serializable {
    public Object getParameter(String name);

    public void setParameter(String name, Object value);
}
