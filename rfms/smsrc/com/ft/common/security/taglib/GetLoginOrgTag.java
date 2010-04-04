package com.ft.common.security.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.ft.common.session.OperatorSessionHelper;
import com.ft.sm.dto.OrganizationDTO;

/**
 * 用于获取当前操作员登陆组织.
 * 
 * @version 1.0
 */
public class GetLoginOrgTag extends TagSupport {
    private static final long serialVersionUID = 3920330377283601468L;

    private String var = "loginOrg";

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    public int doStartTag() throws JspException {
        OrganizationDTO loginOrg = OperatorSessionHelper.getLoginOrg((HttpServletRequest) pageContext.getRequest());
        if (loginOrg != null)
            pageContext.setAttribute(var, loginOrg,
                    PageContext.APPLICATION_SCOPE);

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
