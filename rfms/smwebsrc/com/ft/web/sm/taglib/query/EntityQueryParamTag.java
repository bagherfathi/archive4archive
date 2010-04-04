package com.ft.web.sm.taglib.query;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.taglibs.standard.lang.jstl.Evaluator;
import org.hibernate.type.Type;
import org.hibernate.type.TypeFactory;

/**
 * 和EntityQueryTag嵌套使用，用于传参。
 * @version 1.0
 */
public class EntityQueryParamTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    private String name;

    private String type;

    private Object value;

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
     *                The name to set.
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
     *                The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param value
     *                The value to set.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return Returns the value.
     */
    public Object getValue() {
        return value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    @SuppressWarnings("unchecked")
	public int doEndTag() throws JspException {
        EntityQueryTag tag = (EntityQueryTag) findAncestorWithClass(this,
                EntityQueryTag.class);

        if (tag == null) {
            return EVAL_PAGE;
        }

        Type type = TypeFactory.basic(this.type);
        Evaluator Evalutator = new Evaluator();
        try {
            this.value = (String) Evalutator.evaluate("value", this.value
                    .toString(), String.class, this, pageContext);
        } catch (JspException e) {
            throw new RuntimeException(e);
        }

        try {
            Object objValue = ConvertUtils.convert(value.toString(), type
                    .getReturnedClass());

            if (tag.params == null)
                tag.params = new ArrayList();
            tag.params.add(objValue);
        } catch (Exception e) {
            throw new JspException(e);
        }

        this.release();

        return EVAL_PAGE;
    }
}
