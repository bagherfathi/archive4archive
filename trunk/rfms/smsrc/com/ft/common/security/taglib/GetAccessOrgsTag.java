package com.ft.common.security.taglib;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.ft.common.session.OperatorSessionHelper;

/**
 * ���ڻ�ȡ��ǰ����Ա�ɷ�����֯.
 * 
 * @version 1.0
 */
public class GetAccessOrgsTag extends TagSupport {
    private static final long serialVersionUID = -8204896302908662246L;

    private String var = "accessOrgs";

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    public int doStartTag() throws JspException {
        List accessOrgs = OperatorSessionHelper.getAccessOrgsOfLoginOp((HttpServletRequest) pageContext.getRequest());
        pageContext
                .setAttribute(var, accessOrgs, PageContext.APPLICATION_SCOPE);
        return super.doStartTag();
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
