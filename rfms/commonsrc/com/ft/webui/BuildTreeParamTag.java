package com.ft.webui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.hibernate.type.Type;
import org.hibernate.type.TypeFactory;

/**
 * 构建树参数标签.
 * 
 * @author <a href="mailto:liuliang2@126.com">刘亮</a>
 * @version 1.0
 */
public class BuildTreeParamTag extends TagSupport{
    private String name;
    private String type;
    private String value;

    public void release() {
        super.release();
        this.name = null;
        this.type = null;
        this.value = null;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doEndTag() throws JspException {
	/*
        BuildTreeTag tag =
            (BuildTreeTag) findAncestorWithClass(
                this, BuildTreeTag.class);
        
        if (tag == null) {
            return EVAL_PAGE;
        }
        Evaluator aEvaluator = new Evaluator();
        Object valueObj = (Object)aEvaluator.evaluate("value", this.value,
        	Object.class, this, pageContext);
        
        tag.params.put(this.name,valueObj);

        this.release();

        return EVAL_PAGE;
        */
	BuildTreeTag tag =
            (BuildTreeTag) findAncestorWithClass(
                this, BuildTreeTag.class);

        if (tag == null) {
            return EVAL_PAGE;
        }

        Type type = TypeFactory.basic(this.type);
        Evaluator aEvaluator = new Evaluator();
        if(type == null){
//            Object valueObj = (Object)aEvaluator.evaluate("value", this.value,
//            	Object.class, this, pageContext);
        	Object valueObj = (Object)pageContext.findAttribute(this.value);
            tag.params.put(this.name,valueObj);
        }else{
            try {
                this.value  = (String) aEvaluator.evaluate("value",this.value.toString(),String.class,this,pageContext);
            } catch (JspException e) {
                throw new RuntimeException(e);
            }

            try {
                Object objValue =
                    ConvertUtils.convert(
                        value.toString(), type.getReturnedClass());

                tag.params.put(this.name, objValue);
            } catch (Exception e) {
                throw new JspException(e);
            }
        }

        this.release();

        return EVAL_PAGE;
    }
}
