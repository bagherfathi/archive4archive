package com.ft.sm.dto;

/**
 * DTO�ӿڡ�
 * 
 * @author <a href="mailto:libf@chances.com.cn">libf</a>
 * @version 1.0
 */
public interface DTO extends java.io.Serializable {
    /**
     * ��ȡDTO�з�װ����ʵ�塣
     * 
     * @return
     */
    public Object getTarget();

    /**
     * ����DTO�е���ʵ�塣
     * 
     */
    public void setTarget(Object target);
}
