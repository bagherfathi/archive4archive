package com.ft.webui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.LabelValueBean;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.commons.lookup.EnumLookuper;

/**
 * 根据给定的枚举数据代码和枚举值查找对应的描述并输出到页面
 *
 * @jsp.tag name="lookup" display-name="EnumLookupValueTag" body-content="empty"
 *
 */
public class EnumLookupValueTag extends TagSupport{
	private static final long serialVersionUID = 1L;
    private String var;
    private String beanName = "enumSet";
    private String code;
    private Object value;

    /**
     * @return Returns the bean.
     */
    public String getBeanName() {
        return beanName;
    }

    /**
    * @jsp.attribute required="false" rtexprvalue="true"
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
    
    

    public String getCode() {
		return code;
	}

    /**
     * @jsp.attribute required="true" rtexprvalue="true"
      */
	public void setCode(String code) {
		this.code = code;
	}

	public int doStartTag() throws JspException {
		String label = "";
		
		EnumLookuper lookuper = (EnumLookuper)TagUtils.getInstance().lookup(pageContext, beanName, null);
		Evaluator aEvaluator = new Evaluator();
		if (this.value != null && this.code != null) {
			this.value  = (String) aEvaluator.evaluate("value",this.value.toString(),String.class,this,pageContext);
            LabelValueBean bean = lookuper.lookup(this.code,this.value);
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
