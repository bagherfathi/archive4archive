package com.ft.busi.sm.task;

/**
 * ����ӿڡ� �����������ʵ�ָýӿڡ�
 * 
 */

public interface TaskJob {
    public void execute(TaskJobContext context) throws TaskJobException;
}
