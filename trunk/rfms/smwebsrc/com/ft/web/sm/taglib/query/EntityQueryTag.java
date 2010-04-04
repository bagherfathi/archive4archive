package com.ft.web.sm.taglib.query;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.MethodUtils;

import com.ft.commons.web.SpringWebUtils;
import com.ft.hibernate.taglib.PageContextUtils;

/**
 * 用于页面调用Manager中的方法进行实体查询。
 * 
 * @version 1.0
 */
public class EntityQueryTag extends TagSupport {
    private static final long serialVersionUID = 0L;

    /**
     * 在spring context中的注入名
     */
    private String beanName;

    /**
     * 对象的方法名
     */
    private String methodName;

    /**
     * 方法的参数列表
     */
    protected List params = new ArrayList();

    /**
     * 用于存放查询结果的变量名。
     */
    private String var;

    private String scope;

    public EntityQueryTag() {
        params = null;
    }

    public void release() {
        super.release();
        beanName = null;
        methodName = null;
        var = null;
        if (this.params != null)
            this.params.clear();
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int doStartTag() throws JspException {
        return EVAL_BODY_AGAIN;
    }

    public int doEndTag() throws JspException {
        Object object = SpringWebUtils.getBean(pageContext, getBeanName());
        Object result = null;
        try {
            if (this.params == null || this.params.size() <= 0) {
                result = MethodUtils.invokeMethod(object, getMethodName(),
                        new Object[0]);
            } else {
                result = MethodUtils.invokeMethod(object, getMethodName(),
                        this.params.toArray());
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        if (this.var != null && result != null) {
            pageContext.setAttribute(var, result, PageContextUtils
                    .getScope(this.scope));
        }
        release();
        return EVAL_PAGE;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope
     *                the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return the var
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var
     *                the var to set
     */
    public void setVar(String var) {
        this.var = var;
    }
}
