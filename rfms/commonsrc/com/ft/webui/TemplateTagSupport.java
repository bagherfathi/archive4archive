package com.ft.webui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class TemplateTagSupport extends TagSupport {
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
        TemplateUtils templateUtils = TemplateUtils.getInstance(pageContext);

        try {
            templateUtils.merge(
                this.getTemplateFile() + "Begin", this.getVarName(), this,
                this.pageContext.getOut());
        } catch (Exception e) {
            throw new JspException(e);
        }

        return (EVAL_BODY_INCLUDE);
    }

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException {
        TemplateUtils templateUtils = TemplateUtils.getInstance(pageContext);

        try {
            templateUtils.merge(
                this.getTemplateFile() + "End", this.getVarName(), this,
                this.pageContext.getOut());
        } catch (Exception e) {
            throw new JspException(e);
        }

        return (EVAL_PAGE);
    }

    public abstract String getVarName();

    public String getTemplateFile() {
        String name = this.getClass().getName();
        int idx = name.lastIndexOf('.');

        return "webui/" + name.substring(idx+1);
    }
}
