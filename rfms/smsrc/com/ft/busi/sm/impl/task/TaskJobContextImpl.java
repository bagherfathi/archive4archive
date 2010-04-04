package com.ft.busi.sm.impl.task;

import org.quartz.JobExecutionContext;

import com.ft.busi.sm.task.TaskJobContext;

import java.util.HashMap;

/**
 * 执行任务时的上下文实现类。
 * 
 * 
 */
public class TaskJobContextImpl implements TaskJobContext {
    private static final long serialVersionUID = -6763694108926374750L;

    JobExecutionContext jobContext;

    private HashMap<String,Object> params = new HashMap<String,Object>();

    public TaskJobContextImpl(JobExecutionContext context) {
        jobContext = context;
    }

    public Object getParameter(String name) {
        return params.get(name);
    }

    public void setParameter(String name, Object value) {
        params.put(name, value);
    }
}
