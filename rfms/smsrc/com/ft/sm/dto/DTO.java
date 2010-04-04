package com.ft.sm.dto;

/**
 * DTO接口。
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 */
public interface DTO extends java.io.Serializable {
    /**
     * 获取DTO中封装的主实体。
     * 
     * @return
     */
    public Object getTarget();

    /**
     * 设置DTO中的主实体。
     * 
     */
    public void setTarget(Object target);
}
