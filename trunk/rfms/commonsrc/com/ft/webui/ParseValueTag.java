package com.ft.webui;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.struts.taglib.TagUtils;

import com.ft.commons.web.SpringWebUtils;

/**
 * @jsp.tag name="parseValue" display-name="ParseValueTag" body-content="empty"
 */
public class ParseValueTag extends TagSupport {
    private static final long serialVersionUID = 8789733960879108006L;
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
    protected List params;

    public ParseValueTag() {
        params = null;
    }

    public void release() {
        super.release();
        beanName = null;
        methodName = null;
        params = null;
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
        return 2;
    }

    public int doEndTag() throws JspException {
        Object object = SpringWebUtils.getBean(pageContext, getBeanName());
        Object result = null;
        try {
            result = MethodUtils.invokeMethod(object, getMethodName(),
                    getParams().toArray());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        String propValue = result.toString();

        TagUtils.getInstance().write(pageContext, propValue);
        release();
        return 6;
    }

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

}
