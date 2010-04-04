package com.ft.webui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * @jsp.tag name="menus" display-name="MenusTag" body-content="JSP"
 *
 */
public class MenusTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String var;
    private String module;

    public void release() {
        super.release();
        this.module = null;
        this.var = null;
    }

    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setPageContext(PageContext pageContext) {
        super.setPageContext(pageContext);
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
