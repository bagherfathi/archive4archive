package com.ft.common.enumdata.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.LabelValueBean;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import com.ft.common.enumdata.EnumRepository;
import com.ft.commons.web.SpringWebUtils;

/**
 * 用于获取Enum的标签
 * 
 * @version 1.0
 */
public class EnumTag extends TagSupport {
    private static final long serialVersionUID = -577993464397935175L;

    private String enumCode;

    private String linkValue;

    private String var = "enumVar";

    /*
     * (non-Javadoc)
     * 
     * @see com.onewaveinc.vod.taglib.BaseSimpleTag#doStartTag()
     */
    public int doStartTag() throws JspException {
        EnumRepository repository = (EnumRepository) SpringWebUtils.getBean(
                pageContext.getServletContext(), EnumRepository.getBeanName());

        if (linkValue == null || linkValue.length() <= 0) {
            LabelValueBean[] result = repository.getElement(enumCode);
            pageContext
                    .setAttribute(var, result, PageContext.APPLICATION_SCOPE);
        } else {
            LabelValueBean[] result = repository
                    .getElement(enumCode, linkValue);
            pageContext
                    .setAttribute(var, result, PageContext.APPLICATION_SCOPE);
        }
        clear();
        return super.doStartTag();
    }

    private void clear() {
        this.enumCode = null;
        this.linkValue = null;
    }

    /**
     * @return the enumCode
     */
    public String getEnumCode() {
        return enumCode;
    }

    /**
     * @param enumCode
     *                the enumCode to set
     */
    public void setEnumCode(String enumCode) {
        Evaluator Evalutator = new Evaluator();
        try {
            this.enumCode = (String) Evalutator.evaluate("enumCode", enumCode,
                    String.class, this, pageContext);
        } catch (JspException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the linkValue
     */
    public String getLinkValue() {
        return linkValue;
    }

    /**
     * @param linkValue
     *                the linkValue to set
     */
    public void setLinkValue(String linkValue) {
        Evaluator Evalutator = new Evaluator();
        try {
            this.linkValue = (String) Evalutator.evaluate("linkValue",
                    linkValue, String.class, this, pageContext);
        } catch (JspException e) {
            throw new RuntimeException(e);
        }
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
