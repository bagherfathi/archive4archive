package com.ft.busi.sm.template;

import java.util.HashMap;
import java.util.Map;

/**
 * ģ������ִ��ʱ�����ġ�����ģ������ִ��ʱ���������
 * 
 * @version 1.0
 */
public class TemplateContext {
    private Map<String,Object> attributes;

    public TemplateContext() {
        attributes = new HashMap<String,Object>();
    }

    /**
     * ��Ӳ�����
     * 
     * @param name
     *                �������ơ�
     * @param value
     *                ����ֵ��
     */
    public void addAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    /**
     * @return the attributes
     */
    public Map getAttributes() {
        return attributes;
    }
}
