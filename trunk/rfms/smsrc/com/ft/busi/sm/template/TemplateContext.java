package com.ft.busi.sm.template;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板引擎执行时上下文。用于模板引擎执行时传入参数。
 * 
 * @version 1.0
 */
public class TemplateContext {
    private Map<String,Object> attributes;

    public TemplateContext() {
        attributes = new HashMap<String,Object>();
    }

    /**
     * 添加参数。
     * 
     * @param name
     *                参数名称。
     * @param value
     *                参数值。
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
