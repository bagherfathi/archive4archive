package com.ft.webui;

import javax.servlet.jsp.tagext.TagSupport;

import com.ft.commons.web.SpringWebUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.jsp.JspException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.struts.taglib.TagUtils;

/**
 * 从请求中取出给定键值的数据.
 * 
 * @jsp.tag name="query" display-name="LookupEntityTag" body-content="empty"
 * 
 * @version 1.0
 * 
 */
public class LookupEntityTag extends TagSupport {

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
    protected List params;

    /**
     * 需要获取属性值的属性
     */
    private String property;
    
    private String defaultValue="";
    
    public LookupEntityTag() {
        params = null;
    }

    public void release() {
        super.release();
        beanName = null;
        methodName = null;
        property = null;
        params = null;
        defaultValue = "";
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
    
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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
        } catch (Throwable e) {
            result = null;
        } 
        String propValue = "";
        
        if(result instanceof java.lang.Long){
         Long l=(Long)result;
         propValue=l.longValue()+"";
        }else if(result instanceof java.lang.String){
         propValue=(String) result;
        }else if(result instanceof java.lang.Boolean){
         Boolean b=(Boolean)result;
         propValue=b.booleanValue()+"";
        }else{
          try {
                 propValue = BeanUtils.getProperty(result, getProperty());
             } catch (Throwable e) {
                 result = null;
             }
        }
         
         
        
       
        if (result == null)            
            propValue = defaultValue;
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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

}
