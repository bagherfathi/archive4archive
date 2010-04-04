package com.ft.busi.sm.rule;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 规则引擎下上文。
 * 
 * @version 1.0
 */
public class RuleEngineContext implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Map<String,Object> globalAttributes = new HashMap<String,Object>();

    private Class returnType;

    private Map<String,Object> attribute = new HashMap<String,Object>();

    private Object result;

    private String resultName;

    /**
     * @return Returns the attribute.
     */
    public Map getAttribute() {
        return attribute;
    }

    /**
     * @return Returns the globalAttributes.
     */
    public Map getGlobalAttributes() {
        return globalAttributes;
    }

    /**
     * @return Returns the resultName.
     */
    public String getResultName() {
        return resultName;
    }

    /**
     * @param resultName
     *                The resultName to set.
     */
    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    /**
     * @return Returns the returnType.
     */
    public Class getReturnType() {
        return returnType;
    }

    /**
     * @param returnType
     *                The returnType to set.
     */
    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }

    /**
     * 设置全局变量
     * 
     * @param name
     * @param value
     */
    public void addGlobalAttribute(String name, Object value) {
        this.globalAttributes.put(name, value);
    }

    /**
     * 得到全局变量
     * 
     * @param name
     * @return
     */
    public Object getGlobalAttribute(String name) {
        return this.globalAttributes.get(name);
    }

    /**
     * 设置变量
     * 
     * @param name
     * @param value
     */
    public void addAttribute(String name, Object value) {
        this.attribute.put(name, value);
    }

    /**
     * 得到变量
     * 
     * @param name
     * @return
     */
    public Object getAttribute(String name) {
        return this.globalAttributes.get(name);
    }

    public Object createResult() {
        if (returnType != null) {
            try {
                result = returnType.newInstance();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;

    }

    public void reset() {

    }

    public Object getResult() {
        if (returnType == null) {
            return null;
        } else {
            return result;
        }
    }

}
