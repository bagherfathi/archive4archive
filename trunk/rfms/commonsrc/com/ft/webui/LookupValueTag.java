package com.ft.webui;

import com.ft.commons.lookup.Lookuper;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.LabelValueBean;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * @jsp.tag name="lookup" display-name="LookupValueTag" body-content="empty"
 *
 *
 */
public class LookupValueTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String var;
    private String beanName;
    private Object value;

    /**
     * @return Returns the bean.
     */
    public String getBeanName() {
        return beanName;
    }

    /**
    * @jsp.attribute required="true" rtexprvalue="true"
     */
    public void setBeanName(String bean) {
        this.beanName = bean;
    }

    /**
     * @return Returns the value.
     */
    public Object getValue() {
        return value;
    }

    /**
    * @jsp.attribute required="true" rtexprvalue="true"
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return Returns the var.
     */
    public String getVar() {
        return var;
    }

    /**
    * @jsp.attribute required="false" rtexprvalue="false"
     */
    public void setVar(String var) {
        this.var = var;
    }
    
	public int doStartTag() throws JspException {
        String label = "";
        Lookuper lookuper =
            (Lookuper) TagUtils.getInstance()
                               .lookup(pageContext, beanName, null);
        
        if (this.value != null) {
        	 Evaluator Evalutator = new Evaluator();
           	try {
           		this.value  = (String) Evalutator.evaluate("value",this.value.toString(),String.class,this,pageContext);
       		} catch (JspException e) {
       			throw new RuntimeException(e);
       		}
            LabelValueBean bean = lookuper.lookup(this.value);

            if (bean != null) {
                label = bean.getLabel();
            }
        }

        TagUtils.getInstance().write(pageContext, label.toString());

        return SKIP_BODY;
    }

    public void setPageContext(PageContext arg0) {
        super.setPageContext(arg0);
    }
}
