package com.ft.hibernate.datasource;

import java.io.Serializable;


/**
 * 实体参数对象.
 *
 */
public class EntityParam implements Serializable {
    /** Comment for <code>serialVersionUID</code> .*/
    private static final long serialVersionUID = 1L;

    /**实体参数名.*/
    private String name;

    /** 实体参数属性. */
    private String attribute;

    /** 类型.*/
    private Class type;

    /** 值 .*/
    private Object value;

    /** 默认值 .*/
    private String defaultValue;

    /** 是否是固定的.*/
    private boolean fixed;

    /** http请求中的表单控件的name，如果paramName==null，则取值name**/
    private String paramName;
    
    /** */
    private String expr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getExpr() {
        return expr;
    }

    public void setExpr(String expr) {
        this.expr = expr;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public String getAttribute() {
        if (this.attribute == null) {
            return name;
        }

        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
}
