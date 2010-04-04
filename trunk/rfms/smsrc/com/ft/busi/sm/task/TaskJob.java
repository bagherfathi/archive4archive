package com.ft.busi.sm.task;

/**
 * 任务接口。 所有任务必须实现该接口。
 * 
 */

public interface TaskJob {
    public void execute(TaskJobContext context) throws TaskJobException;
}
