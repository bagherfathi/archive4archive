package com.ft.webui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * @jsp.tag name="urlSplit" display-name="URLSplitTag" body-content="JSP"

 */
public class URLSplitTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String var;
    private int index;
    private String prefix;

    public int doStartTag() throws JspException {
        // String path = WebUtils.getMappingPath((HttpServletRequest) this.pageContext.getRequest());
        String path = "index";

        if (prefix != null) {
            int idx = path.indexOf(prefix);

            if (idx >= 0) {
                path = path.substring(prefix.length());
            }
        }

        String[] paths = path.split("/");
        String result = null;

        if (paths.length > index) {
            result = paths[index];
        } else {
            result = "/";
        }

        pageContext.setAttribute(var, result);

        return SKIP_BODY;
    }

    public void release() {
        super.release();
        this.var = null;
        this.prefix = null;
        this.index = 0;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
